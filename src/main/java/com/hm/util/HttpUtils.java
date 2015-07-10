/**
 * FileName: HttpUtils.java
 */
package com.hm.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.domain.comm.Encoding;

/**
 * <pre>
 * Http接口访问工具类
 * </pre>
 * 
 * @author 徐鹏基 
 */
public class HttpUtils
{
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	/**
	 * POST提交表单，默认编码为UTF-8
	 * @param httpUrl Http接口地址
	 * @param params 参数列表
	 * @return
	 * @throws IOException
	 *
	 * @author 徐鹏基
	 */
	public static String doPost(String httpUrl, Map<String, String> params) throws IOException
	{
		return doPost(httpUrl, params, Encoding.UTF8, Encoding.UTF8);
	}
	
	/**
	 * POST提交表单，可以指定使用的编码格式，request和response使用相同的编码
	 * @param httpUrl
	 * @param params
	 * @param encode
	 * @return
	 * @throws IOException
	 *
	 * @author 徐鹏基
	 */
	public static String doPost(String httpUrl, Map<String, String> params, Encoding encode) throws IOException
	{
		return doPost(httpUrl, params, encode, encode);
	}
	
	/**
	 * POST方式提交表单，可以分别指定request,response的编码格式
	 * @param url Http接口地址
	 * @param params 参数列表
	 * @param reqEncode request使用的编码格式
	 * @param resEncode response使用的编码格式
	 * @return 
	 *
	 * @author 徐鹏基
	 */
	public static String doPost(String httpUrl, Map<String, String> params, Encoding reqEncode, Encoding resEncode) throws IOException
	{
		
		StringBuilder result = new StringBuilder(256);
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(httpUrl);
		
		try
		{
			List<NameValuePair> valueList = new ArrayList<NameValuePair>();
			if(params != null)
			{
				for(Entry<String, String> entry : params.entrySet())
				{
					NameValuePair value = new NameValuePair();
					value.setName(entry.getKey());
					value.setValue(entry.getValue());
					
					valueList.add(value);
				}
			}
			
			client.getParams().setContentCharset(reqEncode.getCode());
			method.setRequestBody(valueList.toArray(new NameValuePair[0]));

			int statusCode = client.executeMethod(method);
			logger.debug("Http execute status code: " + statusCode);
			if(HttpStatus.SC_OK == statusCode)
			{
				client.getParams().setContentCharset(resEncode.getCode());
				result.append(method.getResponseBodyAsString());
			}
		}
		catch(Exception exp)
		{
			logger.error("Http接口访问异常：" + exp.getMessage());
			throw new IOException(exp.getMessage());
		}
		finally
		{
			method.releaseConnection();
		}
		
		logger.debug("Http execute result:" + result.toString());
		return result.toString();
	}
	
	public static String URLEncoder(String str, String encode)
	{
		try
		{
			return URLEncoder.encode(str, encode);
		}
		catch(UnsupportedEncodingException exp)
		{
			logger.error("URLEncoder error:" + exp.getMessage());
			return str;
		}
	}
	
	public static String URLDecoder(String str, String decode)
	{
		try
		{
			return java.net.URLDecoder.decode(str, decode);
		}
		catch(UnsupportedEncodingException exp)
		{
			logger.error("URLDecoder error: " + exp.getMessage());
			return str;
		}
	}
	
	private HttpUtils()
	{
		
	}
}
