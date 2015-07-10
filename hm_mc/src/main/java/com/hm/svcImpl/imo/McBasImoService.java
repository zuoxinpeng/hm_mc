package com.hm.svcImpl.imo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.aggrepoint.dao.PageList;
import com.hm.dao.mc.IMcBasImoDao;
import com.hm.domain.mc.McBasImo;
import com.hm.svc.imo.IMcBasImoService;
import com.hm.util.CommonParam;

@Service
@Transactional
public class McBasImoService extends HibernateServiceBase<McBasImo> implements
		IMcBasImoService {
	
	private static final Logger logger = LoggerFactory.getLogger(McBasImoService.class);
	
	@Autowired
	private IMcBasImoDao theDao;

	public HibernateDao<McBasImo> getDao() {
		return theDao;
	}
	
	CommonParam param = new CommonParam();
	
	/**
	 * 获取组织架构信息
	 * 
	 * @param did
	 *            所要组织id（传入0显示企业所有组织架构，传入非0只显示输入组织架构ID的信息）
	 * @return result 接口返回结果
	 */
	public String getCorpInfo(String did) {
		StringBuffer url = new StringBuffer("http://" + param.getString("IMO_SERVER_HOST")
				+ "OpenPlatform/sync/getsync.php");
		url.append("?cid=");
		url.append(param.getString("IMO_CID"));
		url.append("&appKey=");
		url.append(param.getString("SYNC_KEY"));
		url.append("&appcid=");
		url.append(param.getString("IMO_CID"));
		url.append("&did=");
		url.append(did);
		url.append("&evno=1");
		String result = exeRequest(url.toString());
		return result;
	}

	/**
	 * 获取部门信息
	 * 
	 * @param dids
	 *            所要查询的部门id（部门id逗号隔开，最多支持一次获取100个部门）
	 * @return result 接口返回结果
	 */
	public String getDeptInfo(String dids) {
		StringBuffer url = new StringBuffer("http://" + param.getString("IMO_SERVER_HOST")
				+ "/OpenPlatform/sync/getsync.php");
		url.append("?cid=");
		url.append(param.getString("IMO_CID"));
		url.append("&appKey=");
		url.append(param.getString("SYNC_KEY"));
		url.append("&appcid=");
		url.append(param.getString("IMO_CID"));
		url.append("&dids=");
		url.append(dids);
		url.append("&evno=4");
		String result = exeRequest(url.toString());
		return result;
	}

	/**
	 * 获取部门详细信息
	 * 
	 * @param dids
	 *            所要查询的部门id（部门id逗号隔开，最多支持一次获取100个部门）
	 * @return result 接口返回结果
	 */
	@SuppressWarnings("unchecked")
	public String getDetailDeptInfo(String did) {
		StringBuffer url = new StringBuffer("http://" + param.getString("IMO_SERVER_HOST")
				+ "/OpenPlatform/sync/getsync.php");
		url.append("?cid=");
		url.append(param.getString("IMO_CID"));
		url.append("&appKey=");
		url.append(param.getString("SYNC_KEY"));
		url.append("&appcid=");
		url.append(param.getString("IMO_CID"));
		url.append("&did=");
		url.append(did);
		url.append("&evno=1");
		String result = exeRequest(url.toString());

		JSONObject obj2 = JSONObject.fromObject(result);
		System.out.println("用户组成员：" + obj2.get("uids").toString());

		List<String> list = (List<String>) obj2.get("uids");
		List<String> list2 = new ArrayList<String>();
		for (String s : list) {
			list2.add(s);
		}
		System.out.println(list2.toString().substring(1,
				list2.toString().length() - 1));

		return result;
	}

	/**
	 * 通过员工id获取员工信息
	 * 
	 * @param uids
	 *            所要查询的人员id（员工id逗号隔开，最多支持一次获取100个成员）
	 * @return result 接口返回结果
	 */
	public String getUserInfoByIds(String uids) {
		StringBuffer url = new StringBuffer("http://" + param.getString("IMO_SERVER_HOST")
				+ "/OpenPlatform/sync/getsync.php");
		url.append("?cid=");
		url.append(param.getString("IMO_CID"));
		url.append("&appKey=");
		url.append(param.getString("SYNC_KEY"));
		url.append("&appcid=");
		url.append(param.getString("IMO_CID"));
		url.append("&uids=");
		url.append(uids);
		url.append("&evno=2");
		String result = exeRequest(url.toString());
		return result;
	}

	/**
	 * 通过员工账号获取员工信息
	 * 
	 * @param uas
	 *            所要查询的人员账号（员工账号逗号隔开，最多支持一次获取100个成员）
	 * @return result 接口返回结果
	 */
	public String getUserInfoByAccounts(String uas) {
		StringBuffer url = new StringBuffer("http://" + param.getString("IMO_SERVER_HOST")
				+ "/OpenPlatform/sync/getsync.php");
		url.append("?cid=");
		url.append(param.getString("IMO_CID"));
		url.append("&appKey=");
		url.append(param.getString("SYNC_KEY"));
		url.append("&appcid=");
		url.append(param.getString("IMO_CID"));
		url.append("&uas=");
		url.append(uas);
		url.append("&evno=2");
		String result = exeRequest(url.toString());
		return result;
	}

	/**
	 * 获取企业总人数
	 * 
	 * @return result 接口返回结果
	 */
	public String getUserCount() {
		StringBuffer url = new StringBuffer("http://" + param.getString("IMO_SERVER_HOST")
				+ "/OpenPlatform/sync/getsync.php");
		url.append("?cid=");
		url.append(param.getString("IMO_CID"));
		url.append("&appKey=");
		url.append(param.getString("SYNC_KEY"));
		url.append("&appcid=");
		url.append(param.getString("IMO_CID"));
		url.append("&evno=23");
		String result = exeRequest(url.toString());
		return result;
	}

	/**
	 * 添加员工
	 * 
	 * @param JSONObject
	 *            user 用户参数
	 * @return 接口返回信息
	 */
	public String addUser(JSONObject user) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
//			byte[] base64User = Base64.encode(user.toString().getBytes());
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&user=");
//			params.append(base64User);
			params.append("&evno=5");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 修改员工信息
	 * 
	 * @param String
	 *            account员工账号（也可以传用户id即uid，，二者选一。这里为了方便我们只测试account）
	 * @param JSONObject
	 *            user
	 * @return String 接口返回信息
	 */
	public String updateUser(String account, JSONObject user) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
//			byte[] base64User = Base64.encode(user.toString().getBytes());
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&uaccount=");
			params.append(account);
			params.append("&user=");
//			params.append(base64User);
			params.append("&evno=6");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 删除员工
	 * 
	 * @param String
	 *            account 员工账号
	 * @param String
	 *            did 员工所在部门id
	 * @return String 接口返回结果
	 */
	public String delUser(String account, String did) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&uaccount=");
			params.append(account);
			params.append("&did=");
			params.append(did);
			params.append("&evno=7");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 移动员工
	 * 
	 * @param moveAccount
	 *            移动员工的账号
	 * @param fromDid
	 *            员工的原部门id
	 * @param toDid
	 *            员工移动后的部门id
	 * @return 接口返回结果
	 */
	public String moveUser(String moveAccount, String fromDid, String toDid) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&MoveUa=");
			params.append(moveAccount);
			params.append("&FromParentId=");
			params.append(fromDid);
			params.append("&ToParentId=");
			params.append(toDid);
			params.append("&evno=13");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 添加部门
	 * 
	 * @param JSONObject
	 *            dept 部门参数
	 * @return 接口返回信息
	 */
	public String addDept(JSONObject dept) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
//			byte[] base64Dept = Base64.encode(dept.toString().getBytes());
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&dept=");
//			params.append(base64Dept);
			params.append("&evno=8");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 修改部门信息
	 * 
	 * @param String
	 *            did部门id
	 * @param JSONObject
	 *            dept部门参数
	 * @return String 接口返回信息
	 */
	public String updateDept(String did, JSONObject dept) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
//			byte[] base64Dept = Base64.encode(dept.toString().getBytes());
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&did=");
			params.append(did);
			params.append("&dept=");
//			params.append(base64Dept);
			params.append("&evno=9");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 删除部门
	 * 
	 * @param String
	 *            did 部门id
	 * @return String 接口返回结果
	 */
	public String delDept(String did) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&did=");
			params.append(did);
			params.append("&evno=10");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 移动部门
	 * 
	 * @param did
	 *            部门id
	 * @param fromParentId
	 *            原父部门的id
	 * @param toParentId
	 *            移动后父部门的id
	 * @return 接口返回结果
	 */
	public String moveDept(String did, String fromParentId, String toParentId) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&DeptId=");
			params.append(did);
			params.append("&FromParentId=");
			params.append(fromParentId);
			params.append("&ToParentId=");
			params.append(toParentId);
			params.append("&evno=14");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 修改企业信息
	 * 
	 * @param JSONObject
	 *            cInfo 企业参数
	 * @return 接口返回结果
	 */
	public String updateCompanyInfo(JSONObject cInfo) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/sync/sync.php";
		StringBuffer result = new StringBuffer();
		try {
//			byte[] base64cInfo = Base64.encode(cInfo.toString().getBytes());
			URL sendUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) sendUrl
					.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);
			// 指定提交方式
			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			// 组装提交参数
			StringBuffer params = new StringBuffer();
			params.append("cid=");
			params.append(param.getString("IMO_CID"));
			params.append("&appKey=");
			params.append(param.getString("SYNC_KEY"));
			params.append("&appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&cinfo=");
