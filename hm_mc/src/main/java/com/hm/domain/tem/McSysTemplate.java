package com.hm.domain.tem;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 消息中心_系统和模板对应关系表实体类
 * 
 * @author 左鑫鹏
 * 
 */
@Entity
@Table(name = "ZT_MC_BAS_SYS_TEMPLATE")
public class McSysTemplate implements Serializable {
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
	 * JVM会把传来的字节流中的serialVersionUID与本地相应实体
	 * （类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2014111720185832083L;

	/**
	 * 获取 关系主键, 类型long 默认值:0
	 */
	private long sysTemplateId;

	/**
	 * 获取 比酷、IVALUE, 类型long 默认值:0
	 */
	private long systId;

	/**
	 * 获取 模板主键, 类型long 默认值:0
	 */
	private long templateId;

	/**
	 * 获取 关系主键, 类型long 默认值:0
	 *
	 * @return 关系主键, 类型long 默认值:0
	 */
	@Column(name = "SYS_TEMPLATE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_SYS_TEMPLATE", allocationSize = 1)
	public long getSysTemplateId() {
		return sysTemplateId;
	}

	/**
	 * 设置 关系主键, 类型long 默认值:0
	 *
	 * @param id
	 *            关系主键, 类型long 默认值:0
	 */
	public void setSysTemplateId(long sysTemplateId) {
		this.sysTemplateId = sysTemplateId;
	}

	/**
	 * 获取 比酷、IVALUE, 类型long 默认值:0
	 * 
	 * @return 比酷、IVALUE, 类型long 默认值:0
	 */
	@Column(name = "SYST_ID")
	@Id
	public long getSystId() {
		return this.systId;
	}

	/**
	 * 设置 比酷、IVALUE, 类型long 默认值:0
	 * 
	 * @param sSystId
	 *            比酷、IVALUE, 类型long 默认值:0
	 */
	public void setSystId(long sSystId) {
		this.systId = sSystId;
	}

	/**
	 * 获取 模板主键, 类型long 默认值:0
	 * 
	 * @return 模板主键, 类型long 默认值:0
	 */
	@Column(name = "TEMPLATE_ID")
	@Id
	public long getTemplateId() {
		return this.templateId;
	}

	/**
	 * 设置 模板主键, 类型long 默认值:0
	 * 
	 * @param sTemplateId
	 *            模板主键, 类型long 默认值:0
	 */
	public void setTemplateId(long sTemplateId) {
		this.templateId = sTemplateId;
	}

}
