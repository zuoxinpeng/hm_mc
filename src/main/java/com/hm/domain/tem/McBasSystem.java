package com.hm.domain.tem;

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
import com.aggrepoint.jpa.UpdatedBy;
import com.aggrepoint.jpa.UpdatedDate;

/**
 * 消息中心_系统码表实体类
 *
 * @author 左鑫鹏
 *
 */
@Entity
@Table(name = "ZT_MC_BAS_SYSTEM")
public class McBasSystem implements Serializable {
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
	 * JVM会把传来的字节流中的serialVersionUID与本地相应实体
	 * （类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2014111720191764147L;

	/**
	 * 获取 比酷、IVALUE, 类型long 默认值:0
	 */
	private long systId;

	/**
	 * 获取 SYST_CODE, 类型String 默认值:""
	 */
	private String systCode;

	/**
	 * 获取 SYST_NAME, 类型String 默认值:""
	 */
	private String systName;

	/**
	 * 获取 SYST_IP, 类型String 默认值:""
	 */
	private String systIp;

	/**
	 * 获取 SYST_PW, 类型String 默认值:""
	 */
	private String systPw;

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
	 * 获取 比酷、IVALUE, 类型long 默认值:0
	 *
	 * @return 比酷、IVALUE, 类型long 默认值:0
	 */
	@Column(name = "SYST_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_SYSTEM", allocationSize = 1)
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
	 * 获取 SYST_CODE, 类型String 默认值:""
	 *
	 * @return SYST_CODE, 类型String 默认值:""
	 */
	@Column(name = "SYST_CODE", length = 20)
	public String getSystCode() {
		return this.systCode;
	}

	/**
	 * 设置 SYST_CODE, 类型String 默认值:""
	 *
	 * @param sSystCode
	 *            SYST_CODE, 类型String 默认值:""
	 */
	public void setSystCode(String sSystCode) {
		this.systCode = sSystCode;
	}

	/**
	 * 获取 SYST_NAME, 类型String 默认值:""
	 *
	 * @return SYST_NAME, 类型String 默认值:""
	 */
	@Column(name = "SYST_NAME", length = 20)
	public String getSystName() {
		return this.systName;
	}

	/**
	 * 设置 SYST_NAME, 类型String 默认值:""
	 *
	 * @param sSystName
	 *            SYST_NAME, 类型String 默认值:""
	 */
	public void setSystName(String sSystName) {
		this.systName = sSystName;
	}

	/**
	 * 获取 SYST_IP, 类型String 默认值:""
	 *
	 * @return SYST_IP, 类型String 默认值:""
	 */
	@Column(name = "SYST_IP", length = 56)
	public String getSystIp() {
		return this.systIp;
	}

	/**
	 * 设置 SYST_IP, 类型String 默认值:""
	 *
	 * @param sSystIp
	 *            SYST_IP, 类型String 默认值:""
	 */
	public void setSystIp(String sSystIp) {
		this.systIp = sSystIp;
	}

	/**
	 * 获取 SYST_PW, 类型String 默认值:""
	 *
	 * @return SYST_PW, 类型String 默认值:""
	 */
	@Column(name = "SYST_PW", length = 128)
	public String getSystPw() {
		return this.systPw;
	}

	/**
	 * 设置 SYST_PW, 类型String 默认值:""
	 *
	 * @param sSystPw
	 *            SYST_PW, 类型String 默认值:""
	 */
	public void setSystPw(String sSystPw) {
		this.systPw = sSystPw;
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

}
