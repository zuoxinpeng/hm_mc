package com.hm.svcImpl.mc;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.domain.comm.BasMcParameters;
import com.hm.domain.comm.BaseOutEntity;
import com.hm.domain.comm.McConstantUtil;
import com.hm.domain.mc.McBasEmail;
import com.hm.domain.mc.McBasEmailFail;
import com.hm.domain.mc.McBasImo;
import com.hm.domain.mc.McBasImoFail;
import com.hm.domain.mc.McBasLog;
import com.hm.domain.mc.McBasMessage;
import com.hm.domain.mc.McBasMessageFail;
import com.hm.domain.tem.McBasSystem;
import com.hm.domain.tem.McSysTemplate;
import com.hm.domain.tem.McTemplate;
import com.hm.domain.tem.McTemplateDetail;
import com.hm.svc.imo.IMcBasImoFailService;
import com.hm.svc.imo.IMcBasImoService;
import com.hm.svc.log.IMcBasLogService;
import com.hm.svc.mc.IMcBasEmailFailService;
import com.hm.svc.mc.IMcBasEmailService;
import com.hm.svc.mc.IMcBasMessageFailService;
import com.hm.svc.mc.IMcBasMessageService;
import com.hm.svc.mc.IMcMessageService;
import com.hm.svc.tem.IMcBasSystemService;
import com.hm.svc.tem.IMcSysTemplateService;
import com.hm.svc.tem.IMcTemplateDetailService;
import com.hm.svc.tem.IMcTemplateService;
import com.hm.svcImpl.webservice.SMSXfireClient;
import com.hm.util.Converts;
import com.hm.util.MailSenderUtil;
import com.hm.util.ResponseBundleForConf;
import com.hm.util.ResponseBundleForInfo;
import com.hm.util.Validate;

@Service
@Transactional
public class McMessageService implements IMcMessageService {

	private final Logger logger = LoggerFactory.getLogger(McMessageService.class);

	@Autowired
	IMcTemplateDetailService mcTemplateDetailService;
	@Autowired
	IMcBasMessageService basMcMessageService;
	@Autowired
	IMcBasEmailService basMcEmailService;
	@Autowired
	SMSXfireClient smsXfireClient;

	@Autowired
	private IMcBasImoService basMcImoService;

	@Autowired
	private IMcBasImoFailService basMcImoFailService;

	@Autowired
	private IMcBasEmailFailService basMcEmailFailService;

	@Autowired
	private IMcBasMessageFailService basMessageFailService;

	@Autowired
	private IMcBasLogService basLogService;

	@Autowired
	private IMcBasSystemService basSystemService;

	@Autowired
	private IMcSysTemplateService sysTemplateService;

	@Autowired
	private IMcTemplateService mcTemplateService;

