package com.hm.svc.user;

import java.util.List;

import com.aggrepoint.dao.HibernateService;
import com.aggrepoint.dao.PageList;
import com.hm.domain.user.McBasPiuUser;

/**
 * 消息中心内部用户表 server接口
 * 
 * @author zuoxp
 * 
 */
public interface IMcBasPiuUserService extends HibernateService<McBasPiuUser> {

	/**
	 * 根据 <br />
	 * LOGIN_ID Id 登录账号<br />
	 * PASSWORD Password 登录密码<br />
	 * 获取表T_BAS_MC_PIU_USER 消息中心内部用户表的对象<br />
	 * 开发者 王凯 2014年12月10日 13:30:41
	 * 
	 * @param Id
	 *            登录账号
	 * @param Password
	 *            登录密码
	 * @return BasMcPiuUser对象实体
	 */
	public abstract McBasPiuUser getByIdPassword(java.lang.String loginId, // 登录账号
			java.lang.String password // 登录密码
	);

	/**
	 * 根据 <br />
	 * USER_NAME Name 用户名称<br />
	 * 返回表T_BAS_MC_PIU_USER 消息中心内部用户表的数据列表<br />
	 * 开发者 王凯 2014年12月10日 15:18:38
	 * 
	 * @param Name
	 *            登录账号
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
	public abstract PageList<McBasPiuUser> findByLoginId(
			java.lang.String loginId, // 登录账号
			String order, // 排序字段
			String orderDir, // 排序顺序
			int pageNum, // 当前分页的页数
			int pageSize// 每页条数
	);

	/**
	 * 根据 <br />
	 * USER_ID Id 内部用户主键<br />
	 * 删除数据表T_BAS_MC_PIU_USER 消息中心内部用户表中的内容<br />
	 * 开发者 王凯 2014年12月10日 15:25:23
	 * 
	 * @param Id
	 *            内部用户主键
	 */
	public abstract int deleteById(java.lang.Long userId // 内部用户主键
	);

	/**
	 * 根据 <br />
	 * LOGIN_ID Id 登录账号<br />
	 * 返回表T_BAS_MC_PIU_USER 消息中心内部用户表的数据列表<br />
	 * 开发者 王凯 2014年12月11日 13:27:33
	 * 
	 * @param Id
	 *            登录账号
	 * @param order
	 *            排序字段
	 * @param orderDir
	 *            排序顺序
	 * @return 数据列表的强类型实体类
	 */
	public abstract List<McBasPiuUser> findByLoginId(java.lang.String loginId // 登录账号
	);

}
