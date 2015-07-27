package com.hm.svc.impl.tem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.hm.dao.tem.IMcTemplateDetailDao;
import com.hm.domain.tem.McTemplate;
import com.hm.domain.tem.McTemplateDetail;
import com.hm.svc.tem.IMcTemplateDetailService;
import com.hm.svc.tem.IMcTemplateService;

/**
 * 消息中心_模板明细表 server实现
 * 
 * @author 王凯
 * 
 */
@Service
@Transactional
public class McTemplateDetailService extends HibernateServiceBase<McTemplateDetail> implements IMcTemplateDetailService {
	@Autowired
	private IMcTemplateDetailDao theDao;
	@Autowired
	private IMcTemplateService mcTemplateService;

	public HibernateDao<McTemplateDetail> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * TEMPLATE_ID TempletId 模板ID<br />
	 * 返回表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的数据列表<br />
	 * 开发者 左鑫鹏 2014年11月21日 15:36:40
	 * 
	 * @param TempletId
	 *            模板ID
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McTemplateDetail> findByTemplateCode(String templateCode) {

		List<McTemplate> mcTemplateList = mcTemplateService.findByTemplateEncode(templateCode);
		List<McTemplateDetail> mcTemplateDetailList = null;

		if (mcTemplateList != null && mcTemplateList.size() > 0) {
			long templateId = mcTemplateList.get(0).getTemplateId();
			mcTemplateDetailList = theDao.findByTemplateId(templateId) == null ? null : theDao.findByTemplateId(templateId);
		}

		return mcTemplateDetailList;
	}

	/**
	 * 根据 <br />
	 * TEMPLATE_ID templetId 模板ID<br />
	 * TEMPLET_TYPE_ID TypeId 消息类型ID<br />
	 * 获取表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的对象<br />
	 * 开发者 王凯 2014年11月24日 15:08:34
	 * 
	 * @param templetId
	 *            模板ID
	 * @param typeId
	 *            消息类型ID
	 * @return McTemplateDetail对象实体
	 */

	public McTemplateDetail getByTemplateIdTypeId(Long templateId, Long typeId) {
		return theDao.getByTemplateIdTypeId(templateId, typeId);
	}

	/**
	 * 根据 <br />
	 * TEMPLATE_TYPE_ID TemplateTypeId 消息类型ID<br />
	 * 获取表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的对象<br />
	 * 开发者 ZUOXP 2014年11月26日 09:02:59
	 * 
	 * @param templateEncode
	 *            模板编码
	 * @param TempletTypeId
	 *            消息类型ID
	 * @return McTemplateDetail对象实体
	 */
	public McTemplateDetail getByTemplateEncodeAndTypeId(String templateEncode, java.lang.Long templateTypeId) {
		return theDao.getByTemplateEncodeAndTypeId(templateEncode, templateTypeId);
	}

	/**
	 * 根据 <br />
	 * TEMPLATE_ID Id 模板ID<br />
	 * 删除数据表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表中的内容<br />
	 * 开发者 王凯 2014年12月19日 14:01:16
	 * 
	 * @param Id
	 *            模板ID
	 */
	public int deleteByTemplateId(java.lang.Long templateId // 模板ID
	) {
		return theDao.deleteByTemplateId(templateId);
	}

	/**
	 * 根据 <br />
	 * 返回表T_MC_TEMPLATE_DETAIL 消息中心_模板明细表的数据列表<br />
	 * 开发者 wk 2014年12月22日 15:19:16
	 *
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McTemplateDetail> getAllMcTemplateDetail() {
		return theDao.getAllMcTemplateDetail();
	}

}
