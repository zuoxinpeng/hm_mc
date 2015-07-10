package com.hm.svc.tem;

import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.hm.domain.tem.McTemplateDetail;

/**
 * 消息中心_模板明细表 server接口
 * 
 * @author 王凯
 * 
 */
public interface IMcTemplateDetailService extends
		HibernateService<McTemplateDetail> {

	/**
	 * 根据 <br />
	 * TEMPLATE_ID TemplateId 模板ID<br />
	 * 返回表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的数据列表<br />
	 * 开发者 左鑫鹏 2014年11月21日 15:36:21
	 * 
	 * @param TemplateId
	 *            模板ID
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McTemplateDetail> findByTemplateCode(
			String templateCode);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID Id 模板ID<br />
	 * TEMPLATE_TYPE_ID TypeId 消息类型ID<br />
	 * 获取表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的对象<br />
	 * 开发者 王凯 2014年11月24日 15:01:46
	 * 
	 * @param id
	 *            模板ID
	 * @param typeId
	 *            消息类型ID
	 * @return McTemplateDetail对象实体
	 */
	public abstract McTemplateDetail getByTemplateIdTypeId(java.lang.Long id, // 模板ID
			java.lang.Long typeId // 消息类型ID
	);

	/**
	 * <ul>
	 * <li>1、开发日期：2014-11-26</li>
	 * <li>2、开发时间：上午9:06:29</li>
	 * <li>3、作 者：lim.jong.uk</li>
	 * <li>4、返回类型：McTemplateDetail</li>
	 * <li>5、方法含义：</li>
	 * <li>6、方法说明：</li>
	 * </ul>
	 * 
	 * @param templateEncode
	 * @param TemplateTypeId
	 * @return
	 */
	public abstract McTemplateDetail getByTemplateEncodeAndTypeId(
			String templateEncode, java.lang.Long templateTypeId);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID Id 模板ID<br />
	 * 删除数据表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表中的内容<br />
	 * 开发者 王凯 2014年12月19日 14:00:31
	 * 
	 * @param Id
	 *            模板ID
	 */
	public abstract int deleteByTemplateId(java.lang.Long templateId // 模板ID
	);

	/**
	 * 根据 <br />
	 * 返回表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的数据列表<br />
	 * 开发者 王凯 2014年12月22日 15:18:12
	 * 
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McTemplateDetail> getAllMcTemplateDetail();

}
