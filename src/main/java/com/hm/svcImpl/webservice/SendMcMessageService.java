package com.hm.svcImpl.webservice;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.domain.comm.BasMcParameters;
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
import com.hm.svc.tem.IMcBasSystemService;
import com.hm.svc.tem.IMcSysTemplateService;
import com.hm.svc.tem.IMcTemplateDetailService;
import com.hm.svc.tem.IMcTemplateService;
import com.hm.svc.webservice.ISendMcMessageService;
import com.hm.util.CommonParam;
import com.hm.util.Converts;
import com.hm.util.MailSenderUtil;
import com.hm.util.Validate;

/**
 * 提供的被远程调用接口
 * <ul>
 * <li>1、开发日期：2014-11-26</li>
 * <li>2、开发时间：上午8:46:20</li>
 * <li>3、作        者： 王凯</li>
 * <li>4、类型名称：SendMcMessageService</li>
 * <li>5、类型意图：</li>
 * </ul>
 * 这样传递的参数必须为json数据
 */
@Service
@Transactional
public class SendMcMessageService implements ISendMcMessageService {
	
	@Autowired
	private IMcTemplateDetailService mcTemplateDetailService;
	
	@Autowired
	private IMcBasMessageService basMcMessageService;
	
	@Autowired
	private IMcBasEmailService basMcEmailService;
	
	@Autowired
	private SMSXfireClient smsXfireClient;
	
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
	
	CommonParam commparam = new CommonParam();
	
	/**
	 * webservice接口方法 改方法可以使用，传递的参数为json(按照格式进行传递)
	 * 方法描述：该方法将传递过来的json串先转化为JSONObject对象，并将JSONObject对象
	 * 			 转化为JSONArray，然后将JSONArray转为为McMessageService类中的发送消
	 * 			息的sendMcMessage()方法的集合参数，并调用完成服务
	 * @param mcParametersList
	 * @return 返回值为json字符串
	 */
	public String sendMcMessage(String mcParametersList) {
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		String str = null;
		
		if(mcParametersList != null && !"".equals(mcParametersList)){
		
			if(mcParametersList.startsWith("[") && mcParametersList.endsWith("]")){
				jsonArray = JSONArray.fromObject(mcParametersList); 		//传递的json串转化为JSONArray对象
			}else{
				 json  = JSONObject.fromObject(mcParametersList);	//将传递过来的json串转化为JSONObject
				 jsonArray = JSONArray.fromObject(json); 
			}
			
			@SuppressWarnings("unchecked")
			ArrayList<BasMcParameters> list = (ArrayList<BasMcParameters>) JSONArray.toCollection(jsonArray,  BasMcParameters.class);  //转化为list
			str = this.sendMcMessage(list);			//调用消息中心的消息发送方法  返回的已经是一个json字符串
			System.out.println(str);
			return str;
		}
		
		json.put("flag", 100);
		json.put("msg", "消息中心发送消息失败。调用接口sendMcMessage时，传入消息相关内容为空！");
		json.put("totalNum",0);
		json.put("succNum",0);
		json.put("failNum",0);
		return json.toString();
	}
	
