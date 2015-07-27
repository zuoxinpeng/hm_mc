package com.hm.svc.imo;

import java.util.Date;
import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.aggrepoint.dao.PageList;
import com.hm.domain.mc.McBasImo;

/**
 * 信息中心IMO接口
 * 
 * 左鑫鹏 2014年11月21日14:42:20
 * 
 * */

public interface IMcBasImoService extends HibernateService<McBasImo> {

	/**
	 * 根据 <br />
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送IMO<br />
	 * STATUS Status 发送状态（1：待发送；2：已发送）<br />
	 * 返回表BAS_MC_IMO IMO表的数据列表<br />
	 * 开发者 王凯 2015年1月06日 14:54:17
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
	public abstract List<McBasImo> findBySendTimeStatus(Date sendTime);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver IMO接收人（邮箱的前缀拼音组成）<br />
	 * 返回表MC_BAS_IMO 消息中心_IMO表的数据列表<br />
	 * 开发者 王凯 2015年01月06日 10:05:19
	 * 
	 * @param Receiver
	 *            IMO接收人（邮箱的前缀拼音组成）
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract PageList<McBasImo> findByReceiver(java.lang.String receiver, // IMO接收人
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	);

	/**
	 * sendMsg
	 * 
	 * @param uid
	 *            消息接收者id
	 * @param title
	 *            消息标题
	 * @param content
	 *            消息内容
	 * @return 接口返回结果 String
	 * @exception
	 * @since 1.0.0
	 */
	public String sendMsg(String uid, String title, String content);

	/**
	 * 通过员工账号获取员工信息
	 * 
	 * @param uas
	 *            所要查询的人员账号（员工账号逗号隔开，最多支持一次获取100个成员）
	 * @return result 接口返回结果
	 */
	public String getUserInfoByAccounts(String uas);

	/**
	 * 通过员工id获取员工信息
	 * 
	 * @param uids
	 *            所要查询的人员id（员工id逗号隔开，最多支持一次获取100个成员）
	 * @return result 接口返回结果
	 */
	public String getUserInfoByIds(String uids);
}
