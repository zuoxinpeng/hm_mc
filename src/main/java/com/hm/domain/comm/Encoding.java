package com.hm.domain.comm;

/**
 * <pre>
 * 编码类型枚举
 * </pre>
 * 
 * @author 徐鹏基 
 */
public enum Encoding
{
	ISO_8859_1("ISO-8859-1"),
	GB2312("GB2312"),
	GBK("GBK"),
	UTF8("UTF-8");
	
	private String code;
	private Encoding(String code)
	{
		this.code = code;
	}
	
	public String getCode()
	{
		return this.code;
	}
}
