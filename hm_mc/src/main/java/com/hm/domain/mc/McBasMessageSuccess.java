package com.hm.domain.mc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.aggrepoint.jpa.CreatedDate;
import com.aggrepoint.jpa.UpdatedDate;

/**
 * 短信表（成功）实体类
 * 
 * @author ZUOXP
 * 
 */
@Entity
@Table(name = "ZT_MC_BAS_MESSAGE_SUCCESS")
public class McBasMessageSuccess implements Serializable {
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
	 * JVM会把传来的字节流中的serialVersionUID与本地相应实体
	 * （类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2014112817154558766L;

	public McBasMessageSuccess() {
	}

	public McBasMessageSuccess(String receiver, String content, String key, long status, String grpId, String mId, String sId, String taskType, Date sendTime, Date createTime, Date updateTime) {
		this.receiver = receiver;
		this.content = content;
		this.key = key;
		this.status = status;
		this.grpId = grpId;
		this.mId = mId;
		this.sId = sId;
		this.taskType = taskType;
		this.sendTime = sendTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/**
	 * 获取 短信ID, 类型long 默认值:0
	 */
	private long messageId;

	/**
	 * 获取 短信接收人, 类型String 默认值:""
	 */
	private String receiver;

	/**
	 * 获取 短信内容, 类型String 默认值:""
	 */
	private String content;

	/**
	 * 获取 key值, 类型String 默认值:""
	 */
	private String key;

	/**
	 * 获取 发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 */
	private long status;

	/**
	 * 获取 组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 */
	private String grpId;

	/**
	 * 获取 M_ID, 类型String 默认值:""
	 */
	private String mId;

	/**
	 * 获取 发送人ID, 类型String 默认值:""
	 */
	private String sId;

	/**
	 * 获取 任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 */
	private String taskType;

	/**
	 * 获取 发送开始时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sendTime;

	/**
	 * 获取 创建时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	/**
	 * 获取 更新时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;

	/**
	 * 获取 短信ID, 类型long 默认值:0
	 * 
	 * @return 短信ID, 类型long 默认值:0
	 */
	@Column(name = "MESSAGE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_MESSAGE_SUCCESS", allocationSize = 1)
	public long getMessageId() {
		return this.messageId;
	}

	/**
	 * 设置 短信ID, 类型long 默认值:0
	 * 
	 * @param sMessageId
	 *            短信ID, 类型long 默认值:0
	 */
	public void setMessageId(long sMessageId) {
		this.messageId = sMessageId;
	}

	/**
	 * 获取 短信接收人, 类型String 默认值:""
	 * 
	 * @return 短信接收人, 类型String 默认值:""
	 */
	@Column(name = "RECEIVER", length = 256)
	public String getReceiver() {
		return this.receiver;
	}

	/**
	 * 设置 短信接收人, 类型String 默认值:""
	 * 
	 * @param sReceiver
	 *            短信接收人, 类型String 默认值:""
	 */
	public void setReceiver(String sReceiver) {
		this.receiver = sReceiver;
	}

	/**
	 * 获取 短信内容, 类型String 默认值:""
	 * 
	 * @return 短信内容, 类型String 默认值:""
	 */
	@Column(name = "CONTENT", length = 2000)
	public String getContent() {
		return this.content;
	}

	/**
	 * 设置 短信内容, 类型String 默认值:""
	 * 
	 * @param sContent
	 *            短信内容, 类型String 默认值:""
	 */
	public void setContent(String sContent) {
		this.content = sContent;
	}

	/**
	 * 获取 key值, 类型String 默认值:""
	 * 
	 * @return key值, 类型String 默认值:""
	 */
	@Column(name = "KEY", length = 256)
	public String getKey() {
		return this.key;
	}

	/**
	 * 设置 key值, 类型String 默认值:""
	 * 
	 * @param sKey
	 *            key值, 类型String 默认值:""
	 */
	public void setKey(String sKey) {
		this.key = sKey;
	}

	/**
	 * 获取 发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 * 
	 * @return 发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 */
	@Column(name = "STATUS")
	public long getStatus() {
		return this.status;
	}

	/**
	 * 设置 发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 * 
	 * @param sStatus
	 *            发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 */
	public void setStatus(long sStatus) {
		this.status = sStatus;
	}

	/**
	 * 获取 组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 * 
	 * @return 组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 */
	@Column(name = "GRP_ID", length = 10)
	public String getGrpId() {
		return this.grpId;
	}

	/**
	 * 设置 组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 * 
	 * @param sGrpId
	 *            组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 */
	public void setGrpId(String sGrpId) {
		this.grpId = sGrpId;
	}

	/**
	 * 获取 M_ID, 类型String 默认值:""
	 * 
	 * @return M_ID, 类型String 默认值:""
	 */
	@Column(name = "M_ID", length = 10)
	public String getMId() {
		return this.mId;
	}

	/**
	 * 设置 M_ID, 类型String 默认值:""
	 * 
	 * @param sMId
	 *            M_ID, 类型String 默认值:""
	 */
	public void setMId(String sMId) {
		this.mId = sMId;
	}

	/**
	 * 获取 发送人ID, 类型String 默认值:""
	 * 
	 * @return 发送人ID, 类型String 默认值:""
	 */
	@Column(name = "S_ID", length = 10)
	public String getSId() {
		return this.sId;
	}

	/**
	 * 设置 发送人ID, 类型String 默认值:""
	 * 
	 * @param sSId
	 *            发送人ID, 类型String 默认值:""
	 */
	public void setSId(String sSId) {
		this.sId = sSId;
	}

	/**
	 * 获取 任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 * 
	 * @return 任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 */
	@Column(name = "TASK_TYPE", length = 10)
	public String getTaskType() {
		return this.taskType;
	}

	/**
	 * 设置 任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 * 
	 * @param sTaskType
	 *            任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 */
	public void setTaskType(String sTaskType) {
		this.taskType = sTaskType;
	}

	/**
	 * 获取 发送开始时间, 类型Date 默认值:new Date()
	 * 
	 * @return 发送开始时间, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEND_TIME")
	public Date getSendTime() {
		return this.sendTime;
	}

	/**
	 * 设置 发送开始时间, 类型Date 默认值:new Date()
	 * 
	 * @param sSendTime
	 *            发送开始时间, 类型Date 默认值:new Date()
	 */
	public void setSendTime(Date sSendTime) {
		this.sendTime = sSendTime;
	}

	/**
	 * 获取 创建时间, 类型Date 默认值:new Date()
	 * 
	 * @return 创建时间, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", updatable = false)
	@CreatedDate
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置 创建时间, 类型Date 默认值:new Date()
	 * 
	 * @param sCreateTime
	 *            创建时间, 类型Date 默认值:new Date()
	 */
	public void setCreateTime(Date sCreateTime) {
		this.createTime = sCreateTime;
	}

	/**
	 * 获取 更新时间, 类型Date 默认值:new Date()
	 * 
	 * @return 更新时间, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	@UpdatedDate
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置 更新时间, 类型Date 默认值:new Date()
	 * 
	 * @param sUpdateTime
	 *            更新时间, 类型Date 默认值:new Date()
	 */
	public void setUpdateTime(Date sUpdateTime) {
		this.updateTime = sUpdateTime;
	}

}
