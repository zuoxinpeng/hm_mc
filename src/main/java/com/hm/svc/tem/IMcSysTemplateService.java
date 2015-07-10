package com.hm.svc.tem;

import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.hm.domain.tem.McSysTemplate;

/**
 * 消息中心_系统和模板对应关系表 server接口
 * 
 * @author 左鑫鹏
 * 
 */
public interface IMcSysTemplateService extends HibernateService<McSysTemplate> {
	/**
	 * 根据 <br />
	 * 返回表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表的数据列表<br />
	 * 开发者 王凯 2014年12月03日 09:57:36
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McSysTemplate> findBy(String order, // 排序字段
			String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * SYST_ID Id 系统ID<br />
	 * TEMPLATE_ID Id 模板ID<br />
	 * 获取表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表的对象<br />
	 * 开发者 王凯 2014年12月03日 13:34:06
	 * 
	 * @param Id
	 *            系统ID
	 * @param Id
	 *            模板ID
	 * @return McSysTemplate对象实体
	 */
	public abstract McSysTemplate getById(java.lang.Long systId, // 系统ID
			java.lang.Long templateId // 模板ID
	);

	/**
	 * 根据 <br />
	 * SYS_TEMPLATE_ID templateId SYS_TEMPLET_ID<br />
	 * 删除数据表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表中的内容<br />
	 * 开发者 王凯 2014年12月03日 14:28:58 用于模板取消分配(删除)
	 * 
	 * @param TempletId
	 *            SYS_TEMPLET_ID
	 */
	public abstract int deleteBySysTemplateId(java.lang.Long sysTemplateId // SYS_TEMPLET_ID
	);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID templateId 模板ID<br />
	 * 删除数据表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表中的内容<br />
	 * 开发者 王凯 2014年12月22日 09:48:18 根据模板id删除表中相对应的关系
	 * 
	 * @param templateId
	 *            模板ID
	 */
	public abstract int deleteByTemplateId(java.lang.Long templateId // 模板ID
	);

	/**
	 * 根据 <br />
	 * SYST_ID Id 系统ID<br />
	 * 返回表MC_BAS_SYS_TEMPLATE 消息中心_系统和模板对应关系表的数据列表<br />
	 * 开发者 王凯 2015年01月21日 14:59:54
	 * 
	 * @param Id
	 *            系统ID
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McSysTemplate> findBysystId(java.lang.Long systId); // 系统ID

}
