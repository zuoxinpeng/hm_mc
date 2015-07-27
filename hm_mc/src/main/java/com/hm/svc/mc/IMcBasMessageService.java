package com.hm.svc.mc;

import java.util.Date;
import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.aggrepoint.dao.PageList;
import com.hm.domain.mc.McBasMessage;

/**
 * 短信表 server接口
 * 
 * @author ZUOXP
 * 
 */
public interface IMcBasMessageService extends HibernateService<McBasMessage> {
	/**
	 * 根据 <br />
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送短信<br />
	 * STATUS Status 发送状态（1：待发送；2：已发送）<br />
	 * 返回表T_BAS_MC_MESSAGE 短信表的数据列表<br />
	 * 开发者 ZUOXP 2014年11月27日 16:05:17
	 * 
	 * @param SendTime
	 *            需要延迟发送的时间，不传为立即发送短信
	 * @param Status
	 *            发送状态（1：待发送；2：已发送）
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McBasMessage> findBySendTimeStatus(Date sendTime);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 短信接收号码多个电话可按照”,”分隔<br />
	 * 返回表T_BAS_MC_MESSAGE 消息中心_短信表的数据列表<br />
	 * 开发者 zuoxp 2014年12月08日 15:53:23
	 * 
	 * @param Receiver
	 *            短信接收号码多个电话可按照”,”分隔
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
	public abstract PageList<McBasMessage> findByReceiver(java.lang.String Receiver, // 短信接收号码多个电话可按照”,”分隔
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	);

}
