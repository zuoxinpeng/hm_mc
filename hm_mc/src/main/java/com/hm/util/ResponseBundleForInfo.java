package com.hm.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller层返回的code msg提取类
 */
public class ResponseBundleForInfo {
	protected static Logger logger = LoggerFactory.getLogger(ResponseBundleForInfo.class);
	protected static ResourceBundle erpResponse;
	protected static final String ERP_RESPONSE_FILE = "conf/mcInfo";

	static {
		try {
			// TODO 提供检测conf/erpResponse.properties文件存在与否的机制
			erpResponse = PropertyResourceBundle.getBundle(ERP_RESPONSE_FILE);
		} catch (Exception e) {
			logger.error(ERP_RESPONSE_FILE + "配置文件加载失败。", e);
		}
	}

	public static int getIntegerCode(String key) {
		try {
			if (erpResponse.getString(key) != null) {
				return Integer.parseInt(erpResponse.getString(key));
			}
		} catch (Exception e) {
			logger.error("RESPONSE CODE FROM STRING TO INT THEN ERROR.", e);
		}
		return 0;
	}

	/**
	 * update by Brain
	 * 
	 * @param key
	 * @return
	 */
	public static String getStringCode(String key) {
		return erpResponse.getString(key) != null ? erpResponse.getString(key) : "";
	}

	public static int getIntegerCode(String key, int value) {
		try {
			if (erpResponse.getString(key) != null) {
				return Integer.parseInt(erpResponse.getString(key));
			}
		} catch (Exception e) {
			logger.error("RESPONSE CODE FROM STRING TO INT THEN ERROR.", e);
		}
		return value;
	}

	public static String get(String key) {
		return erpResponse.getString(key);
	}

	public static String get(String key, String value) {
		return erpResponse.getString(key) == null ? value : erpResponse.getString(key);
	}

}
