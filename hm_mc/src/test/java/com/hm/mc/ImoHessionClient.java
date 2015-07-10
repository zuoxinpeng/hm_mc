package com.hm.mc;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.caucho.hessian.client.HessianProxyFactory;
import com.hm.domain.comm.BasMcParameters;
import com.hm.domain.comm.BaseOutEntity;
import com.hm.domain.comm.McConstantUtil;
import com.hm.svc.mc.IMcMessageService;


/**
 * <ul>
 * <li>1、开发日期：2015-01-07</li>
 * <li>2、开发时间：下午4:37:19</li>
 * <li>3、作 者：王凯</li>
 * <li>4、类型名称：testmain</li>
 * <li>5、类型意图：</li>
 * </ul>
 * hession客户端调用 测试调用成功
 */
public class ImoHessionClient {

	public static void main(String[] args) throws MalformedURLException {
		  String url = "http://localhost:8090/messagecenter/mcMessageServer";
		//String url = "http://10.0.97.62/messagecenter/mcMessageServer";
	        HessianProxyFactory factory = new HessianProxyFactory();  
	        IMcMessageService mcMessageService = (IMcMessageService) factory.create(IMcMessageService.class, url);
	        
			/* 参数组装*/
	        ArrayList<BasMcParameters> mcParametersList = new ArrayList<BasMcParameters>();
	        BasMcParameters basMcParameters = new BasMcParameters();
	        Map<String,String> map = new HashMap<String,String>();  
	        map.put("username", "测试"); 
	        map.put("str", "第一"); 
	        ArrayList<String> receiver = new ArrayList<String>();
	        receiver.add("zuoxinpeng");
	        
	        basMcParameters.setTemplateCode("ivalue");
//	        basMcParameters.setKey(McConstantUtil.SMS_KEY);
//	        basMcParameters.setGrpId(McConstantUtil.SMS_GRP_ID);
	        basMcParameters.setSendTime("2014-12-11 13:12:00");
	        basMcParameters.setParametersMap(map);
	        basMcParameters.setReceiver(receiver);
	        //basMcParameters.setMessageType(McConstantUtil.MESSAGETYPE_IMO);
	        basMcParameters.setMessageType(McConstantUtil.MESSAGETYPE_IMO);
	        basMcParameters.setSystemId("bk");
	        basMcParameters.setSystemPw("123");
	        
	        
	        BasMcParameters basMcParameters1 = new BasMcParameters();
	        Map<String,String> map1 = new HashMap<String,String>();  
	        map1.put("username", "丁俊晖"); 
	        map1.put("str", "第一"); 
	        ArrayList<String> receiver1 = new ArrayList<String>();
	        receiver1.add("zuoxinpeng");
	        
	        basMcParameters1.setTemplateCode("ivalue");
//	        basMcParameters.setKey(McConstantUtil.SMS_KEY);
//	        basMcParameters.setGrpId(McConstantUtil.SMS_GRP_ID);
//	        basMcParameters.setSendTime("2014-12-11 13:12:00");
	        basMcParameters1.setParametersMap(map);
	        basMcParameters1.setReceiver(receiver1);
	        basMcParameters1.setMessageType(McConstantUtil.MESSAGETYPE_IMO);
	        basMcParameters1.setSystemId("ivalue");
	        basMcParameters1.setSystemPw("123");
	        
	        
	        
//	        
	        mcParametersList.add(basMcParameters);
	        mcParametersList.add(basMcParameters1);
	        
	       // ArrayList<BasMcParameters> mcParametersList2 = new ArrayList<BasMcParameters>();//用于测试传递参数为null
	        
	        BaseOutEntity json = mcMessageService.sendMcMessage(mcParametersList);
	        System.out.println(json.toString());
	}
}
