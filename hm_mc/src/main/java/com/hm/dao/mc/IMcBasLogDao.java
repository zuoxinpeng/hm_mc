package com.hm.dao.mc;

import org.springframework.stereotype.Component;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Like;
import com.aggrepoint.dao.annotation.PageNum;
import com.aggrepoint.dao.annotation.PageSize;
import com.aggrepoint.dao.annotation.Param;
import com.aggrepoint.dao.annotation.Replace;
import com.hm.domain.mc.McBasLog;

/**
 * 日志表 DAO interface 接口
 * 
 * @author zuoxp
 * 
 */
@Component
public interface IMcBasLogDao extends HibernateDao<McBasLog> {

	/**
	 * 根据 <br />
	 * CONTENT Content 日志内容<br />
	 * PRIORITY Priority 日志重要程度（1.重要、2.一般、3.可忽略）<br />
	 * 返回表T_BAS_MC_LOG 日志表的数据列表<br />
	 * 开发者 王凯 2014年12月17日 16:38:07
	 * 
	 * @param Content
	 *            日志内容
	 * @param Priority
	 *            日志重要程度（1.重要、2.一般、3.可忽略）
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
	@Find(" from McBasLog where 1=1  #{and lower(content) like :content}  #{and priority = :priority}  #{order by :order :dir}")
	public PageList<McBasLog> findByContentPriority(@Like("content") java.lang.String content, // 日志内容
			@Param("priority") java.lang.Long priority, // 日志重要程度（1.重要、2.一般、3.可忽略）
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);
}
