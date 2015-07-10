/*
 * @(#)UrlUtil.java 1.0 2014-11-4
 *
 * Copyright (c) 2014 Ryan Jiang. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hm.util;

import java.util.Map;
import java.util.Set;

/**
 * URL的工具类.
 * 
 * @author Ryan Jiang
 * @version 1.0
 * @since 1.0
 */
public class UrlUtil {

	/**
	 * URL地址转换.
	 * 
	 * @param url
	 *            原有的url地址
	 * @param params
	 *            url中需要加入的restful参数,key为变量名
	 * @return 返回新的url地址
	 */
	public static String convertUrl(String url, Map<String, Object> params) {
		String prefix = "";
		String suffix = "";
		if (params != null) {
			// 如果url中后缀
			if (url.indexOf(".") != -1) {
				prefix = url.split("[.]")[0];
				suffix = "." + url.split("[.]")[1];
			} else {
				prefix = url;
			}
			// 参数的key作为url的参数名称
			Set<String> keys = params.keySet();
			for (String key : keys) {
				prefix = prefix + "/" + key + "/{" + key + "}";
			}

			url = prefix + suffix;
		}
		return url;
	}
}
