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

/**
 * <ul>
 * <li>1、开发日期：2014-11-24</li>
 * <li>2、开发时间：下午4:37:19</li>
 * <li>3、作 者： ZUOXP</li>
 * <li>4、类型名称：testmain</li>
 * <li>5、类型意图：</li>
 * </ul>
 * 
 */
public class EmailHessionClient2 {
	public static void main(String[] args) throws MalformedURLException {

		String url = "http://10.10.1.177:8080/messagecenter/mcMessageServer";
		HessianProxyFactory factory = new HessianProxyFactory();
		IMcMessageService mcMessageService = (IMcMessageService) factory.create(IMcMessageService.class, url);

		/* 参数组装 */
		ArrayList<BasMcParameters> mcParametersList = new ArrayList<BasMcParameters>();
		BasMcParameters basMcParameters = new BasMcParameters();
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "左鑫鹏测试邮件-8");
		map.put("info", "原来的节目档信息：<br/>原来的节目档开始时间：2015-07-08 11:00:00<br/>原来的节目档结束时间：2015-07-08 11:59:59<br/>原来的节目档播放性质：直播<br/>原来的节目档商品：<br/>原来的节目档商品编号：308451<br/>原来的节目档商品名称：缘缘小精灵净水器特惠组<br/><br/>新的节目档信息：<br/>新节目档开始时间：2015-07-08 11:00:00<br/>新节目档结束时间：2015-07-08 11:59:59<br/>新节目档播放性质：直播<br/>新节目档商品信息：<br/>新节目档商品编号：115758<br/>新节目档商品名称：乐扣乐扣百纳箱超值套装<br/><br/><br/>");
		ArrayList<String> receiver = new ArrayList<String>();
		receiver.add("531952358@qq.com");

		basMcParameters.setTemplateCode("mc_test");
		basMcParameters.setSystemId("IV");
		basMcParameters.setSystemPw("07b62034beeabdb7");
		// basMcParameters.setKey(McConstantUtil.SMS_KEY);
		// basMcParameters.setGrpId(McConstantUtil.SMS_GRP_ID);
		// basMcParameters.setSendTime("2014-12-11 13:12:00");
		basMcParameters.setParametersMap(map);
		basMcParameters.setReceiver(receiver);
		basMcParameters.setMessageType(McConstantUtil.MESSAGETYPE_YJ);
		mcParametersList.add(basMcParameters);

		// BasMcParameters basMcParameters2 = new BasMcParameters();
		// Map<String,String> map2 = new HashMap<String,String>();
		// map2.put("username", "刘强");
		// map2.put("str", "王君");
		// ArrayList<String> receiver2 = new ArrayList<String>();
		// receiver2.add("65031443@qq.com");
		// receiver2.add("1528886703@qq.com");
		// basMcParameters2.setTemplateCode("sp0001");
		// basMcParameters2.setKey(McConstantUtil.SMS_KEY);
		// basMcParameters2.setGrpId(McConstantUtil.SMS_GRP_ID);
		// basMcParameters2.setSendTime("2014-12-11 13:12:00");
		// basMcParameters2.setParametersMap(map2);
		// basMcParameters2.setReceiver(receiver2);
		// basMcParameters2.setMessageType(McConstantUtil.MESSAGETYPE_YJ);
		// mcParametersList.add(basMcParameters2);

		BaseOutEntity json = mcMessageService.sendMcMessage(mcParametersList);
		System.out.println(json.getCode());
		System.out.println(json.getMsg());
		System.out.println(json.toString());
	}
}
