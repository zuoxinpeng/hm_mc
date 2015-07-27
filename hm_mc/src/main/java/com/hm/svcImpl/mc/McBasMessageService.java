package com.hm.svcImpl.mc;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.aggrepoint.dao.PageList;
import com.hm.dao.mc.IMcBasMessageDao;
import com.hm.domain.mc.McBasMessage;
import com.hm.svc.mc.IMcBasMessageService;

/**
 * 短信表 server实现
 * 
 * @author ZUOXP
 * 
 */
@Service
@Transactional
public class McBasMessageService extends HibernateServiceBase<McBasMessage>
		implements IMcBasMessageService {
	@Autowired
	private IMcBasMessageDao theDao;

	public HibernateDao<McBasMessage> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送短信<br />
	 * STATUS Status 发送状态（1：待发送；2：已发送）<br />
	 * 返回表T_BAS_MC_MESSAGE 短信表的数据列表<br />
	 * 开发者 ZUOXP 2014年11月27日 16:04:04
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
	public List<McBasMessage> findBySendTimeStatus(Date sendTime) {
		return theDao.findBySendTimeStatus(sendTime);
	}

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 短信接收号码多个电话可按照”,”分隔<br />
	 * 返回表T_BAS_MC_MESSAGE 消息中心_短信表的数据列表<br />
	 * 开发者 zuoxp 2014年12月08日 15:53:44
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
	public PageList<McBasMessage> findByReceiver(java.lang.String Receiver, // 短信接收号码多个电话可按照”,”分隔
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