//			params.append(base64cInfo);
			params.append("&evno=11");
			objOutStrm.write(params.toString().getBytes());
			objOutStrm.flush();
			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 推送消息
	 * 
	 * @param uid
	 *            消息接收者id
	 * @param content
	 *            消息内容
	 * @return 接口返回结果
	 */
	public String sendMsg(String uid, String title, String content) {
		String url = "http://" + param.getString("IMO_SERVER_HOST") + "/OpenPlatform/index.php?app=sendmsg";
		System.out.println(url);
		StringBuffer result = new StringBuffer();
		try {
			JSONObject user = new JSONObject();
			user.put("cid", param.getString("IMO_CID"));
			user.put("uid", uid);
			String strUser = "[" + user.toString() + "]";
			JSONObject msg = new JSONObject();
			msg.put("ver", "1.0");
			msg.put("title", title);
			msg.put("desc", content);
			msg.put("lnk", "");
			msg.put("img", "");

			StringBuffer params = new StringBuffer();
			params.append("fromcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&");
			params.append("fromuid=");
			params.append("1000");
			params.append("&");
			params.append("appcid=");
			params.append(param.getString("IMO_CID"));
			params.append("&");
			params.append("appuid=");
			params.append("1000");
			params.append("&");
			params.append("appkey=");
			params.append(param.getString("MSG_KEY"));
			params.append("&");
			params.append("pushtype=");
			params.append("1");
			params.append("&");
			params.append("users=");
			params.append(strUser);
			params.append("&");
			params.append("msg=");
			params.append(URLEncoder.encode(msg.toString(), "UTF-8"));
			params.append("&");
			params.append("sender=");
			params.append("394893849");
			params.append("&");
			params.append("chart=");
			params.append("UTF8");
			params.append("&");
			params.append("poptype");
			params.append("1");
			URL sendUrl = new URL(url);
			
			HttpURLConnection connection = (HttpURLConnection) sendUrl.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			OutputStream objOutStrm = connection.getOutputStream();
			objOutStrm.write(params.toString().getBytes());

			objOutStrm.flush();

			objOutStrm.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			connection.connect();
			String temp = "";
			while ((temp = in.readLine()) != null) {
				result.append(temp);
			}

			in.close();
		} catch (Exception e) {
			logger.error("McBasImoService sendMsg error:" + e.getMessage(), e);
		}
		logger.info(result.toString());
		return result.toString();
	}

	/**
	 * 执行接口请求的方法
	 * 
	 * @param String
	 *            reqUrl 请求地址
	 * @return String 接口返回结果
	 */
	public String exeRequest(String reqUrl) {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				response.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response.toString();
	}
	
	
	//----------------------------------------------------------
	
	/**
	 * 根据 <br />用于表的定时扫描查询
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送邮件<br />
	 * STATUS Status 发送状态（1：待发送；2：已发送）<br />
	 * 返回表T_BAS_MC_EMAIL 邮件表的数据列表<br />
	 * 开发者 王凯 2015年1月06日 14:58:04
	 * 
	 * @param SendTime
	 *            需要延迟发送的时间，不传为立即发送邮件
	 * @param Status
	 *            发送状态（1：待发送；2：已发送）
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McBasImo> findBySendTimeStatus(Date sendTime) {
		return theDao.findBySendTimeStatus(sendTime);
	}
	
	
	/**
	 * 根据 <br />
	 * RECEIVER Receiver IMO接收人（邮箱的前缀拼音组成）<br />
	 * 返回表MC_BAS_IMO 消息中心_IMO表的数据列表<br />
	 * 开发者 wk 2015年01月06日 10:07:30
	 * 
	 * @param Receiver
	 *            IMO接收人（邮箱的前缀拼音组成）
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @param pageNum
	 *            当前分页的页数
	 * @param pageSize
	 *            每页条数
	 * @return 数据列表的强类型实体类
	 */
	public PageList<McBasImo> findByReceiver(java.lang.String Receiver, // IMO接收人（邮箱的前缀拼音组成）
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	) {
		if (Receiver != null)
			Receiver = Receiver.toLowerCase().trim();
		return theDao.findByReceiver(Receiver, order, orderDir, pageNum,
				pageSize);
	}

}
