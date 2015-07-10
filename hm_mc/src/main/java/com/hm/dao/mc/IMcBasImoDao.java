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
import com.hm.domain.mc.McBasImo;

/**
 * 消息中心_IMO表（成功） DAO interface 接口
 * 
 * @author 王凯
 * 
 */
@Component
public interface IMcBasImoDao extends HibernateDao<McBasImo> {

	
	/**
	 * 用于扫描表的查询
	 * 根据 <br />
	 * SEND_TIME SendTime 需要延迟发送的时间，不传为立即发送IMO<br />
	 * STATUS Status 发送状态（1：待发送；2：已发送）<br />
	 * 返回表BAS_MC_IMO IMO表的数据列表<br />
	 * 开发者 王凯 2015年1月06日 14:58:28
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
	@Find(" from McBasImo where 1=1 and #{to_char(sendTime,'yyyy-mm-dd hh24-mi-ss') <= to_char(:SendTime,'yyyy-mm-dd hh24-mi-ss') or sendTime is null}")
	public List<McBasImo> findBySendTimeStatus(
			@Param("SendTime") Date sendTime);
	
	/**
	 * 根据 <br />
	 * RECEIVER Receiver IMO接收人（邮箱的前缀拼音组成）<br />
	 * 返回表MC_BAS_IMO 消息中心_IMO表的数据列表<br />
	 * 开发者 王凯 2015年01月06日 09:59:50
	 * 
	 * @param Receiver
	 *            IMO接收人（邮箱的前缀拼音组成）
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McBasImo where 1=1  #{and lower(receiver) like :receiver}  #{order by :order :dir}")
	public PageList<McBasImo> findByReceiver(
			@Like("receiver") java.lang.String receiver, // IMO接收人（邮箱的前缀拼音组成）
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

}
