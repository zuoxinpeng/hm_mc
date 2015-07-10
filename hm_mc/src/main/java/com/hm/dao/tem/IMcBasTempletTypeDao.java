package com.hm.dao.tem;

import org.springframework.stereotype.Component;

import com.aggrepoint.dao.HibernateDao;
import com.hm.domain.tem.McBasTemplateType;

/**
 * 消息中心_短信、邮件、站内信、IMO、自由格式模板 DAO interface 接口
 *
 * @author 左鑫鹏
 *
 */
@Component
public interface IMcBasTempletTypeDao extends HibernateDao<McBasTemplateType> {

}
