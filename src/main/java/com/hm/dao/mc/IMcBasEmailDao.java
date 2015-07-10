package com.hm.dao.mc;

import java.util.Date;
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
import com.hm.domain.mc.McBasEmail;

/**
 * 消息中心_邮件表 DAO interface 接口
 * 
 * @author ZUOXP
 * 
 */
@Component
public interface IMcBasEmailDao extends HibernateDao<McBasEmail> {

	/**
	 * 根据 <br />
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送邮件<br />
	 * STATUS Status 发送状态（1：待发送；2：已发送）<br />
	 * 返回表T_BAS_MC_EMAIL 邮件表的数据列表<br />
	 * 开发者 ZUOXP 2014年11月27日 15:58:58
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
	@Find(" from McBasEmail where 1=1 and #{to_char(sendTime,'yyyy-mm-dd hh24-mi-ss') <= to_char(:SendTime,'yyyy-mm-dd hh24-mi-ss') or sendTime is null}")
	public List<McBasEmail> findBySendTimeStatus(
			@Param("SendTime") Date sendTime);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 邮件接收人<br />
	 * 返回表T_BAS_MC_EMAIL 消息中心_邮件表的数据列表<br />
	 * 开发者 王凯 2014年12月30日 16:16:10
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
	@Find(" from McBasEmail where 1=1  #{and lower(receiver) like :receiver}  #{order by :order :dir}")
	public PageList<McBasEmail> findByReceiver(
			@Like(value = "receiver", prefix = false, suffix = true) java.lang.String receiver,  //短邮件接收人可按照”,”分隔 
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

}
