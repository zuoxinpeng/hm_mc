package com.hm.svc.impl.tem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.hm.dao.tem.IMcBasSystemDao;
import com.hm.domain.tem.McBasSystem;
import com.hm.svc.tem.IMcBasSystemService;

/**
 * 消息中心_系统码表 server实现
 * 
 * @author 左鑫鹏
 * 
 */

@Service
@Transactional
public class McBasSystemService extends HibernateServiceBase<McBasSystem> implements IMcBasSystemService {
	@Autowired
	IMcBasSystemDao theDao;

	@Override
	public HibernateDao<McBasSystem> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * 返回表T_MC_BAS_SYSTEM 消息中心_系统码表的数据列表<br />
	 * 开发者 王凯 2014年11月19日 14:41:17
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McBasSystem> findBy(String order, // 排序字段
			String orderDir// 排序顺序
	) {
		return theDao.findBy(order, orderDir);
	}

	/**
	 * 根据 <br />
	 * SYST_CODE systCode 系统代码<br />
	 * 返回表MC_BAS_SYSTEM 消息中心_系统表的数据列表<br />
	 * 开发者 王凯 2015年01月21日 13:02:36
	 * 
	 * @param Code
	 *            系统代码
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McBasSystem> findByCode(java.lang.String systCode // 系统代码
	) {
		if (systCode != null)
			systCode = systCode.toLowerCase().trim();
		return theDao.findByCode(systCode);
	}

}
