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

import com.aggrepoint.jpa.CreatedBy;
import com.aggrepoint.jpa.CreatedDate;

/**
 * 日志表实体类
 * 
 * @author zuoxp
 * 
 */
@Entity
@Table(name = "ZT_MC_BAS_LOG")
public class McBasLog implements Serializable {
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
	 * JVM会把传来的字节流中的serialVersionUID与本地相应实体
	 * （类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2014121714473056492L;

	/**
	 * 获取 日志ID, 类型long 默认值:0
	 */
	private long logId;

	/**
	 * 获取 日志内容, 类型String 默认值:""
	 */
	private String content;

	/**
	 * 获取 日志类型（1.系统级日志，2.业务级日志）, 类型long 默认值:0
	 */
	private long logType;

	/**
	 * 获取 日志重要程度（1.重要、2.一般、3.可忽略）, 类型long 默认值:0
	 */
	private long priority;

	/**
	 * 获取 存档时长, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date achiveTime;

	/**
	 * 获取 日志写入模块标示, 类型String 默认值:""
	 */
	private String moduleFlag;

	/**
	 * 获取 活动日志时长, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date logTime;

	/**
	 * 获取 创建人, 类型String 默认值:""
	 */
	private String creator;

	/**
	 * 获取 创建时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	public McBasLog() {
	}

	public McBasLog(String content) {
		super();
		this.content = content;
		this.logType = 2L;
		this.priority = 3L;
		this.achiveTime = null;
		this.moduleFlag = null;
		this.logTime = null;
		this.createTime = new Date();
	}

	/**
	 * 获取 日志ID, 类型long 默认值:0
	 * 
	 * @return 日志ID, 类型long 默认值:0
	 */
	@Column(name = "LOG_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_LOG", allocationSize = 1)
	public long getLogId() {
		return this.logId;
	}

	/**
	 * 设置 日志ID, 类型long 默认值:0
	 * 
	 * @param sLogId
	 *            日志ID, 类型long 默认值:0
	 */
	public void setLogId(long sLogId) {
		this.logId = sLogId;
	}

	/**
	 * 获取 日志内容, 类型String 默认值:""
	 * 
	 * @return 日志内容, 类型String 默认值:""
	 */
	@Column(name = "CONTENT", length = 2048)
	public String getContent() {
		return this.content;
	}

	/**
	 * 设置 日志内容, 类型String 默认值:""
	 * 
	 * @param sContent
	 *            日志内容, 类型String 默认值:""
	 */
	public void setContent(String sContent) {
		this.content = sContent;
	}

	/**
	 * 获取 日志类型（1.系统级日志，2.业务级日志）, 类型long 默认值:0
	 * 
	 * @return 日志类型（1.系统级日志，2.业务级日志）, 类型long 默认值:0
	 */
	@Column(name = "LOG_TYPE")
	public long getLogType() {
		return this.logType;
	}

	/**
	 * 设置 日志类型（1.系统级日志，2.业务级日志）, 类型long 默认值:0
	 * 
	 * @param sLogType
	 *            日志类型（1.系统级日志，2.业务级日志）, 类型long 默认值:0
	 */
	public void setLogType(long sLogType) {
		this.logType = sLogType;
	}

	/**
	 * 获取 日志重要程度（1.重要、2.一般、3.可忽略）, 类型long 默认值:0
	 * 
	 * @return 日志重要程度（1.重要、2.一般、3.可忽略）, 类型long 默认值:0
	 */
	@Column(name = "PRIORITY")
	public long getPriority() {
		return this.priority;
	}

	/**
	 * 设置 日志重要程度（1.重要、2.一般、3.可忽略）, 类型long 默认值:0
	 * 
	 * @param sPriority
	 *            日志重要程度（1.重要、2.一般、3.可忽略）, 类型long 默认值:0
	 */
	public void setPriority(long sPriority) {
		this.priority = sPriority;
	}

	/**
	 * 获取 存档时长, 类型Date 默认值:new Date()
	 * 
	 * @return 存档时长, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "ACHIVE_TIME")
	public Date getAchiveTime() {
		return this.achiveTime;
	}

	/**
	 * 设置 存档时长, 类型Date 默认值:new Date()
	 * 
	 * @param sAchiveTime
	 *            存档时长, 类型Date 默认值:new Date()
	 */
	public void setAchiveTime(Date sAchiveTime) {
		this.achiveTime = sAchiveTime;
	}

	/**
	 * 获取 日志写入模块标示, 类型String 默认值:""
	 * 
	 * @return 日志写入模块标示, 类型String 默认值:""
	 */
	@Column(name = "MODULE_FLAG", length = 20)
	public String getModuleFlag() {
		return this.moduleFlag;
	}

	/**
	 * 设置 日志写入模块标示, 类型String 默认值:""
	 * 
	 * @param sModuleFlag
	 *            日志写入模块标示, 类型String 默认值:""
	 */
	public void setModuleFlag(String sModuleFlag) {
		this.moduleFlag = sModuleFlag;
	}

	/**
	 * 获取 活动日志时长, 类型Date 默认值:new Date()
	 * 
	 * @return 活动日志时长, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "LOG_TIME")
	public Date getLogTime() {
		return this.logTime;
	}

	/**
	 * 设置 活动日志时长, 类型Date 默认值:new Date()
	 * 
	 * @param sLogTime
	 *            活动日志时长, 类型Date 默认值:new Date()
	 */
	public void setLogTime(Date sLogTime) {
		this.logTime = sLogTime;
	}

	/**
	 * 获取 创建人, 类型String 默认值:""
	 * 
	 * @return 创建人, 类型String 默认值:""
	 */
	@Column(name = "CREATOR", length = 20, updatable = false)
	@CreatedBy
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 设置 创建人, 类型String 默认值:""
	 * 
	 * @param sCreator
	 *            创建人, 类型String 默认值:""
	 */
	public void setCreator(String sCreator) {
		this.creator = sCreator;
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

}
