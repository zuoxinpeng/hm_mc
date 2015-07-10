package com.hm.domain.user;

import java.util.Date;

import com.aggrepoint.winlet.UserProfile;

/**
 * 用户身份
 * 
 * @author zuoxp
 */
public class PiuUserProfile implements UserProfile {
	private static final long serialVersionUID = 193459345357L;

	private String loginId; // 登录名
	private String name; // nickname
	private String password;
	private Date endTime;

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isAnonymous() {
		return loginId == null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
