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
import com.hm.domain.mc.McBasMessage;

/**
 * 短信表 DAO interface 接口
 * 
 * @author ZUOXP
 * 
 */
@Component
public interface IMcBasMessageDao extends HibernateDao<McBasMessage> {

	/**
	 * 根据 <br />
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送短信<br />
	 * STATUS Status 发送状态（1：待发送；2：已发送）<br />
	 * 返回表T_BAS_MC_MESSAGE 短信表的数据列表<br />
	 * 开发者 ZUOXP 2014年11月27日 15:58:58
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
	@Find(" from McBasMessage where 1=1 and #{to_char(sendTime,'yyyy-mm-dd hh24-mi-ss') <= to_char(:SendTime,'yyyy-mm-dd hh24-mi-ss') or sendTime is null}")
	public List<McBasMessage> findBySendTimeStatus(
			@Param("SendTime") Date sendTime);

	/**
	 * 根据 <br />
	 * RECEIVER Receiver 短信接收号码多个电话可按照”,”分隔<br />
	 * 返回表T_BAS_MC_MESSAGE 消息中心_短信表的数据列表<br />
	 * 开发者 zuoxp 2014年12月08日 15:57:37
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
	@Find(" from McBasMessage where 1=1  #{and lower(receiver) like :Receiver}  #{order by :order :dir}")
	public PageList<McBasMessage> findByReceiver(
			@Like(value = "Receiver", prefix = false, suffix = true) java.lang.String Receiver,  //短信接收号码多个电话可按照”,”分隔
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

}
