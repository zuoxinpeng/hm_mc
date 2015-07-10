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
import com.hm.domain.mc.McBasImoSuccess;

/**
 * 消息中心_IMO表（成功） DAO interface 接口
 * 
 * @author 王凯
 * 
 */
@Component
public interface IMcBasImoSuccessDao extends HibernateDao<McBasImoSuccess> {

	/**
	 * 根据 <br />(消息统计查询中用到该方法)
	 * SEND_TIME Time 发送开始时间<br />
	 * 返回表MC_BAS_IMO_SUCCESS 消息中心_IMO表（成功）的数据列表<br />
	 * 开发者 王凯 2015年01月05日 14:43:51
	 * 
	 * @param sendTimeStart
	 *            用于区间查询的发送开始时间
	 * @param sendTimeEnd
	 * 			   用于区间查询的发送开始时间
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McBasImoSuccess where 1=1  #{and createTime >= :sendTimeStart and createTime <= :sendTimeEnd}  #{order by :order :dir}")
	public List<McBasImoSuccess> findByTime(
			@Param("sendTimeStart") java.util.Date sendTimeStart, // 查询使用发送开始时间
			@Param("sendTimeEnd") java.util.Date sendTimeEnd, // 查询使用发送结束时间
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver IMO接收人（邮箱的前缀拼音组成）<br />
	 * 返回表MC_BAS_IMO_SUCCESS 消息中心_IMO表（成功）的数据列表<br />
	 * 开发者 王凯 2015年01月05日 15:27:43
	 * 
	 * @param Receiver
	 *            IMO接收人（邮箱的前缀拼音组成）
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
	@Find(" from McBasImoSuccess where 1=1  #{and lower(receiver) like :receiver}  #{order by :order :dir}")
	public PageList<McBasImoSuccess> findByReceiver(
			@Like("receiver") java.lang.String receiver,  // IMO接收人（邮箱的前缀拼音组成）
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

}
