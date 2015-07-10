package com.hm.dao.tem;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Param;
import com.aggrepoint.dao.annotation.Replace;
import com.hm.domain.tem.McSysTemplate;

/**
 * 消息中心_系统和模板对应关系表 DAO interface 接口
 * 
 * @author 左鑫鹏
 * 
 */
@Component
public interface IMcSysTemplateDao extends HibernateDao<McSysTemplate> {

	/**
	 * 根据 <br />
	 * 返回表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表的数据列表<br />
	 * 开发者 王凯 2014年12月03日 09:56:23
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McSysTemplate where 1=1  #{order by :order :dir}")
	public List<McSysTemplate> findBy(@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir// 排序顺序
	);

	/**
	 * 根据 <br />
	 * SYST_ID Id 系统ID<br />
	 * TEMPLET_ID Id 模板ID<br />
	 * 获取表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表的对象<br />
	 * 开发者 王凯 2014年12月03日 13:32:28
	 * 
	 * @param Id
	 *            系统ID
	 * @param Id
	 *            模板ID
	 * @return McSysTemplate对象实体
	 */
	@Find(" from McSysTemplate where 1=1  and systId = :systId  and templateId = :templateId  ")
	public McSysTemplate getById(@Param("systId") java.lang.Long systId, // 系统ID
			@Param("templateId") java.lang.Long templateId // 模板ID
	);

	/**
	 * 根据 <br />
	 * SYS_TEMPLET_ID TemplateId SYS_TEMPLATE_ID<br />
	 * 删除数据表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表中的内容<br />
	 * 开发者 王凯 2014年12月03日 14:28:36 用于模板取消分配(删除)
	 * 
	 * @param sysTemplateId
	 *            SYS_TEMPLATE_ID
	 */
	@Delete("delete McSysTemplate where sysTemplateId = :sysTemplateId ")
	public int deleteBySysTemplateId(
			@Param("sysTemplateId") java.lang.Long sysTemplateId // SYS_TEMPLET_ID
	);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID templateId 模板ID<br />
	 * 删除数据表T_MC_SYS_TEMPLATE 消息中心_系统和模板对应关系表中的内容<br />
	 * 开发者 王凯 2014年12月22日 09:42:35 根据模板id删除表中相对应的关系
	 * 
	 * @param templateId
	 *            模板ID
	 */
	@Delete("delete McSysTemplate where templateId = :templateId ")
	public int deleteByTemplateId(@Param("templateId") java.lang.Long templateId // 模板ID
	);

	/**
	 * 根据 <br />
	 * SYST_ID Id 系统ID<br />
	 * 返回表MC_BAS_SYS_TEMPLATE 消息中心_系统和模板对应关系表的数据列表<br />
	 * 开发者 王凯 2015年01月21日 14:57:02
	 * 
	 * @param Id
	 *            系统ID
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McSysTemplate where 1=1  #{and systId = :systId}")
	public List<McSysTemplate> findBysystId(@Param("systId") java.lang.Long systId); // 系统ID



}
