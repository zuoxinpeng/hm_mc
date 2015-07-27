package com.hm.svc.impl.webservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.domain.comm.Encoding;
import com.hm.util.CommonParam;
import com.hm.util.HttpUtils;

/**
 * <p>
 * 中台短信发送服务
 * </p>
 * 
 * @version $Id: ZtSmsService.java 2015-06-19 14:14:54Z xupengji
 * @author xupengji
 */
@Service
@Transactional
public class ZtSmsService {
	// private static Logger logger =
	// LoggerFactory.getLogger(ZtSmsService.class);

	/**
	 * 短信发送主方法
	 * 
	 * @param tel
	 *            <电话号码>,smsMsg<发送短信内容>,corpMsgId<用户发送短信时自己定义的短信id，用于区分状态报告。可为空>
	 *            ,ext<用户自行分配小号。可为空>
	 */
	public JSONObject sendSms(String tel, String smsMsg, String corpMsgId, String ext) throws IOException {
		JSONObject resultJson = new JSONObject();
		CommonParam commparam = new CommonParam();

		// 判断当前短信字数限制
		String maxNum = commparam.getString("SMS_MAX_NUM");
		if (smsMsg.length() > Integer.valueOf(maxNum)) {
			resultJson.put("flag", "102");
			return resultJson;
		}

		// 判断手机号是否合法
		if (StringUtils.isBlank(tel) || tel.length() > 11) {
			resultJson.put("flag", "103");
			return resultJson;
		}

		// 调用发送短信接口
		String smsSendUrl = commparam.getString("SMS_SEND_URL");
		String corpId = commparam.getString("SMS_CORP_ID");
		String corpPwd = commparam.getString("SMS_CORP_PWD");
		String corpService = commparam.getString("SMS_CORP_SERVICE");
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("corp_id", corpId);
		paramsMap.put("corp_pwd", corpPwd);
		paramsMap.put("corp_service", corpService);
		paramsMap.put("mobile", tel);
		paramsMap.put("msg_content", smsMsg);
		paramsMap.put("corp_msg_id", corpMsgId);
		paramsMap.put("ext", ext);
		String smsResult = null;
		try {
			smsResult = HttpUtils.doPost(smsSendUrl, paramsMap, Encoding.GB2312);
		} catch (IOException e) {
			throw new IOException("短信发送接口调用异常，异常信息为：" + e.getMessage());
		}

		if (StringUtils.isBlank(smsResult))
			throw new IOException("接口发送调用失败，未知错误！");

		// 重构短信发送接口返回值
		String resultSms = smsResult.split("#")[0];
		if (StringUtils.equals(resultSms, "0")) {
			resultJson.put("flag", "1");
			JSONObject msgJson = new JSONObject();
			msgJson.put("suc", smsResult.split("#")[1]);
			resultJson.put("msg", msgJson);
		} else {
			resultJson.put("flag", resultSms);
		}
		return resultJson;
	}

}
