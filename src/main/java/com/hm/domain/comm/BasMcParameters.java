package com.hm.domain.comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class BasMcParameters implements Serializable {

	private static final long serialVersionUID = 2014112415475254299L;

	public BasMcParameters() {

	}

	// 发送信息的时候返回的一个消息唯一性标示，可以通过回调函数进行查询消息内容
	private String key = "";

	// 模板编码
	private String templateCode = "";

	// 模板参数
	private Map<String, String> parametersMap;

	// 短信接收号码多个电话可按照”,”分隔
	private ArrayList<String> receiver = new ArrayList<String>();

	// 短信内容
	private String content = "";

	// 需要延迟发送的时间，不传为立即发送短信
	private String sendTime = "";

	// 发送状态（1：待发送；2：已发送）
	private long status = 1L;

	// 组ID（网站、ivalue、比酷）
	private String grpId = "";

	// 用户ID
	private String mId = "";

	// 发送人ID
	private String sId = "";

	// 任务类型(1.周期性任务，2.一次性任务)
	private String taskType = "";

	// 消息类型（1：短信；2：邮件；3：站内信；4：IM）
	private String messageType = "";

	// 系统ID，校验id是否合法
	private String systemId = "";

	// 系统密码，校验密码是否合法
	private String systemPw = "";

	// 连接URl
	private String linkUrl = "";

	/**
	 * 获取key
	 * 
	 * @return key key
	 */

	public String getKey() {
		return key;
	}

	/**
	 * 设置key
	 * 
	 * @param key
	 *            key
	 */

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取receiver
	 * 
	 * @return receiver receiver
	 */

	public ArrayList<String> getReceiver() {
		return receiver;
	}

	/**
	 * 设置receiver
	 * 
	 * @param receiver
	 *            receiver
	 */

	public void setReceiver(ArrayList<String> receiver) {
		this.receiver = receiver;
	}

	/**
	 * 获取content
	 * 
	 * @return content content
	 */

	public String getContent() {
		return content;
	}

	/**
	 * 设置content
	 * 
	 * @param content
	 *            content
	 */

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取sendTime
	 * 
	 * @return sendTime sendTime
	 */

	public String getSendTime() {
		return sendTime;
	}

	/**
	 * 设置sendTime
	 * 
	 * @param sendTime
	 *            sendTime
	 */

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 获取status
	 * 
	 * @return status status
	 */

	public long getStatus() {
		return status;
	}

	/**
	 * 设置status
	 * 
	 * @param status
	 *            status
	 */

	public void setStatus(long status) {
		this.status = status;
	}

	/**
	 * 获取grpId
	 * 
	 * @return grpId grpId
	 */

	public String getGrpId() {
		return grpId;
	}

	/**
	 * 设置grpId
	 * 
	 * @param grpId
	 *            grpId
	 */

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	/**
	 * 获取mId
	 * 
	 * @return mId mId
	 */

	public String getmId() {
		return mId;
	}

	/**
	 * 设置mId
	 * 
	 * @param mId
	 *            mId
	 */

	public void setmId(String mId) {
		this.mId = mId;
	}

	/**
	 * 获取sId
	 * 
	 * @return sId sId
	 */

	public String getsId() {
		return sId;
	}

	/**
	 * 设置sId
	 * 
	 * @param sId
	 *            sId
	 */

	public void setsId(String sId) {
		this.sId = sId;
	}

	/**
	 * taskType
	 * 
	 * @return the taskType
	 * @since 1.0.0
	 */

	public String getTaskType() {
		return taskType;
	}

	/**
	 * @param taskType
	 *            the taskType to set
	 */
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	/**
	 * 获取messageType
	 * 
	 * @return messageType messageType
	 */

	public String getMessageType() {
		return messageType;
	}

	/**
	 * 设置messageType
	 * 
	 * @param messageType
	 *            messageType
	 */

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * 获取systemId
	 * 
	 * @return systemId systemId
	 */

	public String getSystemId() {
		return systemId;
	}

	/**
	 * 设置systemId
	 * 
	 * @param systemId
	 *            systemId
	 */

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	/**
	 * 获取linkUrl
	 * 
	 * @return linkUrl linkUrl
	 */

	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * 设置linkUrl
	 * 
	 * @param linkUrl
	 *            linkUrl
	 */

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * 获取systemPw
	 * 
	 * @return systemPw systemPw
	 */

	public String getSystemPw() {
		return systemPw;
	}

	/**
	 * 设置systemPw
	 * 
	 * @param systemPw
	 *            systemPw
	 */

	public void setSystemPw(String systemPw) {
		this.systemPw = systemPw;
	}

	/**
	 * 获取templateCode
	 * 
	 * @return templateCode templateCode
	 */

	public String getTemplateCode() {
		return templateCode;
	}

	/**
	 * 设置templateCode
	 * 
	 * @param templateCode
	 *            templateCode
	 */

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	/**
	 * 获取parametersMap
	 * 
	 * @return parametersMap parametersMap
	 */

	public Map<String, String> getParametersMap() {
		return parametersMap;
	}

	/**
	 * 设置parametersMap
	 * 
	 * @param parametersMap
	 *            parametersMap
	 */

	public void setParametersMap(Map<String, String> parametersMap) {
		this.parametersMap = parametersMap;
	}

}
