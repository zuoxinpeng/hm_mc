package com.hm.svc.mc;

import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.aggrepoint.dao.PageList;
import com.hm.domain.mc.McBasEmailSuccess;

/**
 * 消息中心_邮件表（成功） server接口
 * 
 * @author ZUOXP
 * 
 */
public interface IMcBasEmailSuccessService extends HibernateService<McBasEmailSuccess> {

	/**
	 * 根据 <br />
	 * SEND_TIME Time 发送开始时间<br />
	 * 返回表T_BAS_MC_EMAIL_SUCCESS 消息中心_邮件表（成功）的数据列表<br />
	 * 开发者 王凯 2014年12月15日 18:29:36
	 * 
	 * @param Time
	 *            发送开始时间
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McBasEmailSuccess> findByTime(java.util.Date sendTimeStart, // 发送开始时间
			java.util.Date sendTimeEnd, // 发送开始时间
			String order, // 排序字段
			String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 邮件接收人<br />
	 * 返回表T_BAS_MC_EMAIL_SUCCESS 消息中心_邮件表（成功）的数据列表<br />
	 * 开发者 王凯 2014年12月30日 11:00:07
	 * 
	 * @param receiver
	 *            邮件接收人
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
	public abstract PageList<McBasEmailSuccess> findByReceiver(java.lang.String receiver, // 邮件接收人
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	);

}
