package com.quartz.base.task.domain;

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
 *
 * ScheduleJob	调度任务实体类
 * 
 * ZUOXP
 * 2015-1-19 上午11:33:10
 * 
 * @version 1.0.0
 *
 */
@Entity
@Table(name = "ZT_MC_BAS_SCHEDULE_JOB")
public class ScheduleJob implements Serializable {
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
	 * JVM会把传来的字节流中的serialVersionUID与本地相应实体
	 * （类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2015011209061139449L;

	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";

	/**
	 * 获取 任务ID, 类型long 默认值:0
	 */
	private Long jobId;

	/**
	 * 获取 任务名称, 类型String 默认值:""
	 */
	private String jobName;

	/**
	 * 获取 任务分组, 类型String 默认值:""
	 */
	private String jobGroup;

	/**
	 * 获取 任务状态 0禁用 1启用 2删除, 类型String 默认值:""
	 */
	private String jobStatus;

	/**
	 * 获取 cron表达式（任务运行时间表达式）, 类型String 默认值:""
	 */
	private String cronExpression;

	/**
	 * 获取 任务描述, 类型String 默认值:""
	 */
	private String description;

	/**
	 * 获取 任务执行时调用哪个类的方法 包名+类名, 类型String 默认值:""
	 */
	private String beanClass;

	/**
	 * 获取 任务调用的方法名, 类型String 默认值:""
	 */
	private String methodName;

	/**
	 * 获取 任务是否有状态, 类型String 默认值:""
	 */
	private String isConcurrent;

	/**
	 * 获取 spring bean, 类型String 默认值:""
	 */
	private String springId;

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
	 * 获取 任务ID, 类型long 默认值:0
	 * 
	 * @return 任务ID, 类型long 默认值:0
	 */
	@Column(name = "JOB_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_SCHEDULE_JOB", allocationSize = 1)
	public Long getJobId() {
		return this.jobId;
	}

	/**
	 * 设置 任务ID, 类型long 默认值:0
	 * 
	 * @param sJobId
	 *            任务ID, 类型long 默认值:0
	 */
	public void setJobId(Long sJobId) {
		this.jobId = sJobId;
	}

	/**
	 * 获取 任务名称, 类型String 默认值:""
	 * 
	 * @return 任务名称, 类型String 默认值:""
	 */
	@Column(name = "JOB_NAME", length = 256)
	public String getJobName() {
		return this.jobName;
	}

	/**
	 * 设置 任务名称, 类型String 默认值:""
	 * 
	 * @param sJobName
	 *            任务名称, 类型String 默认值:""
	 */
	public void setJobName(String sJobName) {
		this.jobName = sJobName;
	}

	/**
	 * 获取 任务分组, 类型String 默认值:""
	 * 
	 * @return 任务分组, 类型String 默认值:""
	 */
	@Column(name = "JOB_GROUP", length = 256)
	public String getJobGroup() {
		return this.jobGroup;
	}

	/**
	 * 设置 任务分组, 类型String 默认值:""
	 * 
	 * @param sJobGroup
	 *            任务分组, 类型String 默认值:""
	 */
	public void setJobGroup(String sJobGroup) {
		this.jobGroup = sJobGroup;
	}

	/**
	 * 获取 任务状态 0禁用 1启用 2删除, 类型String 默认值:""
	 * 
	 * @return 任务状态 0禁用 1启用 2删除, 类型String 默认值:""
	 */
	@Column(name = "JOB_STATUS", length = 256)
	public String getJobStatus() {
		return this.jobStatus;
	}

	/**
	 * 设置 任务状态 0禁用 1启用 2删除, 类型String 默认值:""
	 * 
	 * @param sJobStatus
	 *            任务状态 0禁用 1启用 2删除, 类型String 默认值:""
	 */
	public void setJobStatus(String sJobStatus) {
		this.jobStatus = sJobStatus;
	}

	/**
	 * 获取 cron表达式（任务运行时间表达式）, 类型String 默认值:""
	 * 
	 * @return cron表达式（任务运行时间表达式）, 类型String 默认值:""
	 */
	@Column(name = "CRON_EXPRESSION", length = 256)
	public String getCronExpression() {
		return this.cronExpression;
	}

	/**
	 * 设置 cron表达式（任务运行时间表达式）, 类型String 默认值:""
	 * 
	 * @param sCronExpression
	 *            cron表达式（任务运行时间表达式）, 类型String 默认值:""
	 */
	public void setCronExpression(String sCronExpression) {
		this.cronExpression = sCronExpression;
	}

	/**
	 * 获取 任务描述, 类型String 默认值:""
	 * 
	 * @return 任务描述, 类型String 默认值:""
	 */
	@Column(name = "DESCRIPTION", length = 256)
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置 任务描述, 类型String 默认值:""
	 * 
	 * @param sDescription
	 *            任务描述, 类型String 默认值:""
	 */
	public void setDescription(String sDescription) {
		this.description = sDescription;
	}

	/**
	 * 获取 任务执行时调用哪个类的方法 包名+类名, 类型String 默认值:""
	 * 
	 * @return 任务执行时调用哪个类的方法 包名+类名, 类型String 默认值:""
	 */
	@Column(name = "BEAN_CLASS", length = 256)
	public String getBeanClass() {
		return this.beanClass;
	}

	/**
	 * 设置 任务执行时调用哪个类的方法 包名+类名, 类型String 默认值:""
	 * 
	 * @param sBeanClass
	 *            任务执行时调用哪个类的方法 包名+类名, 类型String 默认值:""
	 */
	public void setBeanClass(String sBeanClass) {
		this.beanClass = sBeanClass;
	}

	/**
	 * 获取 任务调用的方法名, 类型String 默认值:""
	 * 
	 * @return 任务调用的方法名, 类型String 默认值:""
	 */
	@Column(name = "METHOD_NAME", length = 256)
	public String getMethodName() {
		return this.methodName;
	}

	/**
	 * 设置 任务调用的方法名, 类型String 默认值:""
	 * 
	 * @param sMethodName
	 *            任务调用的方法名, 类型String 默认值:""
	 */
	public void setMethodName(String sMethodName) {
		this.methodName = sMethodName;
	}

	/**
	 * 获取 任务是否有状态, 类型String 默认值:""
	 * 
	 * @return 任务是否有状态, 类型String 默认值:""
	 */
	@Column(name = "IS_CONCURRENT", length = 256)
	public String getIsConcurrent() {
		return this.isConcurrent;
	}

	/**
	 * 设置 任务是否有状态, 类型String 默认值:""
	 * 
	 * @param sIsConcurrent
	 *            任务是否有状态, 类型String 默认值:""
	 */
	public void setIsConcurrent(String sIsConcurrent) {
		this.isConcurrent = sIsConcurrent;
	}

	/**
	 * 获取 spring bean, 类型String 默认值:""
	 * 
	 * @return spring bean, 类型String 默认值:""
	 */
	@Column(name = "SPRING_ID", length = 256)
	public String getSpringId() {
		return this.springId;
	}

	/**
	 * 设置 spring bean, 类型String 默认值:""
	 * 
	 * @param sSpringId
	 *            spring bean, 类型String 默认值:""
	 */
	public void setSpringId(String sSpringId) {
		this.springId = sSpringId;
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
