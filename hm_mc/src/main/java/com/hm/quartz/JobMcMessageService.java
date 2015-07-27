package com.hm.quartz;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.domain.comm.McConstantUtil;
import com.hm.domain.mc.McBasLog;
import com.hm.domain.mc.McBasMessage;
import com.hm.domain.mc.McBasMessageFail;
import com.hm.domain.mc.McBasMessageSuccess;
import com.hm.svc.impl.webservice.ZtSmsService;
import com.hm.svc.log.IMcBasLogService;
import com.hm.svc.mc.IMcBasMessageFailService;
import com.hm.svc.mc.IMcBasMessageService;
import com.hm.svc.mc.IMcBasMessageSuccessService;
import com.hm.util.Converts;
import com.hm.util.MyDate;

/**
 * 短信表 server实现
 * 
 * @author ZUOXP
 * 
 */
@Service
@Transactional
public class JobMcMessageService {

	private static final Logger logger = LoggerFactory.getLogger(JobMcMessageService.class);

	@Autowired
	IMcBasMessageService basMcMessageService;
	@Autowired
	IMcBasMessageFailService basMcMessageFailService;
	@Autowired
	IMcBasMessageSuccessService basMcMessageSuccessService;
	@Autowired
	IMcBasLogService mcBasLogService;
	@Autowired
	ZtSmsService ztSmsService;

	/**
	 * 查询消息表，调用SMS系统WEBSERVICE接口<br />
	 * 若发送成功，插入成功表，并删除信息表中数据<br />
	 * 若未成功，插入失败表，并删除信息表中数据 <br />
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送短信<br />
	 * 返回表T_BAS_MC_MESSAGE 短信表的数据列表<br />
	 * 开发者 ZUOXP 2014年11月27日 16:04:04
	 */
	public void sendMcToSmsJob() {

		List<McBasMessage> list = basMcMessageService.findBySendTimeStatus(new Date());

		if (list != null && list.size() > 0) {
			logger.info("Send Email task execution start time:" + new MyDate() + " Information number：" + list.size());

			try {
				Iterator<McBasMessage> iter = list.iterator();
				McBasMessage bMessage = new McBasMessage();
				while (iter.hasNext()) {
					bMessage = iter.next();// 待发送短信
					/*
					 * 调用sms系统webservice，向sms系统推送消息消息
					 * 1.推送成功，删除短信表中当条数据，并且将此条数据保存入短信成功表。
					 * 2.推送失败，删除短信表中当条数据，并且将此条数据保存入短信失败表。
					 */

					JSONObject obj = ztSmsService.sendSms(bMessage.getReceiver(), bMessage.getContent(), null, null);

					// 记录日志
					McBasLog log = new McBasLog(obj.toString());// 插入日志信息
					mcBasLogService.insertBasMcLog(log);

					if (obj != null) {

						String flag = Converts.objToStr(obj.get("flag")); // 获取返回值执行结果的标志位

						if (McConstantUtil.SUCCESS_FLAG.equals(flag)) {// flag=1表示执行成功

							String msg = obj.get("msg").toString(); // 获取返回结果的详细信息
							JSONObject obj2 = JSONObject.fromObject(msg);
							String suc = obj2.get("suc").toString();// 获取成功条数

							if (!"0".equals(suc)) {// 成功条数大于0
								McBasMessageSuccess bSuccess = new McBasMessageSuccess(bMessage.getReceiver(), bMessage.getContent(), bMessage.getKey(), 2L, bMessage.getGrpId(), bMessage.getMId(), bMessage.getSId(), bMessage.getTaskType(), bMessage.getSendTime(), bMessage.getCreateTime(), null);
								basMcMessageSuccessService.create(bSuccess);
								basMcMessageService.delete(bMessage);
							} else {
								String error = "该信息已经不可多次发送，每天只能发送给同一接收人一次";// 获取返回值中的错误信息
								McBasMessageFail bFail = new McBasMessageFail(bMessage.getReceiver(), bMessage.getContent(), bMessage.getKey(), 3L, bMessage.getGrpId(), bMessage.getMId(), bMessage.getSId(), bMessage.getTaskType(), error, bMessage.getSendTime(), new Date(), null);
								basMcMessageFailService.create(bFail);
								basMcMessageService.delete(bMessage);
							}
						} else {
							String error = "该信息发送失败";// 获取返回值中的错误信息
							McBasMessageFail bFail = new McBasMessageFail(bMessage.getReceiver(), bMessage.getContent(), bMessage.getKey(), 3L, bMessage.getGrpId(), bMessage.getMId(), bMessage.getSId(), bMessage.getTaskType(), error, bMessage.getSendTime(), new Date(), null);
							basMcMessageFailService.create(bFail);
							basMcMessageService.delete(bMessage);
						}
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			logger.info("Send Message to perform a task over:" + new MyDate());
		}
	}

}