	/**
	 * 消息中心_短信发送
	 * 
	 * @param mcParametersList
	 *            消息发送内容集合
	 * @return key 返回json串，标注结果
	 * 
	 **/
	public BaseOutEntity sendMcMessage(ArrayList<BasMcParameters> mcParametersList) {

		/******************* 1、判断传入参数是否正确 *******************/
		if (mcParametersList == null || mcParametersList.size() == 0) {// 调用消息中心入参错误

			// 返回输出信息
			BaseOutEntity baseOutEntity = new BaseOutEntity(ResponseBundleForInfo.getStringCode("code.global.error.param"), ResponseBundleForInfo.get("msg.global.error.param"), mcParametersList);
			// 记录日志、插入日志表信息、插入错误表信息
			insertLogInfo(null, null, baseOutEntity.toString());
			return baseOutEntity;
		}

		for (BasMcParameters basMcParameters : mcParametersList) {
			String messageType = basMcParameters.getMessageType();
			String key = ResponseBundleForConf.getStringCode("SMS_KEY") == null ? "" : ResponseBundleForConf.getStringCode("SMS_KEY");
			String sysId = basMcParameters.getSystemId() == null ? "" : basMcParameters.getSystemId();
			String sysPw = basMcParameters.getSystemPw() == null ? "" : basMcParameters.getSystemPw();
			String templateCode = basMcParameters.getTemplateCode() == null ? "" : basMcParameters.getTemplateCode();

			/******************* 2、判断传入系统编号和密码是否正确 *******************/

			String sysPwFlag = checkSysIdAndPw(sysId, sysPw, messageType, basMcParameters);
			if (!ResponseBundleForInfo.getStringCode("code.global.success").equals(sysPwFlag)) {
				String msg = "";

				if (ResponseBundleForInfo.getStringCode("code.global.error.sysId").equals(sysPwFlag)) {

					msg = ResponseBundleForInfo.getStringCode("msg.global.error.sysId");// 错误信息

				} else if (ResponseBundleForInfo.getStringCode("code.global.error.sysPw").equals(sysPwFlag)) {

					msg = ResponseBundleForInfo.getStringCode("msg.global.error.sysPw");// 错误信息
				}

				// 返回输出信息
				BaseOutEntity baseOutEntity = new BaseOutEntity(sysPwFlag, msg, mcParametersList);
				// 记录日志、插入日志表信息、插入错误表信息
				insertLogInfo(basMcParameters, messageType, baseOutEntity.toString());
				return baseOutEntity;
			}

			/******************* 3、判断系统是否配置模版 *******************/

			String temFlag = checkAllotTemplate(sysId, messageType, templateCode, basMcParameters);

			if (!ResponseBundleForInfo.getStringCode("code.global.success").equals(temFlag)) {
				String msg = "";
				if (ResponseBundleForInfo.getStringCode("code.global.error.sysTem").equals(temFlag)) {

					msg = ResponseBundleForInfo.getStringCode("msg.global.error.sysTem");// 错误信息

				} else if (ResponseBundleForInfo.getStringCode("code.global.error.temCode").equals(temFlag)) {

					msg = ResponseBundleForInfo.getStringCode("msg.global.error.temCode");// 错误信息
				}

				BaseOutEntity baseOutEntity = new BaseOutEntity(temFlag, msg, mcParametersList);
				// 记录日志、插入日志表信息、插入错误表信息
				insertLogInfo(basMcParameters, messageType, baseOutEntity.toString());
				// 返回输出信息
				return baseOutEntity;
			}

			/******************* 4、判断接收方式是否为空 *******************/
			if (basMcParameters.getReceiver() == null || basMcParameters.getReceiver().size() == 0) {

				BaseOutEntity baseOutEntity = new BaseOutEntity(temFlag, ResponseBundleForInfo.getStringCode("msg.global.error.receiverNull"), mcParametersList);
				// 记录日志、插入日志表信息、插入错误表信息
				insertLogInfo(basMcParameters, messageType, baseOutEntity.toString());
				// 返回输出信息
				return baseOutEntity;
			}

			/******************* 5、判断接收格式是否正确 *******************/
			boolean isFlag = true;
			if (McConstantUtil.MESSAGETYPE_DX.equals(messageType) || McConstantUtil.MESSAGETYPE_YJ.equals(messageType) || McConstantUtil.MESSAGETYPE_IMO.equals(messageType)) {
				isFlag = checkReceiver(messageType, basMcParameters.getReceiver());
				if (!isFlag) {

					BaseOutEntity baseOutEntity = new BaseOutEntity(ResponseBundleForInfo.getStringCode("code.global.error.receiverCheck"), ResponseBundleForInfo.getStringCode("msg.global.error.receiverCheck"), mcParametersList);
					// 记录日志、插入日志表信息、插入错误表信息
					insertLogInfo(basMcParameters, messageType, baseOutEntity.toString());
					// 返回输出信息
					return baseOutEntity;
				}
			}

			/******************* 6、判断模版编码是否为空 *******************/
			if (StringUtils.isBlank(templateCode)) {

				BaseOutEntity baseOutEntity = new BaseOutEntity(ResponseBundleForInfo.getStringCode("code.global.error.temMNull"), ResponseBundleForInfo.getStringCode("msg.global.error.temMNull"), mcParametersList);
				// 记录日志、插入日志表信息、插入错误表信息
				insertLogInfo(basMcParameters, messageType, baseOutEntity.toString());
				// 返回输出信息
				return baseOutEntity;
			}

			/******************* 7、判断模版是否存在 *******************/
			// 根据模板code,type查询出模板详细信息，主要使用参数为模板标题和模板内容
			McTemplateDetail mcTemplateDetail = mcTemplateDetailService.getByTemplateEncodeAndTypeId(templateCode, Converts.StrToLong(messageType));

			if (mcTemplateDetail == null) {

				BaseOutEntity baseOutEntity = new BaseOutEntity(ResponseBundleForInfo.getStringCode("code.global.error.temDNull"), ResponseBundleForInfo.getStringCode("msg.global.error.temDNull"), mcParametersList);
				// 记录日志、插入日志表信息、插入错误表信息
				insertLogInfo(basMcParameters, messageType, baseOutEntity.toString());
				// 返回输出信息
				return baseOutEntity;
			}

			/******************* 8、判断模版是否启用 *******************/
			// 判断模板是否启用
			if (mcTemplateDetail.getTemplateStatus() != 1) {// 2：禁用；1：启用；默认2禁用

				BaseOutEntity baseOutEntity = new BaseOutEntity(ResponseBundleForInfo.getStringCode("code.global.error.temStatusN"), ResponseBundleForInfo.getStringCode("msg.global.error.temStatusN"), mcParametersList);
				// 记录日志、插入日志表信息、插入错误表信息
				insertLogInfo(basMcParameters, messageType, baseOutEntity.toString());
				// 返回输出信息
				return baseOutEntity;
			}

			String templateTitle = mcTemplateDetail.getTemplateTitle() == null ? "" : mcTemplateDetail.getTemplateTitle();
			String templateContent = mcTemplateDetail.getTemplateContent();
			Map<String, String> parametersMap = basMcParameters.getParametersMap();
			Date sendTime = null;
			if (!basMcParameters.getSendTime().isEmpty()) {
				Converts.StrToDate(basMcParameters.getSendTime());
			}
			if (McConstantUtil.MESSAGETYPE_YJ.equals(messageType) || McConstantUtil.MESSAGETYPE_IMO.equals(messageType)) {
				templateTitle = getContent(templateTitle, parametersMap);
			}
			String content = getContent(templateContent, parametersMap);

			if (McConstantUtil.MESSAGETYPE_DX.equals(messageType)) {
				if (basMcParameters.getReceiver() != null && basMcParameters.getReceiver().size() > 0) {
					for (String str : basMcParameters.getReceiver()) {
						McBasMessage basMcMessage = new McBasMessage(str, content, key, 1L, basMcParameters.getGrpId(), basMcParameters.getmId(), basMcParameters.getsId(), basMcParameters.getTaskType(), sendTime, new Date(), null);
						basMcMessageService.create(basMcMessage);
					}
				}
			} else if (McConstantUtil.MESSAGETYPE_YJ.equals(messageType)) {
				if (basMcParameters.getReceiver() != null && basMcParameters.getReceiver().size() > 0) {
					for (String str : basMcParameters.getReceiver()) {
						McBasEmail basMcEmail = new McBasEmail(str, templateTitle, content, key, 1L, basMcParameters.getGrpId(), basMcParameters.getTaskType(), sendTime, new Date(), null);
						basMcEmailService.create(basMcEmail);
					}
				}
			} else if (McConstantUtil.MESSAGETYPE_IMO.equals(messageType)) {// IMO
				// 获取接收人
				ArrayList<String> receiverList = basMcParameters.getReceiver();
				// 用户存放接收人的uid
				ArrayList<String> uidList = new ArrayList<String>();
				for (String receiver : receiverList) {
					// 调用个接口获取员工信息 得到的是json数组
					// 这里应该加一个判断，判断是json还是json数组
					// JSONArray receiverJson =
					// new JSONArray();
					String receiverInfo = basMcImoService.getUserInfoByAccounts(receiver);
					System.out.println("员工信息： " + receiverInfo);
					JSONArray receiverJson = JSONArray.fromObject(receiverInfo);// 有错
					String name = null;
					for (int i = 0; i < receiverJson.size(); i++) {
						JSONObject ob = (JSONObject) receiverJson.get(i);
						if (ob.has("uid")) {
							name = ob.getString("uid");
						}
						System.out.println("员工编号：" + name);
					}
					// 判断接收人是否为空，如果不为空加入到发送人中
					if (!StringUtils.isBlank(name)) {
						uidList.add(name);
					}
					System.out.println("员工编号集合：" + uidList.toString());
				}

				// 封装IMO实体
				McBasImo basImo = new McBasImo(Converts.strArrayToStr(receiverList), Converts.strArrayToStr(uidList), templateTitle, content, key, 1L, basMcParameters.getGrpId(), sendTime, new Date(), null);// 获取传递过来的参数封装到IMO实体类中

				basMcImoService.create(basImo);// 将数据插入到IMO表中
			}
		}
		return new BaseOutEntity(ResponseBundleForInfo.getStringCode("code.global.success"), ResponseBundleForInfo.getStringCode("msg.global.success"), mcParametersList);
	}

