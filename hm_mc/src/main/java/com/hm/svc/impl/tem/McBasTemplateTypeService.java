package com.hm.svc.impl.tem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.hm.dao.tem.IMcBasTempletTypeDao;
import com.hm.domain.tem.McBasTemplateType;
import com.hm.svc.tem.IMcBasTemplateTypeService;

/**
 * 消息中心_短信、邮件、站内信、IMO、自由格式模板 server实现
 *
 * @author 左鑫鹏
 *
 */
@Service
@Transactional
public class McBasTemplateTypeService extends HibernateServiceBase<McBasTemplateType> implements IMcBasTemplateTypeService {
	@Autowired
	private IMcBasTempletTypeDao theDao;

	@Override
	public HibernateDao<McBasTemplateType> getDao() {
		return theDao;
	}

}
