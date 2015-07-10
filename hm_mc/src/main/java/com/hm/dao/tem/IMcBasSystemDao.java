package com.hm.dao.tem;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Param;
import com.aggrepoint.dao.annotation.Replace;
import com.hm.domain.tem.McBasSystem;

/**
 * 消息中心_系统码表 DAO interface 接口
 * 
 * @author 左鑫鹏
 * 
 */
@Component
public interface IMcBasSystemDao extends HibernateDao<McBasSystem> {

	/**
	 * 根据 <br />
	 * 返回表T_MC_BAS_SYSTEM 消息中心_系统码表的数据列表<br />
	 * 开发者 王凯 2014年11月19日 14:39:42
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find("from McBasSystem where 1=1  #{order by :order :dir}")
	public List<McBasSystem> findBy(@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * SYST_CODE Code 系统代码<br />
	 * 返回表MC_BAS_SYSTEM 消息中心_系统表的数据列表<br />
	 * 开发者 王凯  2015年01月21日 13:00:07
	 * 
	 * @param systCode
	 *            系统代码
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McBasSystem where 1=1  #{and lower(systCode) = :systCode}")
	public List<McBasSystem> findByCode(@Param("systCode") java.lang.String systCode);// 系统代码);

}