	/**************************** 公共方法start/左鑫鹏20141127 *****************************/

	/**
	 * 验证消息接收人格式
	 * 
	 */
	public boolean checkReceiver(String messageType, ArrayList<String> receiverList) {

		boolean flag = false;
		if (McConstantUtil.MESSAGETYPE_DX.equals(messageType)) {
			for (String str : receiverList) {
				flag = Validate.isMobile(str);
				if (!flag) {
					return flag;
				}
			}
		} else if (McConstantUtil.MESSAGETYPE_YJ.equals(messageType)) {
			for (String str : receiverList) {
				flag = Validate.IsValidEmail(str);
				if (!flag) {
					return flag;
				}
			}
		} else if (McConstantUtil.MESSAGETYPE_IMO.equals(messageType)) {// 校验IMO
			for (String str : receiverList) {
				flag = Validate.isEnglishNumber(str);
				if (!flag) {
					return flag;
				}
			}
		}
		return flag;
	}

	/**
	 * getContent 消息发送获取模板内容
	 * 
	 * @param subject
	 *            消息模版标题/内容
	 * @param parametersMap
	 *            消息模板参数集合
	 * @return 模板内容
	 */
	public String getContent(String subject, Map<String, String> parametersMap) {
		String content = "";
		try {
			/* 初始化运行时引擎， 默认初始化 */
			try {
				Velocity.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
				Velocity.init();
			} catch (Exception e) {
				e.printStackTrace();
			}

			/* 建立context， 并放入数据 */
			VelocityContext context = new VelocityContext();

			for (Map.Entry<String, String> entry : parametersMap.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				context.put(key, value);
			}
			/* 解析后数据的输出目标，java.io.Writer的子类 */
			StringWriter write = new StringWriter();
			/* 进行解析 */
			Velocity.evaluate(context, write, "", subject);
			content = write.toString();
		} catch (Exception e) {
			throw new RuntimeException("velocity evaluate error! detail [" + e.getMessage() + "]");
		}
		return content;
	}

