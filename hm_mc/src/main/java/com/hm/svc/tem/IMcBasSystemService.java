package com.hm.svc.tem;

import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.hm.domain.tem.McBasSystem;

/**
 * 消息中心_系统码表 server接口
 * 
 * @author 左鑫鹏
 * 
 */
public interface IMcBasSystemService extends HibernateService<McBasSystem> {

	/**
	 * 根据 <br />
	 * 返回表T_MC_BAS_SYSTEM 消息中心_系统码表的数据列表<br />
	 * 开发者 王凯 2014年11月19日 14:40:29
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McBasSystem> findBy(String order, // 排序字段
			String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * SYST_CODE systCode 系统代码<br />
	 * 返回表MC_BAS_SYSTEM 消息中心_系统表的数据列表<br />
	 * 开发者 王凯 2015年01月21日 13:01:49
	 * 
	 * @param Code
	 *            系统代码
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McBasSystem> findByCode(java.lang.String systCode); // 系统代码
	

}
