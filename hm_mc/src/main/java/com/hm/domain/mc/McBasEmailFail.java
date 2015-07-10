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
 *消息中心_邮件表（失败）实体类
 *
 * @author ZUOXP
 *
 */
@Entity
@Table(name = "ZT_MC_BAS_EMAIL_FAIL")
public class McBasEmailFail implements Serializable
{
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2014120318591833982L;
	
	public McBasEmailFail() {
	}

	public McBasEmailFail(String receiver, String subject, String content, String key,
			long status, String grpId, String taskType, String errorMessage,
			Date sendTime, Date createTime, Date updateTime) {
		this.receiver = receiver;
		this.subject = subject;
		this.content = content;
		this.key = key;
		this.status = status;
		this.grpId = grpId;
		this.taskType = taskType;
		this.errorMessage = errorMessage;
		this.sendTime = sendTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/**
	* 获取 短信ID, 类型long 默认值:0
	 */
	private long maileId;

	/**
	* 获取 邮件接收人, 类型String 默认值:""
	 */
	private String receiver;

	/**
	* 获取 邮件主题, 类型String 默认值:""
	 */
	private String subject;

	/**
	* 获取 邮件内容, 类型String 默认值:""
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
	* 获取 任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 */
	private String taskType;

	/**
	* 获取 发送开始时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date sendTime;

	/**
	* 获取 错误信息, 类型String 默认值:""
	 */
	private String errorMessage;

	/**
	* 获取 创建时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;

	/**
	* 获取 更新时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;


	/**
	* 获取 短信ID, 类型long 默认值:0
	 *
	 * @return 短信ID, 类型long 默认值:0
	 */
	@Column(name = "MAILE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_EMAIL_FAIL", allocationSize = 1)
	public long getMaileId()
	{
		return this.maileId; 
	}
	/**
	 * 设置 短信ID, 类型long 默认值:0
	 *
	 * @param sMaileId
	 *            短信ID, 类型long 默认值:0
	 */
	public void setMaileId(long sMaileId)
	{
		this.maileId = sMaileId; 
	}


	/**
	* 获取 邮件接收人, 类型String 默认值:""
	 *
	 * @return 邮件接收人, 类型String 默认值:""
	 */
	@Column(name = "RECEIVER", length = 256)
	public String getReceiver()
	{
		return this.receiver; 
	}
	/**
	 * 设置 邮件接收人, 类型String 默认值:""
	 *
	 * @param sReceiver
	 *            邮件接收人, 类型String 默认值:""
	 */
	public void setReceiver(String sReceiver)
	{
		this.receiver = sReceiver; 
	}


	/**
	* 获取 邮件主题, 类型String 默认值:""
	 *
	 * @return 邮件主题, 类型String 默认值:""
	 */
	@Column(name = "SUBJECT", length = 256)
	public String getSubject()
	{
		return this.subject; 
	}
	/**
	 * 设置 邮件主题, 类型String 默认值:""
	 *
	 * @param sSubject
	 *            邮件主题, 类型String 默认值:""
	 */
	public void setSubject(String sSubject)
	{
		this.subject = sSubject; 
	}


	/**
	* 获取 邮件内容, 类型String 默认值:""
	 *
	 * @return 邮件内容, 类型String 默认值:""
	 */
	@Column(name = "CONTENT", length = 4000)
	public String getContent()
	{
		return this.content; 
	}
	/**
	 * 设置 邮件内容, 类型String 默认值:""
	 *
	 * @param sContent
	 *            邮件内容, 类型String 默认值:""
	 */
	public void setContent(String sContent)
	{
		this.content = sContent; 
	}


	/**
	* 获取 key值, 类型String 默认值:""
	 *
	 * @return key值, 类型String 默认值:""
	 */
	@Column(name = "KEY", length = 256)
	public String getKey()
	{
		return this.key; 
	}
	/**
	 * 设置 key值, 类型String 默认值:""
	 *
	 * @param sKey
	 *            key值, 类型String 默认值:""
	 */
	public void setKey(String sKey)
	{
		this.key = sKey; 
	}


	/**
	* 获取 发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 *
	 * @return 发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 */
	@Column(name = "STATUS")
	public long getStatus()
	{
		return this.status; 
	}
	/**
	 * 设置 发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 *
	 * @param sStatus
	 *            发送状态（1：待发送；2：已发送）, 类型long 默认值:0
	 */
	public void setStatus(long sStatus)
	{
		this.status = sStatus; 
	}


	/**
	* 获取 组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 *
	 * @return 组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 */
	@Column(name = "GRP_ID", length = 10)
	public String getGrpId()
	{
		return this.grpId; 
	}
	/**
	 * 设置 组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 *
	 * @param sGrpId
	 *            组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 */
	public void setGrpId(String sGrpId)
	{
		this.grpId = sGrpId; 
	}


	/**
	* 获取 任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 *
	 * @return 任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 */
	@Column(name = "TASK_TYPE", length = 10)
	public String getTaskType()
	{
		return this.taskType; 
	}
	/**
	 * 设置 任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 *
	 * @param sTaskType
	 *            任务类型(1.周期性任务，2.一次性任务), 类型String 默认值:""
	 */
	public void setTaskType(String sTaskType)
	{
		this.taskType = sTaskType; 
	}


	/**
	* 获取 发送开始时间, 类型Date 默认值:new Date()
	 *
	 * @return 发送开始时间, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEND_TIME")
	public Date getSendTime()
	{
		return this.sendTime; 
	}
	/**
	 * 设置 发送开始时间, 类型Date 默认值:new Date()
	 *
	 * @param sSendTime
	 *            发送开始时间, 类型Date 默认值:new Date()
	 */
	public void setSendTime(Date sSendTime)
	{
		this.sendTime = sSendTime; 
	}


	/**
	* 获取 错误信息, 类型String 默认值:""
	 *
	 * @return 错误信息, 类型String 默认值:""
	 */
	@Column(name = "ERROR_MESSAGE", length = 2000)
	public String getErrorMessage()
	{
		return this.errorMessage; 
	}
	/**
	 * 设置 错误信息, 类型String 默认值:""
	 *
	 * @param sErrorMessage
	 *            错误信息, 类型String 默认值:""
	 */
	public void setErrorMessage(String sErrorMessage)
	{
		this.errorMessage = sErrorMessage; 
	}


	/**
	* 获取 创建时间, 类型Date 默认值:new Date()
	 *
	 * @return 创建时间, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", updatable=false)
	@CreatedDate
	public Date getCreateTime()
	{
		return this.createTime; 
	}
	/**
	 * 设置 创建时间, 类型Date 默认值:new Date()
	 *
	 * @param sCreateTime
	 *            创建时间, 类型Date 默认值:new Date()
	 */
	public void setCreateTime(Date sCreateTime)
	{
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
	public Date getUpdateTime()
	{
		return this.updateTime; 
	}
	/**
	 * 设置 更新时间, 类型Date 默认值:new Date()
	 *
	 * @param sUpdateTime
	 *            更新时间, 类型Date 默认值:new Date()
	 */
	public void setUpdateTime(Date sUpdateTime)
	{
		this.updateTime = sUpdateTime; 
	}




}

