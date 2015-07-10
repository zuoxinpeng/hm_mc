package com.hm.domain.user;

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
 * 消息中心内部用户表实体类
 * 
 * @author zuoxp
 * 
 */
@Entity
@Table(name = "ZT_MC_BAS_PIU_USER")
public class McBasPiuUser implements Serializable {
	/**
	 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
	 * JVM会把传来的字节流中的serialVersionUID与本地相应实体
	 * （类）的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常
	 */
	private static final long serialVersionUID = 2014120513505446287L;

	/**
	 * 获取 内部用户主键, 类型long 默认值:0
	 */
	private long userId;

	/**
	 * 获取 登录账号, 类型String 默认值:""
	 */
	private String loginId;

	/**
	 * 获取 用户名称, 类型String 默认值:""
	 */
	private String name;
	
	/**
	 * 获取 员工编号, 类型String 默认值:""
	 */
	private String employeeNo;

	/**
	 * 获取 登录密码, 类型String 默认值:""
	 */
	private String password;
	
	/**
	 * 获取 手机号码, 类型String 默认值:""
	 */
	private String phone;
	
	/**
	 * 获取 员工邮箱, 类型String 默认值:""
	 */
	private String email;
	
	/**
	 * 获取 imo账号, 类型String 默认值:""
	 */
	private String imoId;
	

	/**
	 * 获取 账号状态（1启用，2禁用）, 类型long 默认值:0
	 */
	private long accountStatus;

	/**
	 * 获取 备注, 类型String 默认值:""
	 */
	private String remark;

	/**
	 * 获取 上次访问ip, 类型String 默认值:""
	 */
	private String lastLoginIp;

	/**
	 * 获取 最后登录时间, 类型Date 默认值:new Date()
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastLoginTime;

	/**
	 * 获取 创建人, 类型String 默认值:""
	 */
	private String creater;

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
	 * 获取 内部用户主键, 类型long 默认值:0
	 * 
	 * @return 内部用户主键, 类型long 默认值:0
	 */
	@Column(name = "USER_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_ZT_MC_BAS_PIU_USER", allocationSize = 1)
	public long getUserId() {
		return this.userId;
	}

	/**
	 * 设置 内部用户主键, 类型long 默认值:0
	 * 
	 * @param sUserId
	 *            内部用户主键, 类型long 默认值:0
	 */
	public void setUserId(long sUserId) {
		this.userId = sUserId;
	}

	/**
	 * 获取 登录账号, 类型String 默认值:""
	 * 
	 * @return 登录账号, 类型String 默认值:""
	 */
	@Column(name = "LOGIN_ID", length = 256)
	public String getLoginId() {
		return this.loginId;
	}

	/**
	 * 设置 登录账号, 类型String 默认值:""
	 * 
	 * @param sLoginId
	 *            登录账号, 类型String 默认值:""
	 */
	public void setLoginId(String sLoginId) {
		this.loginId = sLoginId;
	}

	/**
	 * 获取 用户名称, 类型String 默认值:""
	 * 
	 * @return 用户名称, 类型String 默认值:""
	 */
	@Column(name = "USER_NAME", length = 256)
	public String getName() {
		return this.name;
	}

	/**
	 * 设置 用户名称, 类型String 默认值:""
	 * 
	 * @param sUserName
	 *            用户名称, 类型String 默认值:""
	 */
	public void setName(String sUserName) {
		this.name = sUserName;
	}
	
	/**
	* 获取 员工编号, 类型String 默认值:""
	 *
	 * @return 员工编号, 类型String 默认值:""
	 */
	@Column(name = "USER_NUMBER", length = 256)
	public String getEmployeeNo()
	{
		return this.employeeNo; 
	}
	
	/**
	 * 设置 员工编号, 类型String 默认值:""
	 *
	 * @param sEmployeeNo
	 *            员工编号, 类型String 默认值:""
	 */
	public void setEmployeeNo(String sEmployeeNo)
	{
		this.employeeNo = sEmployeeNo; 
	}
	
	/**
	 * 获取 登录密码, 类型String 默认值:""
	 * 
	 * @return 登录密码, 类型String 默认值:""
	 */
	@Column(name = "PASSWORD", length = 256)
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置 登录密码, 类型String 默认值:""
	 * 
	 * @param sPassword
	 *            登录密码, 类型String 默认值:""
	 */
	public void setPassword(String sPassword) {
		this.password = sPassword;
	}

