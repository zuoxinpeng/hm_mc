package com.hm.mc;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;

import com.hm.util.CommonParam;

/**
 * WEBSERVICE客户端代码.这里使用的是xfire进行客户端调用.
 * 

* 运行环境： * 1.需要JDK. * 2.需要导入xfire的jar以及依赖的jar包. *

 */
public class SMSXfireClient {

	/** 返回类型. */
	private static String RETURN_TYPE_JSON = "1";

	/** 成功的标志位. */
	private static final String SUCCESS_FLAG = "1";

	/**
	 * 调用WEBSERVICE的入口方法.
	 * 
	 * @param args
	 *            参数列表
	 */

	public static void main(String[] args) {
		
		CommonParam commparam = new CommonParam();
		// 接口KEY值
		String keyVal = "123456";
		
		String content = "你好啊";
		try {
			content = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 接口参数值,格式为JSON格式的字符串
		JSONObject json = new JSONObject();
		json.put("tel", "18631136383");
		json.put("msg", content);
		json.put("grp_id", "17");
		json.put("sendtime", null);
		json.put("mid", null);
		json.put("sid", null);
		String jsonStr = json.toString();
		try {
			// 创建客户端对象
			Client client = new Client(new URL(commparam.getString("SMS_URL")));
			// 构造参数对象
			Object[] param = new Object[] { keyVal, jsonStr, RETURN_TYPE_JSON };
			// 调用远程接口方法
			Object[] results = client.invoke(commparam.getString("SMS_METHOD"), param);
			// 判断结果不为空且长度大于0
			if (results != null && results.length > 0) {
				// 返回结果
				JSONObject obj = JSONObject.fromObject(results[0]);
				// 获取返回值执行结果的标志位
				String flag = obj.get("flag").toString();
				// flag=1表示执行成功
				if (SUCCESS_FLAG.equals(flag)) {
					// 获取返回值内容
					String msg = obj.get("msg").toString();
					JSONObject obj2 = JSONObject.fromObject(msg);
					obj2.get("suc");
					// 打印返回值内容
					System.out.println("msg=====" + msg);
					// TODO 这里可以进行业务逻辑操作
					System.out.println("suc=====");
				} else {
					// 获取返回值中的错误信息
					String error = obj.get("error").toString();
					// 打印错误信息内容
					System.out.println("error=====" + error);
					// TODO 这里可以进行错误的处理逻辑
				}
			}
		} catch (MalformedURLException e) {
			// 打印异常
			e.printStackTrace();
			// TODO 这里需要进行异常处理
		} catch (Exception e) {
			// 打印异常
			e.printStackTrace();
			// TODO 这里需要进行异常处理
		}
	}
}