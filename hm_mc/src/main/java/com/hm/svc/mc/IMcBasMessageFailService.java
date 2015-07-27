package com.hm.svc.mc;

import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.aggrepoint.dao.PageList;
import com.hm.domain.mc.McBasMessage;
import com.hm.domain.mc.McBasMessageFail;

/**
 * 短信表（未成功） server接口
 * 
 * @author ZUOXP
 * 
 */
public interface IMcBasMessageFailService extends HibernateService<McBasMessageFail> {

	public void saveBasMcMessageFail(McBasMessageFail basMcMessageFail, McBasMessage basMcMessage);

	/**
	 * 根据 <br />
	 * SEND_TIME Time 发送开始时间<br />
	 * 返回表T_BAS_MC_MESSAGE_FAIL 消息中心_短信表（未成功）的数据列表<br />
	 * 开发者 王凯 2014年12月15日 11:11:38
	 * 
	 * @param sendTimeStart
	 *            发送开始时间(查询的使用的开始时间)
	 * @param sendTimeEnd
	 *            发送开始时间(查询使用的结束时间)
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McBasMessageFail> findByTime(java.util.Date sendTimeStart, // 发送开始时间(查询的使用的开始时间)
			java.util.Date sendTimeEnd, // 发送开始时间(查询使用的结束时间)
			String order, // 排序字段
			String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 短信接收人<br />
	 * 返回表T_BAS_MC_MESSAGE_FAIL 消息中心_短信表（未成功）的数据列表<br />
	 * 开发者 王凯 2014年12月26日 17:17:49
	 * 
	 * @param receiver
	 *            短信接收人
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
	public abstract PageList<McBasMessageFail> findByReceiver(java.lang.String receiver, // 短信接收人
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	);

}
