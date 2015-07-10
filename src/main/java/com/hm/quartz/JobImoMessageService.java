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

import com.hm.domain.mc.McBasImo;
import com.hm.domain.mc.McBasImoFail;
import com.hm.domain.mc.McBasImoSuccess;
import com.hm.svc.imo.IMcBasImoFailService;
import com.hm.svc.imo.IMcBasImoService;
import com.hm.svc.imo.IMcBasImoSuccessService;
import com.hm.util.MyDate;

/**
 * IMO定时任务
 * 
 * @author 王凯
 * 
 */
@Service
@Transactional
public class JobImoMessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobImoMessageService.class);

	@Autowired
	private IMcBasImoService mcBasImoService;

	@Autowired
	private IMcBasImoSuccessService mcBasImoSuccessService;

	@Autowired
	private IMcBasImoFailService mcBasImoFailService;

	/**
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送IMO<br />
	 * 返回表BAS_MC_IMO IMO表的数据列表<br />
	 * 开发者 王凯 2015年1月06日 15:05:07
	 */
	public void sendMcImoJob() {
		
		List<McBasImo> imoList = mcBasImoService.findBySendTimeStatus(new Date());// 查询IMO 表数据

		if (imoList != null && imoList.size() > 0) {
			logger.info("Send IMO task execution start time:" + new MyDate() + " Information number：" + imoList.size());

			try {
				Iterator<McBasImo> iter = imoList.iterator();
				McBasImo bImo = new McBasImo();
				while (iter.hasNext()) {
					bImo = iter.next(); 

					// 调用消息推送方法  接收人为uid
					String returnFlag = mcBasImoService.sendMsg(bImo.getReceiverUid(), bImo.getSubject(), bImo.getContent());
					logger.info("JobImoMessageService sendMcImoJob returnFlag:" + returnFlag);
					
					JSONObject json = JSONObject.fromObject(returnFlag);
					logger.info("JobImoMessageService sendMcImoJob returnFlagJson:" + json.toString());
					
					// 根据返回值做处理success为发送成功 fail为发送失败
					if ("success".equals(json.get("result"))) {

						McBasImoSuccess bSuccess = new McBasImoSuccess(
								bImo.getReceiver(),bImo.getReceiverUid(), bImo.getSubject(),
								bImo.getContent(), bImo.getKey(), 2L,
								bImo.getGrpId(), bImo.getSendTime(),
								bImo.getCreateTime(), null);

						mcBasImoSuccessService.create(bSuccess);
						mcBasImoService.delete(bImo);
					} else {
						
						McBasImoFail bFail = new McBasImoFail(
								bImo.getReceiver(),bImo.getReceiverUid(), bImo.getSubject(),
								bImo.getContent(), bImo.getKey(), 3L,
								bImo.getGrpId(), "", bImo.getSendTime(),
								new Date(), null);
						mcBasImoFailService.create(bFail);
						mcBasImoService.delete(bImo);
					}
				}
			} catch (Exception e) {
				logger.error("JobImoMessageService sendMcImoJob:" + e.getMessage(), e);
			}
		} 
	}
}
