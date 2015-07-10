package com.hm.svc.webservice;


/**
 * 信息中心接口利用xifre提供webservice服务
 * 
 * 左鑫鹏 2014年11月21日14:42:20
 * 
 * */

public interface ISendMcMessageService {

	/**
	 * 消息中心_消息发送
	 * 
	 * @param mcParametersList
	 *            消息发送内容集合
	 * @return key 返回json串，标注结果
	 * 
	 **/
	//public JSONObject sendMcMessage(ArrayList<BasMcParameters> mcParametersList);
	
	/**
	 * webservice接口方法
	 * @param mcParametersList(接收的参数必须为指定格式的json串) 消息发送内容集合
	 * @return 返回结果为json串,标注结果
	 */
	public String sendMcMessage(String mcParameters);
	
	
}