	/**
	 * 获取 手机号码, 类型String 默认值:""
	 *
	 * @return 手机号码, 类型String 默认值:""
	 */
	@Column(name = "PHONE", length = 256)
	public String getPhone()
	{
		return this.phone; 
	}
	
	/**
	 * 设置 手机号码, 类型String 默认值:""
	 *
	 * @param sPhone
	 *            手机号码, 类型String 默认值:""
	 */
	public void setPhone(String sPhone)
	{
		this.phone = sPhone; 
	}


	/**
	 * 获取 员工邮箱, 类型String 默认值:""
	 *
	 * @return 员工邮箱, 类型String 默认值:""
	 */
	@Column(name = "EMAIL", length = 256)
	public String getEmail()
	{
		return this.email; 
	}
	
	/**
	 * 设置 员工邮箱, 类型String 默认值:""
	 *
	 * @param sEmail
	 *            员工邮箱, 类型String 默认值:""
	 */
	public void setEmail(String sEmail)
	{
		this.email = sEmail; 
	}

	/**
	 * 获取 imo账号, 类型String 默认值:""
	 *
	 * @return imo账号, 类型String 默认值:""
	 */
	@Column(name = "IMOID", length = 256)
	public String getImoId()
	{
		return this.imoId; 
	}
	
	/**
	 * 设置 imo账号, 类型String 默认值:""
	 *
	 * @param sImoId
	 *            imo账号, 类型String 默认值:""
	 */
	public void setImoId(String sImoId)
	{
		this.imoId = sImoId; 
	}
	
	/**
	 * 获取 账号状态（1启用，2禁用）, 类型long 默认值:0
	 * 
	 * @return 账号状态（1启用，2禁用）, 类型long 默认值:0
	 */
	@Column(name = "ACCOUNT_STATUS")
	public long getAccountStatus() {
		return this.accountStatus;
	}

	/**
	 * 设置 账号状态（1启用，2禁用）, 类型long 默认值:0
	 * 
	 * @param sAccountStatus
	 *            账号状态（1启用，2禁用）, 类型long 默认值:0
	 */
	public void setAccountStatus(long sAccountStatus) {
		this.accountStatus = sAccountStatus;
	}

	/**
	 * 获取 备注, 类型String 默认值:""
	 * 
	 * @return 备注, 类型String 默认值:""
	 */
	@Column(name = "REMARK", length = 256)
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置 备注, 类型String 默认值:""
	 * 
	 * @param sRemark
	 *            备注, 类型String 默认值:""
	 */
	public void setRemark(String sRemark) {
		this.remark = sRemark;
	}

	/**
	 * 获取 上次访问ip, 类型String 默认值:""
	 * 
	 * @return 上次访问ip, 类型String 默认值:""
	 */
	@Column(name = "LAST_LOGIN_IP", length = 256)
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	/**
	 * 设置 上次访问ip, 类型String 默认值:""
	 * 
	 * @param sLastLoginIp
	 *            上次访问ip, 类型String 默认值:""
	 */
	public void setLastLoginIp(String sLastLoginIp) {
		this.lastLoginIp = sLastLoginIp;
	}

	/**
	 * 获取 最后登录时间, 类型Date 默认值:new Date()
	 * 
	 * @return 最后登录时间, 类型Date 默认值:new Date()
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_TIME")
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * 设置 最后登录时间, 类型Date 默认值:new Date()
	 * 
	 * @param sLastLoginTime
	 *            最后登录时间, 类型Date 默认值:new Date()
	 */
	public void setLastLoginTime(Date sLastLoginTime) {
		this.lastLoginTime = sLastLoginTime;
	}

	/**
	 * 获取 创建人, 类型String 默认值:""
	 * 
	 * @return 创建人, 类型String 默认值:""
	 */
	@Column(name = "CREATER", length = 256, updatable = false)
	@CreatedBy
	public String getCreater() {
		return this.creater;
	}

	/**
	 * 设置 创建人, 类型String 默认值:""
	 * 
	 * @param sCreater
	 *            创建人, 类型String 默认值:""
	 */
	public void setCreater(String sCreater) {
		this.creater = sCreater;
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
	@Column(name = "UPDATER", length = 256)
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
