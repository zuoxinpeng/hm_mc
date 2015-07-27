/**
 * <p>项目名称：com-hm-mc<p>
 * <ul>
 * <li>1、版权所有：</li>
 * <li>2、开发日期：2014-11-24</li>
 * <li>3、开发时间：下午4:37:19</li>
 * <li>4、作          者：lim.jong.uk</li>
 * <li>5、包路径名：com.hm.mc</li>
 * <li>6、文件名称：testmain.java</li>
 * </ul>
 */
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
import com.hm.util.CommonParam;

/**
 * <ul>
 * <li>1、开发日期：2014-11-24</li>
 * <li>2、开发时间：下午4:37:19</li>
 * <li>3、作 者： zuoxp</li>
 * <li>4、类型名称：testmain</li>
 * <li>5、类型意图：</li>
 * </ul>
 * 
 */
public class McHessionClient {
	public static void main(String[] args) throws MalformedURLException {

		String url = "http://localhost:8080/messagecenter/mcMessageServer";
		HessianProxyFactory factory = new HessianProxyFactory();
		IMcMessageService mcMessageService = (IMcMessageService) factory.create(IMcMessageService.class, url);
		CommonParam commparam = new CommonParam();
		/* 参数组装 */
		ArrayList<BasMcParameters> mcParametersList = new ArrayList<BasMcParameters>();
		BasMcParameters basMcParameters = new BasMcParameters();
		Map<String, String> map = new HashMap<String, String>();
		map.put("function", "test1");
		map.put("identityCode", "1234566");
		map.put("time", "3");
		ArrayList<String> receiver = new ArrayList<String>();
		receiver.add("18510512801");

		basMcParameters.setTemplateCode("hm_user3");
		basMcParameters.setKey(commparam.getString("SMS_KEY"));
		basMcParameters.setSystemId("IV");
		basMcParameters.setSystemPw("07b62034beeabdb72");
		basMcParameters.setGrpId("42");
		basMcParameters.setSendTime("2014-12-01 13:12:00");
		basMcParameters.setParametersMap(map);
		basMcParameters.setReceiver(receiver);
		basMcParameters.setMessageType(McConstantUtil.MESSAGETYPE_DX);
		mcParametersList.add(basMcParameters);

		BaseOutEntity json = mcMessageService.sendMcMessage(mcParametersList);
		System.out.println(json.toString());
	}
}
