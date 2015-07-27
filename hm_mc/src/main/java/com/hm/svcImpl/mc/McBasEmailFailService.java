package com.hm.svcImpl.mc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.aggrepoint.dao.PageList;
import com.hm.dao.mc.IMcBasEmailFailDao;
import com.hm.domain.mc.McBasEmailFail;
import com.hm.svc.mc.IMcBasEmailFailService;

/**
 * 消息中心_邮件表（失败） server实现
 * 
 * @author ZUOXP
 * 
 */
@Service
@Transactional
public class McBasEmailFailService extends HibernateServiceBase<McBasEmailFail>
		implements IMcBasEmailFailService {
	@Autowired
	private IMcBasEmailFailDao theDao;

	public HibernateDao<McBasEmailFail> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * SEND_TIME Time 发送开始时间<br />
	 * 返回表T_BAS_MC_EMAIL_FAIL 消息中心_邮件表（失败）的数据列表<br />
	 * 开发者 王凯 2014年12月15日 18:27:24
	 * 
	 * @param Time
	 *            发送开始时间
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McBasEmailFail> findByTime(java.util.Date sendTimeStart, // 发送开始时间
			java.util.Date sendTimeEnd, // 发送开始时间
			String order, // 排序字段
			String orderDir// 排序顺序
	) {
		return theDao.findByTime(sendTimeStart, sendTimeEnd, order, orderDir);
	}

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 邮件接收人<br />
	 * 返回表T_BAS_MC_EMAIL_FAIL 消息中心_邮件表（失败）的数据列表<br />
	 * 开发者王凯 2014年12月30日 14:51:39
	 * 
	 * @param Receiver
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
	public PageList<McBasEmailFail> findByReceiver(java.lang.String receiver, // 邮件接收人
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	) {
		if (receiver != null)
			receiver = receiver.toLowerCase().trim();
		return theDao.findByReceiver(receiver, order, orderDir, pageNum,pageSize);
	}

}
