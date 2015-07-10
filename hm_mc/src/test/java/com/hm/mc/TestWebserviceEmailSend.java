package com.hm.mc;

import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;

public class TestWebserviceEmailSend {

	
		// 利用jsonobject拼接json
	  	 /*JSONObject json = new JSONObject();
	  	 json.put("grpId", "42");//(必填)组ID（网站、ivalue、比酷）
	  	 json.put("messageType","2");//(必填)消息类型（1：短信2：邮件3：站内信4：IMO）
	  	// json.put("parametersMap", "{'username':'丁俊晖','str':'第2'}");//(必填)模板参数
	  	 json.put("receiver", "[409393745@qq.com]");//可以设置多个接收人,(必填)传入电话号码list或者邮箱list
	  	 json.put("sId", "");//(选填)发送人ID
	  	 json.put("systemId", "ivalue");//(必填)系统ID，校验id是否合法
	  	 json.put("systemPw", "123");//(必填)系统密码，校验密码是否合法
	  	 json.put("templateCode", "ivalue");	//(必填)模板编码
	  	 json.put("templateFlag", "2");	//(必填)模板编码
	  	 */
	  	/* JSONObject json1 = new JSONObject();
	  	 json1.put("content", "");
	  	 json1.put("grpId", "");
	  	// json1.put("key", "qwefwrfqwf");
	  	 json1.put("linkUrl", "");
	  	 json1.put("mId", "");
	  	 json1.put("messageType","4");
	  	 json1.put("parametersMap", "{'username':'丁俊晖','str':'第1'}");
	  	 json1.put("receiver", "['zuoxinpeng','zuoxinpeng']");
	  	 json1.put("sId", "");
	  	 json1.put("systemId", "ivalue");
	  	 json1.put("systemPw", "123");
	  	 json1.put("templateCode", "ivalue");*/
	  	 
	  	 
		/*JSONArray jsonArray = JSONArray.fromObject(json); 	
		jsonArray  = JSONArray.fromObject(json1);
		System.out.println(jsonArray);
	  */
	public static void main(String[] args) throws Exception{
	  	 
		 JSONObject json = new JSONObject();
	  	 json.put("grpId", "42");//(必填)组ID（网站、ivalue、比酷）
	  	 json.put("messageType","2");//(必填)消息类型（1：短信2：邮件3：站内信4：IMO）
	  	 json.put("receiver", "['409393745@qq.com','wkshuobule@163.com']");//可以设置多个接收人,(必填)传入电话号码list或者邮箱list
	  	 json.put("systemId", "ivalue");//(必填)系统ID，校验id是否合法
	  	 json.put("systemPw", "123");//(必填)系统密码，校验密码是否合法
	  	 json.put("templateCode", "ivalue");	//(必填)模板编码
	  	 json.put("templateFlag", "2");	//(必填)模板是否替换标志
	  	 
	  	 
	  	 JSONObject json1 = new JSONObject();
	  	 json.put("grpId", "42");//(必填)组ID（网站、ivalue、比酷）
	  	 json1.put("messageType","4");
	  	 json1.put("parametersMap", "{'username':'丁俊晖','str':'第1'}");
	  	 json1.put("receiver", "['409393745@qq.com']");
	  	 json1.put("systemId", "ivalue");
	  	 json1.put("systemPw", "123");
	  	 json1.put("templateCode", "ivalue");
		 json1.put("templateFlag", "1");	//(必填)模板是否替换标志
	  	 
	  	//发送多条信息
	  	JSONArray jsonArray = new JSONArray();
		//jsonArray.add(json);//将JSONObject对象添加到Json数组中 
		jsonArray.add(json1);//将JSONObject对象添加到Json数组中 
		
	  	Client c=new Client(new URL("http://localhost:8090/messagecenter/service/ISendMcMessageService?wsdl"));
	  	Object[] results =c.invoke("sendMcMessage", new Object[]{jsonArray});
	  	System.out.println(results[0]);
	  	
	}

}
