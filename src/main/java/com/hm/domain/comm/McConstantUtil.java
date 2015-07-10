package com.hm.domain.comm;

import java.io.Serializable;

public class McConstantUtil implements Serializable {
	
	
	 /**
	 * serialVersionUID:TODO
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	public static long SMS_STATUS_TO_SEND = 1L;	//短信状态-待发送
	public static long SMS_STATUS_IS_SEND = 2L;//短信状态-已发送
	
	public static String MESSAGETYPE_DX = "1";	// 短信
	public static String MESSAGETYPE_YJ = "2";	// 邮件
	public static String MESSAGETYPE_ZNX = "3";	// 站内信
	public static String MESSAGETYPE_IMO = "4";	// IMO
	
	/*调用SMS系统WEBSERVICE相关参数start*/
	public static String RETURN_TYPE_JSON = "1";//返回类型
	public static String SUCCESS_FLAG = "1";//成功的标志位
	/*调用SMS系统WEBSERVICE相关参数end*/
	
	public static String IV_GRP_ID = "42";	//给SMS系统传递的组ID，由SMS系统进行约定
	
}
