package com.hm.mc;

import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;

/**
 * 
 * @author 王凯 测试调用发布的webservice 说明：传递的json字符串必须按照格式传递
 *         目前测为一个联系人或者多个联系人发送一条信息或者多条信息
 *         问题描述：测试一个联系人存在，一个联系人不存在，都放在一组发送人中，返回值该如何处理
 */

public class TestImoWebservice {

	public static void main(String[] args) throws Exception {
		// 利用jsonobject拼接json
		JSONObject json = new JSONObject();
		// JSONArray json = new JSONArray();
		// json.put("content", "");
		json.put("grpId", "");// (必填)组ID（网站、ivalue、比酷）
		// json.put("key", "qwefwrfqwf");
		// json.put("linkUrl", "");
		// json.put("mId", "");
		json.put("messageType", "4");// (必填)消息类型（1：短信2：邮件3：站内信4：IMO）
		json.put("parametersMap", "{\"username\":\"测试\",\"str\":\"测试\"}");// (必填)模板参数
		json.put("receiver", "[\"zuoxinpeng\",\"zuoxinpeng\"]");// 可以设置多个接收人,(必填)传入电话号码list或者邮箱list
		json.put("sId", "");// (选填)发送人ID
		json.put("systemId", "");// (选填)系统ID，校验id是否合法
		json.put("systemPw", "");// (选填)系统密码，校验密码是否合法
		json.put("templateCode", "ivalue001"); // (必填)模板编码

		JSONObject json1 = new JSONObject();
		json1.put("content", "");
		json1.put("grpId", "");
		// json1.put("key", "qwefwrfqwf");
		json1.put("linkUrl", "");
		json1.put("mId", "");
		json1.put("messageType", "4");
		json1.put("parametersMap", "{'username':'测试1','str':'测试1'}");
		json1.put("receiver", "['zuoxinpeng','wangkai']");
		json1.put("sId", "");
		json1.put("systemId", "");
		json1.put("systemPw", "");
		json1.put("templateCode", "ivalue001");

		/*
		 * JSONArray jsonArray = JSONArray.fromObject(json); jsonArray =
		 * JSONArray.fromObject(json1); System.out.println(jsonArray);
		 */

		// 发送多条短信使用
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(json);// 将JSONObject对象添加到Json数组中
		jsonArray.add(json1);// 将JSONObject对象添加到Json数组中
		String str = jsonArray.toString();
		Boolean b = str.startsWith("[");
		Boolean d = str.endsWith("]");
		System.out.println(b);
		System.out.println(d);
		// {"flag":"true","code":"104","msg":"消息中心发送消息成功。","totalNum":2,"succNum":2,"failNum":0}
		// 这种方法只是对简单参数有用
		Client c = new Client(new URL("http://172.16.33.13:8090/messagecenter/service/ISendMcMessageService?wsdl"));
		Object[] results = c.invoke("sendMcMessage", new Object[] { jsonArray });
		System.out.println(results[0]);
		System.exit(0);

		// JSONObject js = JSONObject.fromObject(results[0]);//获取的为json串

		// 如果想要获取每一个字段可以进行迭代
		/*
		 * Map m = js; Iterator<Map.Entry<String, Object>> entries =
		 * m.entrySet().iterator(); while (entries.hasNext()) { Entry<String,
		 * Object> entry = entries.next(); System.out.println("Key = " +
		 * entry.getKey() + ", Value = " + entry.getValue()); }
		 */

	}
}
