package com.hm.svcImpl.mc;

public class McBasMsgParameters {

	// 消息发送人
	private String sender = "";

	// 会员ID
	private Long accountId = null;

	// 是否动态消息标题 0：否；1：是
	private String isDynamicSubject = "0";

	// 接收人对象：1、外部用户;2、内部用户;3、合作伙伴
	private String receiverType = "";

	// 获取 类型：1-业务提醒、2-系统公告、3-其它信息, 类型long 默认值:(long)1
	private long mgType = 1L;

	// 优先级重要性：1、重要;2、普通
	private String priority = "2";

	// 系统ID
	private String systemId = "11";

	// 消息发送是否循环（1：循环；2：不循环只执行一遍）
	private String isCircle = "2";

	//
	private String function = "sendSystemMessage";

	//
	private String siteId = "1";

	//
	private String siteKey = "123456";

	//
	private String msgId = "1";

	//
	private String sendUserId = "1000000000000000";

	//
	private String title = "系统消息";

	//
	private String messageType = "74";

	//
	private String btnName = "btnMessage";

	//
	private String recordId = "1";

	//
	private String msgAppId = "2";

	//
	private String saveOffline = "true";

	// 消息发送开始时间
	private String sendStartTime = "";

	// 消息发送结束时间
	private String sendEndTime = "";

	// 发送时间
	private String sendTime = "";

	// 结束时间
	private String endTime = "";

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getIsCircle() {
		return isCircle;
	}

	public void setIsCircle(String isCircle) {
		this.isCircle = isCircle;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	// public String getSubject() {
	// return subject;
	// }
	//
	// public void setSubject(String subject) {
	// this.subject = subject;
	// }

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteKey() {
		return siteKey;
	}

	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getMsgAppId() {
		return msgAppId;
	}

	public void setMsgAppId(String msgAppId) {
		this.msgAppId = msgAppId;
	}

	public String getSaveOffline() {
		return saveOffline;
	}

	public void setSaveOffline(String saveOffline) {
		this.saveOffline = saveOffline;
	}

	public String getSendStartTime() {
		return sendStartTime;
	}

	public void setSendStartTime(String sendStartTime) {
		this.sendStartTime = sendStartTime;
	}

	public String getSendEndTime() {
		return sendEndTime;
	}

	public void setSendEndTime(String sendEndTime) {
		this.sendEndTime = sendEndTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public long getMgType() {
		return mgType;
	}

	public void setMgType(long mgType) {
		this.mgType = mgType;
	}

	public String getIsDynamicSubject() {
		return isDynamicSubject;
	}

	public void setIsDynamicSubject(String isDynamicSubject) {
		this.isDynamicSubject = isDynamicSubject;
	}

}
