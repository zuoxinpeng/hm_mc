package com.hm.svc.tem;

import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.aggrepoint.dao.PageList;
import com.hm.domain.tem.McTemplate;

/**
 * 消息中心_模板表 server接口
 * 
 * @author 左鑫鹏
 * 
 */
public interface IMcTemplateService extends HibernateService<McTemplate> {

	/**
	 * 根据 <br />
	 * TEMPLATE_ENCODE templateEncode 模板编码<br />
	 * 返回表T_MC_TEMPLATE 消息中心_模板表的数据列表<br />
	 * 开发者 左鑫鹏 2014年11月21日 15:45:49
	 * 
	 * @param templateEncode
	 *            模板编码
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McTemplate> findByTemplateEncode(java.lang.String templateEncode);

	/**
	 * 根据 <br />
	 * TEMPLATE_ENCODE Encode 模板编码<br />
	 * 返回表T_MC_TEMPLATE 消息中心_模板表的数据列表<br />
	 * 开发者 王凯 2014年11月24日 13:22:07
	 * 
	 * @param Encode
	 *            模板编码
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
	public PageList<McTemplate> findByEncode(java.lang.String encode, // 模板编码
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID Id 模板主键<br />
	 * 删除数据表T_MC_TEMPLATE 消息中心_模板表中的内容<br />
	 * 开发者 王凯 2014年12月19日 11:46:38
	 * 
	 * @param templateId
	 *            模板主键
	 */
	public abstract int deleteById(java.lang.Long templateId);// 模板主键
	

}
