package com.hm.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @类名称: 获取配置资源文件 [公共参数] 信息
 * @功能描述: 填写类的作用功能
 * @作者: 左鑫鹏
 * @创建日期: 2015年6月13日
 * @Copyright: 惠买集团 版权所有
 */
public class ResponseBundleForConf {
	protected static Logger logger = LoggerFactory.getLogger(ResponseBundleForConf.class);
	protected static ResourceBundle mcResponse;
	protected static final String MC_RESPONSE_FILE = "conf/mcConfig";

	static {
		try {
			// 提供检测conf/mcResponse.properties文件存在与否的机制
			mcResponse = PropertyResourceBundle.getBundle(MC_RESPONSE_FILE);
		} catch (Exception e) {
			logger.error(MC_RESPONSE_FILE + "配置文件加载失败。", e);
		}
	}

	public static int getIntegerCode(String key) {
		try {
			if (mcResponse.getString(key) != null) {
				return Integer.parseInt(mcResponse.getString(key));
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
		return mcResponse.getString(key) != null ? mcResponse.getString(key) : "";
	}

	public static int getIntegerCode(String key, int value) {
		try {
			if (mcResponse.getString(key) != null) {
				return Integer.parseInt(mcResponse.getString(key));
			}
		} catch (Exception e) {
			logger.error("RESPONSE CODE FROM STRING TO INT THEN ERROR.", e);
		}
		return value;
	}

	public static String get(String key) {
		return mcResponse.getString(key);
	}

	public static String get(String key, String value) {
		return mcResponse.getString(key) == null ? value : mcResponse.getString(key);
	}

}
