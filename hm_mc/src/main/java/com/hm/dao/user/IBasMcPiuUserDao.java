package com.hm.dao.user;

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
import com.hm.domain.user.McBasPiuUser;

/**
 * 消息中心内部用户表 DAO interface 接口
 * 
 * @author zuoxp
 * 
 */
@Component
public interface IBasMcPiuUserDao extends HibernateDao<McBasPiuUser> {

	/**
	 * 根据 <br />
	 * LOGIN_ID Id 登录账号<br />
	 * PASSWORD Password 登录密码<br />
	 * 获取表T_BAS_MC_PIU_USER 消息中心内部用户表的对象<br />
	 * 开发者 王凯 2014年12月10日 13:29:42
	 * 
	 * @param Id
	 *            登录账号
	 * @param Password
	 *            登录密码
	 * @return BasMcPiuUser对象实体
	 */
	@Find(" from McBasPiuUser where 1=1  and lower(loginId) = :loginId  and lower(password) = :password  ")
	public McBasPiuUser getByIdPassword(@Param("loginId") java.lang.String loginId, // 登录账号
			@Param("password") java.lang.String password // 登录密码
	);

	/**
	 * 根据 <br />
	 * USER_NAME Name 用户名称<br />
	 * 返回表T_BAS_MC_PIU_USER 消息中心内部用户表的数据列表<br />
	 * 开发者 王凯 2014年12月10日 15:22:26
	 * 
	 * @param Name
	 *            用户名称
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
	@Find(" from McBasPiuUser where 1=1  #{and lower(loginId) like :loginId}  #{order by :order :dir}")
	public PageList<McBasPiuUser> findByLoginId(@Like("loginId") java.lang.String loginId, // 登录账号
			@Replace("order") String order, // 排序字段
			@Replace("dir") String orderDir, // 排序顺序
			@PageNum int pageNum, // 当前分页的页数
			@PageSize int pageSize// 每页条数
	);

	/**
	 * 根据 <br />
	 * USER_ID Id 内部用户主键<br />
	 * 删除数据表T_BAS_MC_PIU_USER 消息中心内部用户表中的内容<br />
	 * 开发者 王凯 2014年12月10日 15:27:18
	 * 
	 * @param userId
	 *            内部用户主键
	 */
	@Delete("delete McBasPiuUser where userId = :userId ")
	public int deleteById(@Param("userId") java.lang.Long userId // 内部用户主键
	);

	/**
	 * 根据 <br />
	 * LOGIN_ID Id 登录账号<br />
	 * 返回表T_BAS_MC_PIU_USER 消息中心内部用户表的数据列表<br />
	 * 开发者 王凯 2014年12月11日 13:25:44
	 * 
	 * @param loginId
	 *            登录账号
	 * @return 数据列表的强类型实体类
	 */
	@Find(" from McBasPiuUser where 1=1  #{and lower(loginId) = :loginId}")
	public List<McBasPiuUser> findByLoginId(@Param("loginId") java.lang.String loginId// 登录账号
	);
}
