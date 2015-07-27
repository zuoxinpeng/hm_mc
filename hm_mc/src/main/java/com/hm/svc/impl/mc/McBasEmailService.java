package com.hm.svc.impl.mc;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.aggrepoint.dao.PageList;
import com.hm.dao.mc.IMcBasEmailDao;
import com.hm.domain.mc.McBasEmail;
import com.hm.svc.mc.IMcBasEmailService;

/**
 * 消息中心_邮件表 server实现
 * 
 * @author ZUOXP
 * 
 */
@Service
@Transactional
public class McBasEmailService extends HibernateServiceBase<McBasEmail> implements IMcBasEmailService {
	@Autowired
	private IMcBasEmailDao theDao;

	public HibernateDao<McBasEmail> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送邮件<br />
	 * STATUS Status 发送状态（1：待发送；2：已发送）<br />
	 * 返回表T_BAS_MC_EMAIL 邮件表的数据列表<br />
	 * 开发者 ZUOXP 2014年11月27日 16:04:04
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
	public List<McBasEmail> findBySendTimeStatus(Date sendTime) {
		return theDao.findBySendTimeStatus(sendTime);
	}

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 邮件接收人<br />
	 * 返回表T_BAS_MC_EMAIL 消息中心_邮件表的数据列表<br />
	 * 开发者 王凯 2014年12月30日 16:17:47
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
	public PageList<McBasEmail> findByReceiver(java.lang.String receiver, // 邮件接收人
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	) {
		if (receiver != null)
			receiver = receiver.toLowerCase().trim();
		return theDao.findByReceiver(receiver, order, orderDir, pageNum, pageSize);
	}

}
