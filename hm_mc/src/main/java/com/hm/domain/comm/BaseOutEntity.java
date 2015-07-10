package com.hm.domain.comm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.hm.util.JsonUtils;

public class BaseOutEntity implements Serializable {

	private static transient final long serialVersionUID = 3814728826602200430L;

	private String code = "";
	private String msg = "success";
	private Object data;

	public BaseOutEntity() {

	}

	public BaseOutEntity(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JsonUtils.getInstance().beanToJson(this);
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg != null ? msg : "");
		map.put("data", data != null ? data : "");
		return map;
	}
}
