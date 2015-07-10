package com.hm.svc.imo;

import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.aggrepoint.dao.PageList;
import com.hm.domain.mc.McBasImoFail;

/**
 * 消息中心_IMO表（失败） server接口
 * 
 * @author 王凯
 * 
 */
public interface IMcBasImoFailService extends HibernateService<McBasImoFail> {

	/**
	 * 根据 <br />
	 * SEND_TIME Time 发送开始时间<br />
	 * 返回表MC_BAS_IMO_FAIL 消息中心_IMO表（失败）的数据列表<br />
	 * 开发者 王凯 2015年01月05日 15:59:07
	 * 
	 * @param Time
	 *            发送开始时间
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McBasImoFail> findByTime(java.util.Date sendTimeStart, // 发送开始时间
			java.util.Date sendTimeEnd, // 发送开始时间
			String order, // 排序字段
			String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver IMO接收人<br />
	 * 返回表MC_BAS_IMO_FAIL 消息中心_IMO表（失败）的数据列表<br />
	 * 开发者 王凯 2015年01月05日 16:06:52
	 * 
	 * @param Receiver
	 *            IMO接收人
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
	public abstract PageList<McBasImoFail> findByReceiver(
			java.lang.String receiver, // IMO接收人
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	);

}
