package com.hm.svc.mc;

import java.util.ArrayList;
import java.util.Map;

import com.hm.domain.comm.BasMcParameters;
import com.hm.domain.comm.BaseOutEntity;

/**
 * 信息中心接口
 * 
 * 左鑫鹏 2014年11月21日14:42:20
 * 
 * */

public interface IMcMessageService {

	/**
	 * 消息中心_消息发送
	 * 
	 * @param mcParametersList
	 *            消息发送内容集合
	 * @return key 返回json串，标注结果
	 * 
	 **/
	public BaseOutEntity sendMcMessage(ArrayList<BasMcParameters> mcParameters);

	/**
	 * getContent 消息发送获取模板内容
	 * 
	 * @param subject
	 *            消息模版标题/内容
	 * @param parametersMap
	 *            消息模板参数集合
	 * @return 模板内容
	 **/
	public String getContent(String subject, Map<String, String> parametersMap);

	/**
	 * 消息中心_邮件发送
	 * 
	 */
	public int sendEmail(String receiver, String subject, String content);

}