	/**
	 * 消息中心_邮件发送
	 * 
	 */
	public int sendEmail(String receiver, String subject, String content) {
		int returnFlag = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", receiver);
		map.put("content", content);
		map.put("subject", subject);
		boolean status = MailSenderUtil.sendTextMail(map);

		for (int i = 0; i < 2; i++) {
			if (!status) {
				status = MailSenderUtil.sendTextMail(map);
			}
		}
		if (status) {
			returnFlag = 1;// 发送成功
		} else {
			returnFlag = 2;// 发送失败
		}
		return returnFlag;
	}

	/**************************** 公共方法end/左鑫鹏20141127 *****************************/

	/**
	 * 将错误信息插入到数据库中
	 * 
	 * @param mcParameters
	 *            传递过来的消息的内容
	 * @param messageType
	 *            传递消息的类型
	 * @param msg
	 *            错误原因
	 */
	public void insertFailInfo(BasMcParameters basMcParameters, String messageType, String msg) {
		// 还有获取标题没有完成，目前参数固定为空
		if (McConstantUtil.MESSAGETYPE_DX.equals(messageType)) {
			McBasMessageFail basMcMessageFail = new McBasMessageFail(Converts.strArrayToStr(basMcParameters.getReceiver()), basMcParameters.getContent(), basMcParameters.getKey(), 3L, basMcParameters.getGrpId(), basMcParameters.getmId(), basMcParameters.getsId(), basMcParameters.getTaskType(), msg,
					Converts.StrToDate(basMcParameters.getSendTime()), new Date(), null);
			basMessageFailService.create(basMcMessageFail);
		} else if (McConstantUtil.MESSAGETYPE_YJ.equals(messageType)) {
			McBasEmailFail basMcEmailFail = new McBasEmailFail(Converts.strArrayToStr(basMcParameters.getReceiver()), "", basMcParameters.getContent(), basMcParameters.getKey(), 3L, basMcParameters.getGrpId(), basMcParameters.getTaskType(), msg, Converts.StrToDate(basMcParameters.getSendTime()),
					new Date(), null);
			basMcEmailFailService.create(basMcEmailFail);
		} else if (McConstantUtil.MESSAGETYPE_IMO.equals(messageType)) {// 这里还需要修改。接收人uid
			McBasImoFail basImoFail = new McBasImoFail(Converts.strArrayToStr(basMcParameters.getReceiver()), Converts.strArrayToStr(basMcParameters.getReceiver()), "", basMcParameters.getContent(), basMcParameters.getKey(), 3L, basMcParameters.getGrpId(), msg, Converts.StrToDate(basMcParameters
					.getSendTime()), new Date(), null);
			basMcImoFailService.create(basImoFail);
		}
	}

