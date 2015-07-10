package com.hm.domain.tem;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.aggrepoint.jpa.CreatedBy;
import com.aggrepoint.jpa.CreatedDate;
import com.aggrepoint.jpa.UpdatedBy;
import com.aggrepoint.jpa.UpdatedDate;

/**
 * 消息中心_模板明细表实体类
 * 
 * @author 左鑫鹏
 * 
 */
@Entity
@Table(name = "ZT_MC_BAS_TEMPLATE_DETAIL")
public class McTemplateDetail implements Serializable {
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
	 * JVM会把传来的字节流中的serialVersionUID与本地相应实体
	 * （类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2014112110451338081L;

	/**
	 * 获取 模板明细主键, 类型long 默认值:0
	 */
	private long templateDetailId;

	/**
	 * 获取 模板ID, 类型long 默认值:0
	 */
	private long templateId;

	/**
	 * 获取 消息类型ID, 类型long 默认值:0
	 */
	private long templateTypeId;

	/**
	 * 获取 模板标题, 类型String 默认值:""
	 */
	private String templateTitle;

	/**
	 * 获取 模板内容, 类型String 默认值:""
	 */
	private String templateContent;

	/**
	 * 获取 模板备注, 类型String 默认值:""
	 */
	private String templateRemark;

	/**
	 * 获取 启用禁用（2：禁用；1：启用；默认2禁用）, 类型long 默认值:(long)2
	 */
	private long templateStatus;

	/**
	 * 获取 创建人, 类型String 默认值:""
	 */
	private String creator;

	/**
	 * 获取 创建时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	/**
	 * 获取 更新人, 类型String 默认值:""
	 */
	private String updater;

	/**
	 * 获取 更新时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;

	/**
	 * 消息中心_模板明细表 对象
	 */
	private McTemplate mcTemplate;

	/**
	 * 获取 模板明细主键, 类型long 默认值:0
	 * 
	 * @return 模板明细主键, 类型long 默认值:0
	 */
	@Column(name = "TEMPLATE_DETAIL_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_TEMPLATE_DETAIL", allocationSize = 1)
	public long getTemplateDetailId() {
		return this.templateDetailId;
	}

	/**
	 * 设置 模板明细主键, 类型long 默认值:0
	 * 
	 * @param sTemplateDetailId
	 *            模板明细主键, 类型long 默认值:0
	 */
	public void setTemplateDetailId(long sTemplateDetailId) {
		this.templateDetailId = sTemplateDetailId;
	}

	/**
	 * 获取 模板ID, 类型long 默认值:0
	 * 
	 * @return 模板ID, 类型long 默认值:0
	 */
	@Column(name = "TEMPLATE_ID")
	public long getTemplateId() {
		return this.templateId;
	}

	/**
	 * 设置 模板ID, 类型long 默认值:0
	 * 
	 * @param sTemplateId
	 *            模板ID, 类型long 默认值:0
	 */
	public void setTemplateId(long sTemplateId) {
		this.templateId = sTemplateId;
	}

	/**
	 * 获取 消息类型ID, 类型long 默认值:0
	 * 
	 * @return 消息类型ID, 类型long 默认值:0
	 */
	@Column(name = "TEMPLATE_TYPE_ID")
	public long getTemplateTypeId() {
		return this.templateTypeId;
	}

	/**
	 * 设置 消息类型ID, 类型long 默认值:0
	 * 
	 * @param sTemplateTypeId
	 *            消息类型ID, 类型long 默认值:0
	 */
	public void setTemplateTypeId(long sTemplateTypeId) {
		this.templateTypeId = sTemplateTypeId;
	}

	/**
	 * 获取 模板标题, 类型String 默认值:""
	 * 
	 * @return 模板标题, 类型String 默认值:""
	 */
	@Column(name = "TEMPLATE_TITLE", length = 256)
	public String getTemplateTitle() {
		return this.templateTitle;
	}

	/**
	 * 设置 模板标题, 类型String 默认值:""
	 * 
	 * @param sTemplateTitle
	 *            模板标题, 类型String 默认值:""
	 */
	public void setTemplateTitle(String sTemplateTitle) {
		this.templateTitle = sTemplateTitle;
	}

	/**
	 * 获取 模板内容, 类型String 默认值:""
	 * 
	 * @return 模板内容, 类型String 默认值:""
	 */
	@Column(name = "TEMPLATE_CONTENT", length = 1024)
	public String getTemplateContent() {
		return this.templateContent;
	}

	/**
	 * 设置 模板内容, 类型String 默认值:""
	 * 
	 * @param sTemplateContent
	 *            模板内容, 类型String 默认值:""
	 */
	public void setTemplateContent(String sTemplateContent) {
		this.templateContent = sTemplateContent;
	}

	/**
	 * 获取 模板备注, 类型String 默认值:""
	 * 
	 * @return 模板备注, 类型String 默认值:""
	 */
	@Column(name = "TEMPLATE_REMARK", length = 256)
	public String getTemplateRemark() {
		return this.templateRemark;
	}

	/**
	 * 设置 模板备注, 类型String 默认值:""
	 * 
	 * @param sTemplateRemark
	 *            模板备注, 类型String 默认值:""
	 */
	public void setTemplateRemark(String sTemplateRemark) {
		this.templateRemark = sTemplateRemark;
	}

	/**
	 * 获取 启用禁用（2：禁用；1：启用；默认2禁用）, 类型long 默认值:(long)2
	 * 
	 * @return 启用禁用（2：禁用；1：启用；默认2禁用）, 类型long 默认值:(long)2
	 */
	@Column(name = "TEMPLATE_STATUS")
	public long getTemplateStatus() {
		return this.templateStatus;
	}

	/**
	 * 设置 启用禁用（2：禁用；1：启用；默认2禁用）, 类型long 默认值:(long)2
	 * 
	 * @param sTemplateStatus
	 *            启用禁用（2：禁用；1：启用；默认2禁用）, 类型long 默认值:(long)2
	 */
	public void setTemplateStatus(long sTemplateStatus) {
		this.templateStatus = sTemplateStatus;
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

	/**
	 * 获取 更新人, 类型String 默认值:""
	 * 
	 * @return 更新人, 类型String 默认值:""
	 */
	@Column(name = "UPDATER", length = 20)
	@UpdatedBy
	public String getUpdater() {
		return this.updater;
	}

	/**
	 * 设置 更新人, 类型String 默认值:""
	 * 
	 * @param sUpdater
	 *            更新人, 类型String 默认值:""
	 */
	public void setUpdater(String sUpdater) {
		this.updater = sUpdater;
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

	/**
	 * 获取 消息中心_模板明细表 对象
	 * 
	 * @return
	 */
	@ManyToOne(targetEntity = McTemplate.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID", nullable = true, insertable = false, updatable = false)
	public McTemplate getMcTemplate() {
		return mcTemplate;
	}

	/**
	 * 设置 消息中心_模板明细表 对象
	 * 
	 * @param mcTemplate
	 *            消息中心_模板明细表
	 */
	public void setMcTemplate(McTemplate mcTemplate) {
		this.mcTemplate = mcTemplate;
	}

}
