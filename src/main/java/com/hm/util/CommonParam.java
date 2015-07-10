package com.hm.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 获取配置资源文件 [公共参数] 信息
 * 
 * @author 左鑫鹏
 */
public class CommonParam {
	private ResourceBundle resourceBundle;

	public CommonParam() {
		Locale locale = new Locale("en", "US");
		resourceBundle = ResourceBundle.getBundle("sysConfig", locale);
	}

	public String getString(String key) {
		if (key == null || key.equals("") || key.equals("null")) {
			return "";
		}
		String result = "";
		try {
			result = resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return result;
	}
}
