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
 *消息中心_IMO表实体类
 *
 * @author wk
 *
 */
@Entity
@Table(name = "ZT_MC_BAS_IMO")
public class McBasImo implements Serializable
{
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2015010609483930559L;
	
	public McBasImo(){
	}
	
	public McBasImo(String receiver, String receiverUid,String subject,
			String content, String key, long status, String grpId,
			Date sendTime, Date createTime, Date updateTime) {
		super();
		this.receiver = receiver;
		this.receiverUid = receiverUid;
		this.subject = subject;
		this.content = content;
		this.key = key;
		this.status = status;
		this.grpId = grpId;
		this.sendTime = sendTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}




	/**
	* 获取 IMOID, 类型long 默认值:0
	 */
	private long imoId;

	/**
	* 获取 IMO接收人（邮箱的前缀拼音组成）, 类型String 默认值:""
	 */
	private String receiver;
	/**
	* 获取 IMO接收人员工编号, 类型String 默认值:""
	 */
	private String receiverUid;

	/**
	* 获取 IMO主题, 类型String 默认值:""
	 */
	private String subject;

	/**
	* 获取 IMO内容, 类型String 默认值:""
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
	* 获取 发送开始时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date sendTime;

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
	* 获取 IMOID, 类型long 默认值:0
	 *
	 * @return IMOID, 类型long 默认值:0
	 */
	@Column(name = "IMO_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_IMO", allocationSize = 1)
	public long getImoId() {
		return imoId;
	}
	/**
	 * 设置 IMOID, 类型long 默认值:0
	 *
	 * @param sId
	 *            IMOID, 类型long 默认值:0
	 */
	public void setImoId(long simoId) {
		this.imoId = simoId;
	}


	/**
	* 获取 IMO接收人（邮箱的前缀拼音组成）, 类型String 默认值:""
	 *
	 * @return IMO接收人（邮箱的前缀拼音组成）, 类型String 默认值:""
	 */
	@Column(name = "RECEIVER", length = 256)
	public String getReceiver()
	{
		return this.receiver; 
	}
	/**
	 * 设置 IMO接收人（邮箱的前缀拼音组成）, 类型String 默认值:""
	 *
	 * @param sReceiver
	 *            IMO接收人（邮箱的前缀拼音组成）, 类型String 默认值:""
	 */
	public void setReceiver(String sReceiver)
	{
		this.receiver = sReceiver; 
	}
	
	

	/**
	* 获取 IMO接收人UID, 类型String 默认值:""
	 *
	 * @return IMO接收人(员工编号), 类型String 默认值:""
	 */
	@Column(name = "RECEIVER_UID", length = 256)
	public String getReceiverUid() {
		return receiverUid;
	}

	/**
	 * 设置 IMO接收人UID, 类型String 默认值:""
	 *
	 * @param sReceiver
	 *            IMO接收人(员工编号), 类型String 默认值:""
	 */
	public void setReceiverUid(String receiverUid) {
		this.receiverUid = receiverUid;
	}

	/**
	* 获取 IMO主题, 类型String 默认值:""
	 *
	 * @return IMO主题, 类型String 默认值:""
	 */
	@Column(name = "SUBJECT", length = 256)
	public String getSubject()
	{
		return this.subject; 
	}
	/**
	 * 设置 IMO主题, 类型String 默认值:""
	 *
	 * @param sSubject
	 *            IMO主题, 类型String 默认值:""
	 */
	public void setSubject(String sSubject)
	{
		this.subject = sSubject; 
	}


	/**
	* 获取 IMO内容, 类型String 默认值:""
	 *
	 * @return IMO内容, 类型String 默认值:""
	 */
	@Column(name = "CONTENT", length = 4000)
	public String getContent()
	{
		return this.content; 
	}
	/**
	 * 设置 IMO内容, 类型String 默认值:""
	 *
	 * @param sContent
	 *            IMO内容, 类型String 默认值:""
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
	 * @param sId
	 *            组ID（网站、ivalue、比酷）, 类型String 默认值:""
	 */
	public void setGrpId(String sGrpId)
	{
		this.grpId = sGrpId; 
	}


	/**
	* 获取 发送开始时间, 类型Date 默认值:new Date()
	 *
	 * @return 发送开始时间, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SEND_TIME")
	public Date getSendTime()
	{
		return this.sendTime; 
	}
	/**
	 * 设置 发送开始时间, 类型Date 默认值:new Date()
	 *
	 * @param sTime
	 *            发送开始时间, 类型Date 默认值:new Date()
	 */
	public void setSendTime(Date sSendTime)
	{
		this.sendTime = sSendTime; 
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
	 * @param sTime
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
	 * @param sTime
	 *            更新时间, 类型Date 默认值:new Date()
	 */
	public void setUpdateTime(Date sUpdateTime)
	{
		this.updateTime = sUpdateTime; 
	}




}

