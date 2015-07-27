package com.hm.dao.tem;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Param;
import com.hm.domain.tem.McTemplateDetail;

/**
 * 消息中心_模板明细表 DAO interface 接口
 * 
 * @author 王凯
 * 
 */
@Component
public interface IMcTemplateDetailDao extends HibernateDao<McTemplateDetail> {

	/**
	 * 根据 <br />
	 * TEMPLATE_ID TemplateId 模板ID<br />
	 * 返回表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的数据列表<br />
	 * 开发者 左鑫鹏 2014年11月21日 15:34:42
	 * 
	 * @param TemplateId
	 *            模板ID
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McTemplateDetail where 1=1 and templateId = :templateId")
	public List<McTemplateDetail> findByTemplateId(@Param("templateId") java.lang.Long templateId);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID Id 模板ID<br />
	 * TEMPLATE_TYPE_ID TypeId 消息类型ID<br />
	 * 获取表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的对象<br />
	 * 开发者 王凯 2014年11月24日 15:01:03
	 * 
	 * @param Id
	 *            模板ID
	 * @param TypeId
	 *            消息类型ID
	 * @return McTemplateDetail对象实体
	 */
	@Find(" from McTemplateDetail where 1=1  and templateId = :templateId  and templateTypeId = :templateTypeId")
	public McTemplateDetail getByTemplateIdTypeId(@Param("templateId") java.lang.Long templateId, // 模板ID
			@Param("templateTypeId") java.lang.Long templateTypeId // 消息类型ID
	);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID TemplateId 模板ID<br />
	 * TEMPLATE_TYPE_ID TemplateTypeId 消息类型ID<br />
	 * 获取表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的对象<br />
	 * 开发者 ZUOXP 2014年11月26日 08:56:16
	 * 
	 * @param TemplateId
	 *            模板ID
	 * @param TemplateTypeId
	 *            消息类型ID
	 * @return McTemplateDetail对象实体
	 */
	@Find(" from McTemplateDetail where 1=1  and templateId = (select m.templateId from McTemplate m where m.templateEncode = :templateEncode) and templateTypeId = :templateTypeId  ")
	public McTemplateDetail getByTemplateEncodeAndTypeId(@Param("templateEncode") String templateEncode, // 模板code
			@Param("templateTypeId") java.lang.Long templateTypeId // 消息类型ID
	);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID Id 模板ID<br />
	 * 删除数据表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表中的内容<br />
	 * 开发者 王凯 2014年12月19日 13:59:03
	 * 
	 * @param templateId
	 *            模板ID
	 */
	@Delete("delete McTemplateDetail where templateId = :templateId ")
	public int deleteByTemplateId(@Param("templateId") java.lang.Long templateId // 模板ID
	);

	/**
	 * 根据 <br />
	 * 返回表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的数据列表<br />
	 * 开发者 王凯 2014年12月22日 15:17:24
	 * 
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McTemplateDetail where 1=1")
	public List<McTemplateDetail> getAllMcTemplateDetail();

}
