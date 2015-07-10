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
import com.hm.domain.mc.McBasEmailFail;

/**
 * 消息中心_邮件表（失败） DAO interface 接口
 * 
 * @author ZUOXP
 * 
 */
@Component
public interface IMcBasEmailFailDao extends HibernateDao<McBasEmailFail> {

	/**
	 * 根据 发送时间查询<br />
	 * SEND_TIME Time 发送开始时间<br />
	 * 返回表T_BAS_MC_EMAIL_FAIL 消息中心_邮件表（失败）的数据列表<br />
	 * 开发者 王凯 2014年12月15日 18:24:46
	 * 
	 * @param sendTimeStart
	 *            用于区间查询的发送开始时间
	 *@param sendTimeEnd
	 *            用于区间查询的发送开始时间
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McBasEmailFail where 1=1  #{and createTime >= :sendTimeStart and createTime <= :sendTimeEnd}  #{order by :order :dir}")
	public List<McBasEmailFail> findByTime(
			@Param("sendTimeStart") java.util.Date sendTimeStart, // 发送开始时间
			@Param("sendTimeEnd") java.util.Date sendTimeEnd, // 发送开始时间
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 邮件接收人<br />
	 * 返回表T_BAS_MC_EMAIL_FAIL 消息中心_邮件表（失败）的数据列表<br />
	 * 开发者 王凯 2014年12月30日 14:46:28
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
	@Find(" from McBasEmailFail where 1=1  #{and lower(receiver) like :receiver}  #{order by :order :dir}")
	public PageList<McBasEmailFail> findByReceiver(
			@Like(value = "receiver", prefix = false, suffix = true) java.lang.String receiver,  //短邮件接收人可按照”,”分隔 
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

}