	/**
	 * 根据传递的系统id和系统密码检验
	 * 
	 * @param sysId
	 *            系统id
	 * @param sysPw
	 *            系统密码
	 * @param messageType
	 *            消息类型
	 * @param basMcParameters
	 *            参数集合
	 * @return true系统ID、密码验证通过 false系统ID或者密码不正确
	 */
	public String checkSysIdAndPw(String sysId, String sysPw, String messageType, BasMcParameters basMcParameters) {

		List<McBasSystem> sysList = basSystemService.findByCode(sysId);

		/* 1、判断系统ID是否存在 */
		if (sysList == null || sysList.size() == 0) {
			return ResponseBundleForInfo.getStringCode("code.global.error.sysId");
		}

		/* 2、判断系统密码是否正确 */
		if (!sysList.get(0).getSystPw().equals(sysPw)) {
			return ResponseBundleForInfo.getStringCode("code.global.error.sysPw");
		}

		/* 3、系统ID、密码验证通过 */
		return ResponseBundleForInfo.getStringCode("code.global.success");
	}

	/**
	 * 此方法描述的是：根据系统名称验证是否分配模板
	 * 
	 * @author: ZUOXP
	 * @version: 2015年7月1日 下午2:45:03
	 */
	public String checkAllotTemplate(String sysId, String messageType, String tempCode, BasMcParameters basMcParameters) {

		/* 1、判断系统是否配置消息模版 */
		List<McSysTemplate> sysTemList = sysTemplateService.findBysystId(basSystemService.findByCode(sysId).get(0).getSystId());
		if (sysTemList == null || sysTemList.size() == 0) {
			return ResponseBundleForInfo.getStringCode("code.global.error.sysTem");
		}

		/* 2、判断传入模版是否存在 */
		List<McTemplate> templateList = mcTemplateService.findByTemplateEncode(tempCode);
		if (templateList == null || templateList.size() == 0) {
			return ResponseBundleForInfo.getStringCode("code.global.error.temCode");
		}

		/* 3、判断系统是否配置模版 */
		for (McSysTemplate sysTemplate : sysTemList) {// 循环系统模板表，就是给系统分配模板的中间表
			for (McTemplate template : templateList) {
				if (template.getTemplateId() == sysTemplate.getTemplateId()) {
					return ResponseBundleForInfo.getStringCode("code.global.success");
				}
			}
		}
		return ResponseBundleForInfo.getStringCode("code.global.error.temCode");
	}

	/**
	 * 此方法描述的是：记录日志、插入日志表信息、插入错误表信息
	 * 
	 * @author: ZUOXP
	 * @version: 2015年7月1日 下午3:49:39
	 */
	public void insertLogInfo(BasMcParameters basMcParameters, String messageType, String msg) {
		// 将错误的信息插入错误表中
		insertFailInfo(basMcParameters, messageType, msg);
		// 插入日志信息
		basLogService.insertBasMcLog(new McBasLog(msg));
		// 记录日志
		logger.info(msg);
	}
}
