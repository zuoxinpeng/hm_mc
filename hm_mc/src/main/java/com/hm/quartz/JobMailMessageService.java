package com.hm.quartz;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.domain.mc.McBasEmail;
import com.hm.domain.mc.McBasEmailFail;
import com.hm.domain.mc.McBasEmailSuccess;
import com.hm.svc.mc.IMcBasEmailFailService;
import com.hm.svc.mc.IMcBasEmailService;
import com.hm.svc.mc.IMcBasEmailSuccessService;
import com.hm.svc.mc.IMcMessageService;
import com.hm.util.MyDate;

/**
 * 短信表 server实现
 * 
 * @author ZUOXP
 * 
 */
@Service
@Transactional
public class JobMailMessageService {

	private static final Logger logger = LoggerFactory.getLogger(JobMailMessageService.class);

	@Autowired
	IMcMessageService mcMessageService;
	@Autowired
	IMcBasEmailService basMcEmailService;
	@Autowired
	IMcBasEmailFailService basMcEmailFailService;
	@Autowired
	IMcBasEmailSuccessService basMcEmailSuccessService;

	/**
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送短信<br />
	 * 返回表T_BAS_MC_MESSAGE 短信表的数据列表<br />
	 * 开发者 ZUOXP 2014年11月27日 16:04:04
	 */
	public void sendMcEmailJob() {

		List<McBasEmail> list = basMcEmailService.findBySendTimeStatus(new Date());

		if (list != null && list.size() > 0) {
			logger.info("Send Email task execution start time:" + new MyDate() + " Information number：" + list.size());

			try {
				Iterator<McBasEmail> iter = list.iterator();
				McBasEmail bMessage = new McBasEmail();
				while (iter.hasNext()) {
					bMessage = iter.next();// 待发送邮件

					int returnFlag = mcMessageService.sendEmail(bMessage.getReceiver(), bMessage.getSubject(), bMessage.getContent());

					if (returnFlag == 1) {

						McBasEmailSuccess bSuccess = new McBasEmailSuccess(bMessage.getReceiver(), bMessage.getSubject(), bMessage.getContent(), bMessage.getKey(), 2L, bMessage.getGrpId(), bMessage.getTaskType(), bMessage.getSendTime(), bMessage.getCreateTime(), null);
						basMcEmailSuccessService.create(bSuccess);
						basMcEmailService.delete(bMessage);
					} else {

						McBasEmailFail bFail = new McBasEmailFail(bMessage.getReceiver(), bMessage.getSubject(), bMessage.getContent(), bMessage.getKey(), 3L, bMessage.getGrpId(), bMessage.getTaskType(), "", bMessage.getSendTime(), new Date(), null);
						basMcEmailFailService.create(bFail);
						basMcEmailService.delete(bMessage);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
			logger.info("Send Email to perform a task over:" + new MyDate());
		}
	}

}
