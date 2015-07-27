package com.hm.dao.tem;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Like;
import com.aggrepoint.dao.annotation.PageNum;
import com.aggrepoint.dao.annotation.PageSize;
import com.aggrepoint.dao.annotation.Param;
import com.aggrepoint.dao.annotation.Replace;
import com.hm.domain.tem.McTemplate;

/**
 * 消息中心_模板表 DAO interface 接口
 * 
 * @author 左鑫鹏
 * 
 */
@Component
public interface IMcTemplateDao extends HibernateDao<McTemplate> {

	/**
	 * 根据 <br />
	 * TEMPLATE_ENCODE TemplateEncode 模板编码<br />
	 * 返回表T_MC_TEMPLATE 消息中心_模板表的数据列表<br />
	 * 开发者 左鑫鹏 2014年11月21日 15:44:37
	 * 
	 * @param TemplateEncode
	 *            模板编码
	 * 
	 * @param order
	 *            排序字段
	 * 
	 * @param orderDir
	 *            排序顺序
	 * 
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McTemplate where 1=1  #{and lower(templateEncode) = :templateEncode}")
	public List<McTemplate> findByTemplateEncode(@Param("templateEncode") java.lang.String templateEncode);

	/**
	 * 根据 <br />
	 * TEMPLATE_ENCODE Encode 模板编码<br />
	 * 返回表T_MC_TEMPLATE 消息中心_模板表的数据列表<br />
	 * 开发者 王凯 2014年11月24日 11:43:44
	 * 
	 * @param Encode
	 *            模板编码
	 * 
	 * @param order
	 *            排序字段
	 * 
	 * @param orderDir
	 *            排序顺序
	 * 
	 * @param pageNum
	 *            当前分页的页数
	 * 
	 * @param pageSize
	 *            每页条数
	 * 
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McTemplate where 1=1  #{and templateEncode like :templateEncode}  #{order by :order :dir}")
	public PageList<McTemplate> findByEncode(@Like("templateEncode") java.lang.String templateEncode, // 模板编码
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

	/**
	 * 根据 <br />
	 * TEMPLATE_ID Id 模板主键<br />
	 * 删除数据表T_MC_TEMPLATE 消息中心_模板表中的内容<br />
	 * 开发者 王凯 2014年12月19日 11:45:13
	 * 
	 * @param Id
	 *            模板主键
	 */
	@Delete("delete McTemplate where templateId = :templateId ")
	public int deleteById(@Param("templateId") java.lang.Long templateId);// 模板主键

}
