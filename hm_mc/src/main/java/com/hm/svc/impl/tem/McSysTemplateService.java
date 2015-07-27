package com.hm.svc.impl.tem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.hm.dao.tem.IMcSysTemplateDao;
import com.hm.domain.tem.McSysTemplate;
import com.hm.svc.tem.IMcSysTemplateService;

/**
 * 消息中心_系统和模板对应关系表 server实现
 * 
 * @author 左鑫鹏
 * 
 */
@Service
@Transactional
public class McSysTemplateService extends HibernateServiceBase<McSysTemplate> implements IMcSysTemplateService {
	@Autowired
	IMcSysTemplateDao theDao;

	@Override
	public HibernateDao<McSysTemplate> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * 返回表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表的数据列表<br />
	 * 开发者 王凯 2014年12月03日 09:58:19
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McSysTemplate> findBy(String order, // 排序字段
			String orderDir// 排序顺序
	) {
		return theDao.findBy(order, orderDir);
	}

	/**
	 * 根据 <br />
	 * SYST_ID Id 系统ID<br />
	 * TEMPLATE_ID Id 模板ID<br />
	 * 获取表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表的对象<br />
	 * 开发者 王凯 2014年12月03日 13:35:22
	 * 
	 * @param sysId
	 *            系统ID
	 * @param templateId
	 *            模板ID
	 * @return McSysTemplate对象实体
	 */
	public McSysTemplate getById(Long sysId, Long templateId) {
		return theDao.getById(sysId, templateId);
	}

	/**
	 * 根据 <br />
	 * SYS_TEMPLATE_ID TemplateId SYS_TEMPLATE_ID<br />
	 * 删除数据表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表中的内容<br />
	 * 开发者 王凯 2014年12月03日 14:32:45 用于模板取消分配(删除)
	 * 
	 * @param sysTemplateId
	 *            SYS_TEMPLATE_ID
	 */
	public int deleteBySysTemplateId(java.lang.Long sysTemplateId // SYS_TEMPLATE_ID
	) {
		return theDao.deleteBySysTemplateId(sysTemplateId);
	}

	/**
	 * 根据 <br />
	 * TEMPLATE_ID templateId 模板ID<br />
	 * 删除数据表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表中的内容<br />
	 * 开发者 王凯 2014年12月22日 09:49:26
	 * 
	 * @param templateId
	 *            模板ID
	 */
	public int deleteByTemplateId(java.lang.Long templateId // 模板ID
	) {
		return theDao.deleteByTemplateId(templateId);
	}

	/**
	 * 根据 <br />
	 * SYST_ID Id 系统ID<br />
	 * (用于根据系统id查询出所有分配的模板) 返回表MC_BAS_SYS_TEMPLATE 消息中心_系统和模板对应关系表的数据列表<br />
	 * 开发者 王凯 2015年01月21日 15:00:56
	 * 
	 * @param systId
	 *            系统ID
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McSysTemplate> findBysystId(java.lang.Long systId) // 系统ID
	{
		return theDao.findBysystId(systId);
	}
}
