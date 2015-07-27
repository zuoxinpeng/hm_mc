package com.hm.svc.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.HibernateDao;
import com.aggrepoint.dao.HibernateServiceBase;
import com.aggrepoint.dao.PageList;
import com.hm.dao.user.IBasMcPiuUserDao;
import com.hm.domain.user.McBasPiuUser;
import com.hm.svc.user.IMcBasPiuUserService;

/**
 * 消息中心内部用户表 server实现
 * 
 * @author zuoxp
 * 
 */
@Service
@Transactional
public class McBasPiuUserService extends HibernateServiceBase<McBasPiuUser> implements IMcBasPiuUserService {
	@Autowired
	private IBasMcPiuUserDao theDao;

	public HibernateDao<McBasPiuUser> getDao() {
		return theDao;
	}

	/**
	 * 根据 <br />
	 * LOGIN_ID Id 登录账号<br />
	 * PASSWORD Password 登录密码<br />
	 * 获取表T_BAS_MC_PIU_USER 消息中心内部用户表的对象<br />
	 * 开发者 王凯 2014年12月10日 13:31:25
	 * 
	 * @param Id
	 *            登录账号
	 * @param Password
	 *            登录密码
	 * @return BasMcPiuUser对象实体
	 */
	public McBasPiuUser getByIdPassword(String loginId, String password) {
		if (loginId != null)
			loginId = loginId.toLowerCase().trim();
		if (password != null)
			password = password.toLowerCase().trim();
		return theDao.getByIdPassword(loginId, password);
	}

	/**
	 * 根据 <br />
	 * USER_NAME Name 用户名称<br />
	 * 返回表T_BAS_MC_PIU_USER 消息中心内部用户表的数据列表<br />
	 * 开发者 王凯 2014年12月10日 15:21:34
	 * 
	 * @param loginId
	 *            登陆账号
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
	public PageList<McBasPiuUser> findByLoginId(java.lang.String loginId, // 登陆账号
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	) {
		if (loginId != null)
			loginId = loginId.toLowerCase().trim();
		return theDao.findByLoginId(loginId, order, orderDir, pageNum, pageSize);
	}

	/**
	 * 根据 <br />
	 * USER_ID Id 内部用户主键<br />
	 * 删除数据表T_BAS_MC_PIU_USER 消息中心内部用户表中的内容<br />
	 * 开发者 王凯 2014年12月10日 15:26:37
	 * 
	 * @param Id
	 *            内部用户主键
	 */
	public int deleteById(java.lang.Long userId // 内部用户主键
	) {
		return theDao.deleteById(userId);
	}

	/**
	 * 根据 <br />
	 * LOGIN_ID Id 登录账号<br />
	 * 返回表T_BAS_MC_PIU_USER 消息中心内部用户表的数据列表<br />
	 * 开发者 王凯 2014年12月11日 13:28:32
	 * 
	 * @param Id
	 *            登录账号
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public List<McBasPiuUser> findByLoginId(java.lang.String loginId// 登录账号
	) {
		if (loginId != null)
			loginId = loginId.toLowerCase().trim();
		return theDao.findByLoginId(loginId);
	}

}