	/**
	 * 消息中心_短信发送(可以在msg里面放每一条消息的信息)
	 * 
	 * @param mcParametersList
	 *            消息发送内容集合
	 * @return key 返回json串，标注结果
	 * 
	 **/
	public String sendMcMessage(ArrayList<BasMcParameters> mcParametersList) {

		int successNum = 0;
		int totalNum = 0;
		int failNum = 0;	//失败条数
		JSONObject json = new JSONObject();
//		CommonParam commparam = new CommonParam();
		
	//	ArrayList<Map<String,Object>> jsonList = new ArrayList<Map<String,Object>>();	用于处理返回数据为一条信息一条json串
		
		if (mcParametersList != null && mcParametersList.size() > 0) {
			//消息总条数
			totalNum = mcParametersList.size();

			for (BasMcParameters basMcParameters : mcParametersList) {
				String messageType = basMcParameters.getMessageType();
				String key = commparam.getString("SMS_KEY");
				String sysId = basMcParameters.getSystemId();
				String sysPw = basMcParameters.getSystemPw();
				String templateCode = basMcParameters.getTemplateCode();
				/*if(messageType.equals("4")){
					key = commparam.getString("SYNC_KEY");
				}*/
				
				/**
				 * 判断传入系统id和密码
				 * 首先判断是否存在这里系统，存在则判断系统的密码
				 * 
				 */
				boolean sysPwFlag = checkSysIdAndPw(sysId,sysPw,messageType,basMcParameters);
				
				if(sysPwFlag){//判断系统id和密码
					boolean temFlag = checkAllotTemplate(sysId,messageType,templateCode,basMcParameters);
					
					if(temFlag){
							if (basMcParameters.getReceiver() != null
						&& basMcParameters.getReceiver().size() > 0) {

					boolean isFlag = true;
					if (McConstantUtil.MESSAGETYPE_DX.equals(messageType)
							|| McConstantUtil.MESSAGETYPE_YJ.equals(messageType)
							|| McConstantUtil.MESSAGETYPE_IMO.equals(messageType)//判断消息的类型
							) {
						
						isFlag = checkReceiver(messageType,
								basMcParameters.getReceiver());
					}
					if (isFlag) {

						//String templateCode = basMcParameters.getTemplateCode();

						if (StringUtils.isNotBlank(templateCode)) {
							
							// 根据模板code,type查询出模板详细信息，主要使用参数为模板标题和模板内容
							McTemplateDetail mcTemplateDetail = mcTemplateDetailService.getByTemplateEncodeAndTypeId(templateCode, Converts.StrToLong(messageType));

							if(mcTemplateDetail != null){
								//判断模板是否启用
								if(mcTemplateDetail.getTemplateStatus() == 1){
									String templateTitle = mcTemplateDetail.getTemplateTitle() == null ? "" : mcTemplateDetail.getTemplateTitle();
									String templateContent = mcTemplateDetail.getTemplateContent();
									Map<String, String> parametersMap = basMcParameters.getParametersMap();
									Date sendTime = null;
									if(!basMcParameters.getSendTime().isEmpty()){
										Converts.StrToDate(basMcParameters.getSendTime());
									}
									if(McConstantUtil.MESSAGETYPE_YJ.equals(messageType)
											|| McConstantUtil.MESSAGETYPE_IMO.equals(messageType)){
										templateTitle = getContent(templateTitle, parametersMap);
									}
									String content = getContent(templateContent, parametersMap);
									
									if(McConstantUtil.MESSAGETYPE_DX.equals(messageType)){
										McBasMessage basMcMessage = new McBasMessage(Converts.strArrayToStr(basMcParameters.getReceiver()), content, key, 1L, 
												basMcParameters.getGrpId(), basMcParameters.getmId(), basMcParameters.getsId(), basMcParameters.getTaskType(), sendTime, new Date(), null);
										basMcMessageService.create(basMcMessage);
									}else if(McConstantUtil.MESSAGETYPE_YJ.equals(messageType)){
										McBasEmail basMcEmail = new McBasEmail(Converts.strArrayToStr(basMcParameters.getReceiver()), templateTitle, content, key, 1L, 
												basMcParameters.getGrpId(), basMcParameters.getTaskType(), sendTime, new Date(), null);
										basMcEmailService.create(basMcEmail);
									}else if(McConstantUtil.MESSAGETYPE_IMO.equals(messageType)){//IMO
										//获取接收人
										ArrayList<String>  receiverList = basMcParameters.getReceiver();
										//用户存放接收人的uid
										ArrayList<String> uidList = new ArrayList<String> ();
										for(String receiver:receiverList){
											//调用个接口获取员工信息 得到的是json数组
											//这里应该加一个判断，判断是json还是json数组
											//JSONArray receiverJson = new JSONArray();
											String receiverInfo = basMcImoService.getUserInfoByAccounts(receiver);
											System.out.println("员工信息： "+receiverInfo);
											JSONArray receiverJson = JSONArray.fromObject(receiverInfo);//有错
											String name = null;
											for (int i = 0; i < receiverJson.size(); i++) {
												 JSONObject ob  = (JSONObject) receiverJson.get(i);
												 if(ob.has("uid")){
													 name = ob.getString("uid");
												 }
												 System.out.println("员工编号：" + name);
											}
											//判断接收人是否为空，如果不为空加入到发送人中
											if (!StringUtils.isBlank(name)) {
												uidList.add(name);
											}
											System.out.println("员工编号集合：" + uidList.toString());
										}
										
										//封装IMO实体
										McBasImo basImo = new McBasImo(Converts.strArrayToStr(receiverList),Converts.strArrayToStr(uidList),templateTitle,content, key, 1L, 
												basMcParameters.getGrpId(),sendTime,new Date(), null);//获取传递过来的参数封装到IMO实体类中
										
										basMcImoService.create(basImo);//将数据插入到IMO表中
									}
									
									successNum++;
									/* 用于处理返回数据为一条信息一条json串
									Map<String,Object> map = new LinkedHashMap<String,Object>();
									map.put("flag", "true");
									map.put("code", "104");
									map.put("msg", "消息中心发送消息成功。");
									map.put("totalNum", totalNum);
									map.put("succNum", successNum);
									map.put("fail", failNum);
									jsonList.add(map);*/
								}else{
									String reason = "模板编码为：" + templateCode + "的模板没有启用！";
									insertFailInfo(basMcParameters,messageType,reason);//将错误的信息插入错误表中
									McBasLog log = new McBasLog(reason);//插入日志信息
									basLogService.insertBasMcLog(log);
									failNum++;
									
									continue;
									/*
									 用于处理返回数据为一条信息一条json串
									String reason = "模板编码为：" + templateCode + "的模板没有启用！";
									insertFailInfo(basMcParameters,messageType,reason);//将错误的信息插入错误表中
									McBasLog log = new McBasLog(reason);//插入日志信息
									basLogService.insertBasMcLog(log);
									
									Map<String,Object> map = new LinkedHashMap<String,Object>();
									map.put("flag", "false");
									map.put("code", "103");
									map.put("msg", "消息中心发送消息失败。模板编码为：" + templateCode + "的模板没有启用！");
									map.put("totalNum", totalNum);
									map.put("succNum", successNum);
									map.put("fail", failNum);
									jsonList.add(map);
									failNum++;
									continue;*/
								}
							}else {
								
								String reason = "模板编码为：" + templateCode + "对应模板编码为空！";
								insertFailInfo(basMcParameters,messageType,reason);//将错误的信息插入错误表中
								McBasLog log = new McBasLog(reason);//插入日志信息
								basLogService.insertBasMcLog(log);
								
								failNum++;
								continue;
								/*
								用于处理返回数据为一条信息一条json串
								Map<String,Object> map = new LinkedHashMap<String,Object>();
								map.put("flag", "false");
								map.put("code", "103");
								map.put("msg", "消息中心发送消息失败,模板编码对应类型编码为空！");
								map.put("totalNum", totalNum);
								map.put("succNum", successNum);
								map.put("fail", failNum);
								jsonList.add(map);*/
							}
						}else {
							String reason = "模板编码为空。";
							insertFailInfo(basMcParameters,messageType,reason);//将错误的信息插入错误表中
							McBasLog log = new McBasLog(reason);//插入日志信息
							basLogService.insertBasMcLog(log);
							
							failNum++;
							continue;
							
							/*
							用于处理返回数据为一条信息一条json串
							Map<String,Object> map = new LinkedHashMap<String,Object>();
							map.put("flag", "false");
							map.put("code", "103");
							map.put("msg", "模板编码为空。！");
							map.put("totalNum", totalNum);
							map.put("succNum", successNum);
							map.put("fail", failNum);
							jsonList.add(map);*/
							
							/*json.put("flag", "false");
							json.put("code", "103");
							json.put("msg", "消息中心发送消息失败，模板编码为空。");
							json.put("totalNum", totalNum);
							json.put("succNum", successNum);
							return json.toString();*/
						}
					} else {//测试可用
						String value = "";
						if (McConstantUtil.MESSAGETYPE_DX.equals(messageType)) {
							value = "短信";
						} else if (McConstantUtil.MESSAGETYPE_YJ.equals(messageType)) {
							value = "邮件";
						}else if(McConstantUtil.MESSAGETYPE_IMO.equals(messageType)){
							value = "IMO";
						}
						
						String reason = "消息中心调用sendMessage接口发送" + value + "消息时，接收人格式不正确。";
						insertFailInfo(basMcParameters,messageType,reason);//将错误的信息插入错误表中
						McBasLog log = new McBasLog(reason);//插入日志信息
						basLogService.insertBasMcLog(log);
						
						failNum++;
						continue;
						
						/*
					       用于处理返回数据为一条信息一条json串
					    Map<String,Object> map = new LinkedHashMap<String,Object>();
						map.put("flag", "false");
						map.put("code", "103");
						map.put("msg",  "消息中心调用sendMessage接口发送" + value + "消息时，接收人格式不正确。");
						map.put("totalNum", totalNum);
						map.put("succNum", successNum);
						map.put("fail", failNum);
						jsonList.add(map);*/
						
						/*json.put("flag", "false");
						json.put("code", "102");
						json.put("msg", "消息中心调用sendMessage接口发送" + value + "消息时，接收人格式不正确。");
						json.put("totalNum", totalNum);
						json.put("succNum", successNum);
						return json.toString();*/
					}
				} else {
					String value = "";
					if (McConstantUtil.MESSAGETYPE_DX.equals(messageType)) {
						value = "发送短信的手机号码为空";
					} else if (McConstantUtil.MESSAGETYPE_YJ.equals(messageType)) {
						value = "发送邮件的邮箱地址为空";
					} else if (McConstantUtil.MESSAGETYPE_ZNX.equals(messageType)) {
						value = "发送站内信的登录账户名为空";
					} else {
						value = "发送IMO的登录账户名为空";
					}
					
					insertFailInfo(basMcParameters,messageType,value);//将错误的信息插入错误表中
					McBasLog log = new McBasLog(value);//插入日志信息
					basLogService.insertBasMcLog(log);
					
					failNum++;
					continue;
					
					/*
					用于处理返回数据为一条信息一条json串
					Map<String,Object> map = new LinkedHashMap<String,Object>();
					map.put("flag", "false");
					map.put("code", "101");
					map.put("msg",  "消息中心调用sendMessage接口发送消息时，" + value + "。");
					map.put("totalNum", totalNum);
					map.put("succNum", successNum);
					map.put("fail", failNum);
					jsonList.add(map);
					*/
					
					/*json.put("flag", "false");
					json.put("code", "101");
					json.put("msg", "消息中心调用sendMessage接口发送消息时，" + value + "。");
					json.put("totalNum", totalNum);
					json.put("succNum", successNum);
					return json.toString();*/
				}
					}else{
						String reason = sysId + "系统未配置"+templateCode+"模板";
						insertFailInfo(basMcParameters,messageType,reason);
						McBasLog log = new McBasLog(reason);
						basLogService.insertBasMcLog(log);
						
						failNum++;
						continue;
					}
				}else{
					failNum++;
					continue;
				}
			}
		} else {
			//测试成功
			json.put("flag", "false");
			json.put("code", "100");
			json.put("msg", "消息中心发送消息失败。调用接口sendMcMessage时，传入消息相关内容为空！");
			json.put("totalNum", totalNum);
			json.put("succNum", successNum);
			
			String reason = "消息中心发送消息失败。调用接口sendMcMessage时，传入消息相关内容为空！";
			insertFailInfo(null,null,reason);//将错误的信息插入错误表中
			McBasLog log = new McBasLog(reason);//插入日志信息
			basLogService.insertBasMcLog(log);
			
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("flag", "false");
			map.put("code", "100");
			map.put("msg",  "消息中心发送消息失败。调用接口sendMcMessage时，传入消息相关内容为空！");
			map.put("totalNum", totalNum);
			map.put("succNum", successNum);
			map.put("fail", failNum);
			jsonList.add(map);*/
			
			return json.toString();
			
		}
		
		//全部在执行完后返回
		if(failNum > 0 && successNum > 0){
			json.put("flag", "false");
			json.put("code", "104");
			json.put("msg", "消息中心部分消息发送成功！");
			json.put("totalNum", totalNum);
			json.put("succNum", successNum);
			json.put("fail", failNum);
			return json.toString();
		}
		if(failNum > 0 && successNum == 0){
			json.put("flag", "false");
			json.put("code", "104");
			json.put("msg", "消息中心消息发送失败！");
			json.put("totalNum", totalNum);
			json.put("succNum", successNum);
			json.put("fail", failNum);
			return json.toString();	
		}
		
		json.put("flag", "true");
		json.put("code", "104");
		json.put("msg", "消息中心发送消息成功。");
		json.put("totalNum", totalNum);
		json.put("succNum", successNum);
		json.put("failNum", failNum);
		return json.toString();
		
		/*Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("flag", "false");
		map.put("code", "104");
		map.put("msg", "消息中心发送消息成功");
		map.put("totalNum", totalNum);
		map.put("succNum", successNum);
		jsonList.add(map);*/
		
		//return JSONArray.fromObject(jsonList).toString(); 用于处理返回数据为一条信息一条json串
	}

