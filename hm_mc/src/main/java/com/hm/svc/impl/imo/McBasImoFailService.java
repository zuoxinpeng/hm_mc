package com.hm.svc.impl.imo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.aggrepoint.dao.PageList;
import com.hm.dao.mc.IMcBasImoFailDao;
import com.hm.domain.mc.McBasImoFail;
import com.hm.svc.imo.IMcBasImoFailService;

/**
 * 消息中心_IMO表（失败） server实现
 * 
 * @author 王凯
 * 
 */
@Service
@Transactional
public class McBasImoFailService extends HibernateServiceBase<McBasImoFail> implements IMcBasImoFailService {
	@Autowired
	private IMcBasImoFailDao theDao;

	public HibernateDao<McBasImoFail> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * SEND_TIME Time 发送开始时间<br />
	 * 返回表MC_BAS_IMO_FAIL 消息中心_IMO表（失败）的数据列表<br />
	 * 开发者 王凯 2015年01月05日 16:02:35
	 * 
	 * @param Time
	 *            发送开始时间
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McBasImoFail> findByTime(java.util.Date sendTimeStart, // 发送开始时间
			java.util.Date sendTimeEnd, // 发送开始时间
			String order, // 排序字段
			String orderDir// 排序顺序
	) {
		return theDao.findByTime(sendTimeStart, sendTimeEnd, order, orderDir);
	}

	/**
	 * 根据 <br />
	 * RECEIVER Receiver IMO接收人<br />
	 * 返回表MC_BAS_IMO_FAIL 消息中心_IMO表（失败）的数据列表<br />
	 * 开发者 王凯 2015年01月05日 16:08:48
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
	public PageList<McBasImoFail> findByReceiver(java.lang.String receiver, // IMO接收人
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
