package com.hm.svcImpl.webservice;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.domain.comm.McConstantUtil;
import com.hm.util.CommonParam;

/**
 * WEBSERVICE客户端代码.这里使用的是XFIRE进行客户端调用.
 * 运行环境： * 1.需要JDK. * 2.需要导入XFIRE的jar以及依赖的jar包. *
 */
@Service
@Transactional
public class SMSXfireClient {

	/**
	 * 调用WEBSERVICE的入口方法.
	 * 
	 * @param args
	 *            参数列表
	 */

	public JSONObject sendMcToSMS(String tel, String content, String sendtime,
			String grpId, String mid, String sid) {

		JSONObject obj = null;

		try {
			content = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// 接口参数值,格式为JSON格式的字符串
		JSONObject json = new JSONObject();
		json.put("grp_id", grpId);
		json.put("msg", content);
		json.put("sid", sid);
		json.put("sendtime", sendtime);
		json.put("mid", mid);
		json.put("tel", tel);
		String jsonStr = json.toString();
		CommonParam commparam = new CommonParam();

		try {
			// 创建客户端对象
			Client client = new Client(new URL(commparam.getString("SMS_URL")));
			// 构造参数对象
			Object[] param = new Object[] {commparam.getString("SMS_KEY"), jsonStr, McConstantUtil.RETURN_TYPE_JSON };
			// 调用远程接口方法
			Object[] results = client.invoke(commparam.getString("SMS_METHOD"), param);
			// 判断结果不为空且长度大于0
			if (results != null && results.length > 0) {
				// 返回结果
				obj = JSONObject.fromObject(results[0]);
//				// 获取返回值执行结果的标志位
//				String flag = obj.get("flag").toString();
//				// flag=1表示执行成功
//				if (McConstantUtil.SUCCESS_FLAG.equals(flag)) {
//					// 获取返回值内容
//					String msg = obj.get("msg").toString();
//					// 打印返回值内容
//					System.out.println("msg=====" + msg);
//					// TODO 这里可以进行业务逻辑操作
//				} else {
//					// 获取返回值中的错误信息
//					String error = obj.get("error").toString();
//					// 打印错误信息内容
//					System.out.println("error=====" + error);
//					// TODO 这里可以进行错误的处理逻辑
//				}
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
		return obj;
	}
}