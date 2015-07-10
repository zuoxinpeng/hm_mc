package com.hm.svc.log;

import com.aggrepoint.dao.HibernateService;
import com.aggrepoint.dao.PageList;
import com.hm.domain.mc.McBasLog;

/**
 * 日志表 server接口
 * 
 * @author zuoxp
 * 
 */
public interface IMcBasLogService extends HibernateService<McBasLog> {

	/**
	 * 根据 <br />
	 * CONTENT Content 日志内容<br />
	 * PRIORITY Priority 日志重要程度（1.重要、2.一般、3.可忽略）<br />
	 * 返回表T_BAS_MC_LOG 日志表的数据列表<br />
	 * 开发者 王凯 2014年12月17日 16:44:41
	 * 
	 * @param content
	 *            日志内容
	 * @param priority
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
	public abstract PageList<McBasLog> findByContentPriority(
			java.lang.String content, // 日志内容
			java.lang.Long priority, // 日志重要程度（1.重要、2.一般、3.可忽略）
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	);
	
	/**
	 * 添加日志
	 * @param basMcLog
	 */
	public abstract void insertBasMcLog(McBasLog basMcLog);

}
