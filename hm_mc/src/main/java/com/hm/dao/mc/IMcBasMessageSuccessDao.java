package com.hm.dao.mc;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Like;
import com.aggrepoint.dao.annotation.PageNum;
import com.aggrepoint.dao.annotation.PageSize;
import com.aggrepoint.dao.annotation.Param;
import com.aggrepoint.dao.annotation.Replace;
import com.hm.domain.mc.McBasMessageSuccess;

/**
 * 短信表（成功） DAO interface 接口
 * 
 * @author ZUOXP
 * 
 */
@Component
public interface IMcBasMessageSuccessDao extends HibernateDao<McBasMessageSuccess> {

	/**
	 * 根据 <br />
	 * SEND_TIME Time 发送开始时间<br />
	 * 返回表T_BAS_MC_MESSAGE_SUCCESS 消息中心_短信表（成功）的数据列表<br />
	 * 开发者 王凯 2014年12月15日 10:54:29
	 * 
	 * @param sendTimeStart
	 *            发送开始时间 (查询的使用的开始时间)
	 * @param sendTimeEnd
	 *            发送开始时间(查询使用的结束时间)
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McBasMessageSuccess where 1=1  #{and createTime >= :sendTimeStart and createTime <= :sendTimeEnd}  #{order by :order :dir}")
	public List<McBasMessageSuccess> findByTime(@Param("sendTimeStart") java.util.Date sendTimeStart, // 发送开始时间
																										// (查询的使用的开始时间)
			@Param("sendTimeEnd") java.util.Date sendTimeEnd, // 发送开始时间(查询使用的结束时间)
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 短信接收人<br />
	 * 返回表T_BAS_MC_MESSAGE_SUCCESS 消息中心_短信表（成功）的数据列表<br />
	 * 开发者 王凯 2014年12月26日 16:47:43
	 * 
	 * @param Receiver
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
	@Find(" from McBasMessageSuccess where 1=1  #{and lower(receiver) like :receiver}  #{order by :order :dir}")
	public PageList<McBasMessageSuccess> findByReceiver(@Like(value = "receiver", prefix = false, suffix = true) java.lang.String receiver, // 短信接收号码多个电话可按照”,”分隔
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

}