	/**************************** 公共方法start/左鑫鹏20141127 *****************************/

	/**
	 * 验证消息接收人格式
	 * 
	 */
	public boolean checkReceiver(String messageType, ArrayList<String> receiverList) {
		boolean flag = false;
		if (messageType.equals("1")) {
			for (String str : receiverList) {
				flag = Validate.isMobile(str);
				if (!flag) {
					return flag;
				}
			}
		} else if (messageType.equals("2")) {
			for (String str : receiverList) {
				flag = Validate.IsValidEmail(str);
				if (!flag) {
					return flag;
				}
			}
		}else if(messageType.equals("4")){//校验IMO
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
	 * @param subject 消息模版标题/内容
	 * @param parametersMap 消息模板参数集合
	 * @return	模板内容
	*/
	public String getContent(String subject, Map<String, String> parametersMap) {
		String content = "";
		try {
			/* 初始化运行时引擎， 默认初始化 */
			Velocity.init();

			/* 建立context， 并放入数据 */
			VelocityContext context = new VelocityContext();
			
			for(Map.Entry<String, String> entry : parametersMap.entrySet()){   
			   System.out.println(entry.getKey()+"--->"+entry.getValue());  
			   String key = entry.getKey();
			   String value = entry.getValue(); 
			   context.put(key, value);
			}
			/* 解析后数据的输出目标，java.io.Writer的子类 */
			StringWriter write = new StringWriter();
			/* 进行解析 */
			Velocity.evaluate(context, write, "mc", subject);
			content = write.toString();
		} catch (Exception e) {
			e.printStackTrace();
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
		if(status){
			returnFlag = 1;//发送成功
		}else{
			returnFlag = 2;//发送失败
		}
		return returnFlag;
	}
	
	/**************************** 公共方法end/左鑫鹏20141127 *****************************/
	
	/**
	 * 将错误信息插入到数据库中
	 * @param mcParameters  传递过来的消息的内容
	 * @param messageType	传递消息的类型
	 * @param reason		错误原因
	 */
	public void insertFailInfo(BasMcParameters basMcParameters,String messageType,String reason){
		//还有获取标题没有完成，目前参数固定为空
		if(McConstantUtil.MESSAGETYPE_DX.equals(messageType)){
			McBasMessageFail basMcMessageFail = new McBasMessageFail(Converts.strArrayToStr(basMcParameters.getReceiver()), 
					basMcParameters.getContent(), commparam.getString("SMS_KEY"), 3L, basMcParameters.getGrpId(), //有问题   commparam.getString("SMS_KEY")
					basMcParameters.getmId(), basMcParameters.getsId(), basMcParameters.getTaskType(),reason,
					Converts.StrToDate(basMcParameters.getSendTime()), new Date(), null);
			basMessageFailService.create(basMcMessageFail);
		}else if(McConstantUtil.MESSAGETYPE_YJ.equals(messageType)){
			McBasEmailFail basMcEmailFail = new McBasEmailFail(Converts.strArrayToStr(basMcParameters.getReceiver()),"",
					basMcParameters.getContent(),commparam.getString("SMS_KEY"),3L,basMcParameters.getGrpId(),basMcParameters.getTaskType()
					,reason,Converts.StrToDate(basMcParameters.getSendTime()),new Date(),null);
			basMcEmailFailService.create(basMcEmailFail);
		}else if(McConstantUtil.MESSAGETYPE_IMO.equals(messageType)){//这里还需要修改。接收人uid
			McBasImoFail basImoFail = new McBasImoFail(Converts.strArrayToStr(basMcParameters.getReceiver()),Converts.strArrayToStr(basMcParameters.getReceiver()),"",
					basMcParameters.getContent(),commparam.getString("SYNC_KEY"),3L,basMcParameters.getGrpId()
					,reason,Converts.StrToDate(basMcParameters.getSendTime()),new Date(),null);
			basMcImoFailService.create(basImoFail);
		}
	}

	/**
	 * 根据传递的系统id和系统密码检验
	 * @param sysId 系统id
	 * @param sysPw 系统密码
	 * @param messageType 消息类型
	 * @param basMcParameters 参数集合
	 * @return
	 */
	public boolean checkSysIdAndPw(String sysId,String sysPw,String messageType,BasMcParameters basMcParameters){
		boolean flag = false;
		List<McBasSystem> sysList = basSystemService.findByCode(sysId);
		McBasSystem basSystem = null;
		if(sysList != null  && sysList.size() == 1){
			 basSystem = sysList.get(0);
			 //如果密码正确返回true
			 if(basSystem.getSystPw().equals(sysPw)){
				 flag = true;
				 return flag;
			 }
			 String reason = sysId + "系统密码错误";
			 insertFailInfo(basMcParameters,messageType,reason);
			//插入日志信息
			 McBasLog log = new McBasLog(reason);
			 basLogService.insertBasMcLog(log);
			 return flag;
		}else{
			String reason = sysId + "系统不存在";
			insertFailInfo(basMcParameters,messageType,reason);
			McBasLog log = new McBasLog(reason);
			basLogService.insertBasMcLog(log);
			return flag;//系统为空返回错误
		}
	}
	
	/**
	 * 根据系统名称验证是否分配模板
	 * @param sysId
	 * @return
	 */
	public boolean checkAllotTemplate(String sysId,String messageType,String tempCode,BasMcParameters basMcParameters){
		boolean flag = false;
		List<McBasSystem> sysList = basSystemService.findByCode(sysId);
		McBasSystem basSystem = null;
		if(sysList != null  && sysList.size() == 1){
			basSystem = sysList.get(0);
			List<McSysTemplate> sysTemList = sysTemplateService.findBysystId(basSystem.getSystId());
			if(sysTemList != null && sysTemList.size() > 0 ){
				for(McSysTemplate sysTemplate: sysTemList){//循环系统模板表，就是给系统分配模板的中间表
					//根据模板id去查询是否存在
					List<McTemplate> templateList = mcTemplateService.findByTemplateEncode(tempCode);
					if(templateList != null && templateList.size() > 0){
						for(McTemplate template:templateList){
							if(template.getTemplateId() == sysTemplate.getTemplateId())
								flag = true;
						}
					}
				}
				return flag;
			}
		}
		//插入错误信息和日志信息
		/*String reason = sysId + "系统未配置"+tempCode+"模板";
		insertFailInfo(basMcParameters,messageType,reason);
		McBasLog log = new McBasLog(reason);
		basLogService.insertBasMcLog(log);*/
		return flag;
	}
}