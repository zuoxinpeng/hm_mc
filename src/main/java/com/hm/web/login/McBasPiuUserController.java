package com.hm.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.winlet.PageStorage;
import com.aggrepoint.winlet.UserProfile;
import com.aggrepoint.winlet.form.Validate;
import com.aggrepoint.winlet.form.Validates;
import com.aggrepoint.winlet.form.Validation;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Code;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;
import com.hm.domain.user.McBasPiuUser;
import com.hm.svc.user.IMcBasPiuUserService;

/**
 * 消息中心内部用户表 Controller 实现<br />
 * 2014年12月10日 15:09:10
 * 
 * @author 王凯
 * 
 */
@Winlet("basMcPiuUser")
public class McBasPiuUserController {
	
	@Autowired
	private IMcBasPiuUserService basMcPiuUserService;

	public static final String EDIT_KEY = "edit_key";

	// //////////////////////////////////////////////////////////////////////////
	//
	// 查询窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 查询窗口
	 * 
	 * @return 前台页面的jspx页面的名字，例如下面的是searchBasMcPiuUser.jspx
	 */
	@Window
	@Return(log = "显示消息中心内部用户表信息查询窗口")
	public String searchWin(UserProfile up, HttpServletRequest request) {
		return "searchBasMcPiuUser";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * 
	 * @param loginId
	 *            登陆账号
	 * @param pageId
	 *            分页参数
	 * @param sortBy
	 *            排序字段
	 * @param sort
	 *            排序顺序
	 * @param model
	 *            模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是listBasMcPiuUser.jspx
	 */
	@Window
	@Return(log = "显示消息中心内部用户表信息列表")
	public String listWin(
			@RequestParam(value = "searchLoginId", required = false) java.lang.String loginId,// 登陆账号
			@RequestParam(value = "page", required = false) Integer pageId,// 分页参数
			@RequestParam(value = "sortby", required = false) String sortBy,// 排序字段
			@RequestParam(value = "sort", required = false) String sort,// 排序顺序
			UserProfile up, // 用户
			Model model, // 模型对象
			HttpServletRequest request // 请求
	) {
		int pageNum = pageId == null ? 1 : pageId;
		int pageSize = 10;
		model.addAttribute("basMcPiuUser", basMcPiuUserService.findByLoginId(
				loginId, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		return "listBasMcPiuUser";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 编辑窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 编辑窗口
	 * 
	 * @param ps
	 *            页面对象
	 * @param model
	 *            模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是editBasMcPiuUser.jspx
	 */
	@Window
	@Return({
			@Code(value = "", log = "消息中心内部用户表信息编辑窗口不用显示"),// 提示信息
			@Code(value = "new", log = "显示新建消息中心内部用户表", view = "createBasMcPiuUser", title = "新建消息中心内部用户表"),// 提示信息
			@Code(value = "editBasMcPiuUser", log = "显示编辑消息中心内部用户表", title = "编辑消息中心内部用户表"),// 提示信息
			@Code(value = "notfound", log = "找不到要编辑的消息中心内部用户表", view = "") // 提示信息
	})
	public String editWin(PageStorage ps, // 页面对象
			UserProfile up, // 用户
			Model model,// 模型对象
			HttpServletRequest request // 请求
	) {
		Long userId = ps.getAttribute(EDIT_KEY); // 从PageStorage中取出EDIT_KEY的值

		if (userId == null) // EDIT_KEY的值为NULL表示无需显示消息中心内部用户表信息编辑窗口
			return "";

		if (userId <= 0) { // EDIT_KEY的值<=0表示添加消息中心内部用户表信息
			model.addAttribute("basMcPiuUser", new McBasPiuUser());
			return "new";
		}

		// EDIT_KEY的值>0表示编辑消息中心内部用户表信息
		McBasPiuUser basMcPiuUser = basMcPiuUserService.find(userId);
		if (basMcPiuUser == null) { // 数据库中不存在对应的消息中心内部用户表信息，清除EDIT_KEY的值
			ps.removeAttribute(EDIT_KEY);
			return "notfound";
		}

		model.addAttribute("basMcPiuUser", basMcPiuUser);
		return "editBasMcPiuUser";
	}

	/**
	 * 转到新建窗口
	 * 
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", cache = true, log = "启动添加消息中心内部用户表信息")
	public String addBasMcPiuUser(UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, new Long(0)); // PageStorage中的EDIT_KEY的值为0L表示添加消息中心内部用户表
		return "";
	}

	/**
	 * 根据主键删除信息
	 * 
	 * @param Id
	 *            内部用户主键
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return({
			@Code(value = "", update = "editWin", log = "成功删除消息中心内部用户表信息", msg = "成功删除用户"), // 提示信息
			@Code(value = "notfound", view = "", log = "找不到要删除的消息中心内部用户表信息") // 提示信息
	})
	public String delBasMcPiuUser(
			@RequestParam(value = "userId") java.lang.Long userId,// 内部用户主键
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	) {
		return basMcPiuUserService.deleteById(userId) > 0 ? "" : "notfound";
	}

	/**
	 * 根据主键转到编辑窗口
	 * 
	 * @param Id
	 *            内部用户主键
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", log = "启动编辑消息中心内部用户表信息")
	public String editBasMcPiuUser(
			@RequestParam(value = "userId") java.lang.Long userId,// 内部用户主键
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, userId); // PageStorage中的EDIT_KEY的值为不为零的数值表示编辑消息中心内部用户表
		return "";
	}

	/**
	 * 保存操作
	 * 
	 * @param basMcPiuUser
	 *            保存校验的对象
	 * @param bindingResult
	 *            返回对象
	 * @param v
	 *            校验对象
	 * @param ps
	 *            页面对象
	 * @return
	 */
	@Action
	@Return({
			@Code(value = "", log = "成功保存消息中心内部用户表信息编辑", msg = "保存成功", update = "listWin"),// 提示信息
			@Code(value = "vf", log = "AJAX表单字段校验", view = ""),// 提示信息
			@Code(value = "repeat", log = "登陆账号已存在,请重新添加", msg = "登陆账号已存在,请重新添加", view = ""),
			@Code(value = "error", log = "表单字段校验出错", view = "") // 提示信息
	})
	// 验证
	@Validates({
			@Validate(name = "userId", id = "ne", error = "内部用户主键不能为空。"),// 内部用户主键
			@Validate(name = "userId", id = "float", error = "内部用户主键必须为数字。"),// 内部用户主键
			@Validate(name = "loginId", id = "ne", error = "登录账号不能为空。"),// 登录账号
			@Validate(name = "loginId", id = "maxlen", args = { "256" }, error = "登录账号长度不能超过256。"),// 登录账号
			@Validate(name = "name", id = "ne", error = "用户名称不能为空。"),// 用户名称
			@Validate(name = "name", id = "maxlen", args = { "256" }, error = "用户名称长度不能超过256。"),// 用户名称
			@Validate(name = "password", id = "ne", error = "登录密码不能为空。"),// 登录密码
			@Validate(name = "password", id = "maxlen", args = { "256" }, error = "登录密码长度不能超过256。"),// 登录密码
			@Validate(name = "accountStatus", id = "ne", error = "账号状态不能为空。"),// 账号状态（1启用，2禁用）
			@Validate(name = "accountStatus", id = "float", error = "账号状态必须为数字。"),// 账号状态（1启用，2禁用）
	})
	public String save(@Valid McBasPiuUser basMcPiuUser, // 保存校验的对象
			BindingResult bindingResult,// 返回对象
			Validation v,// 校验对象
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		if (v.isValidateField()) // AJAX数据校验过程中执行save()方法，而不是提交表单保存消息中心内部用户表信息
			return "vf";

		if (v.hasError()) // 提交表单保存消息中心内部用户表信息，但是表单中存在数据校验错误
			return "error";

		Long status = ps.getAttribute(EDIT_KEY);
		
		if(status <= 0 || status == null){
			if (basMcPiuUserService.findByLoginId(basMcPiuUser.getLoginId()).size() > 0) {// 如果登陆账号存在则不能添加
				return "repeat";
			}else{
				basMcPiuUser.setUserId((Long) ps.getAttribute(EDIT_KEY));
				basMcPiuUserService.createOrUpdate(basMcPiuUser);
				ps.removeAttribute(EDIT_KEY);
				return "";
			}
		}
		
		basMcPiuUser.setUserId((Long) ps.getAttribute(EDIT_KEY));
		basMcPiuUserService.createOrUpdate(basMcPiuUser);
		ps.removeAttribute(EDIT_KEY);
		return "";
	}

	/**
	 * 取消操作
	 * 
	 * @param ps
	 *            页面对象
	 * @return
	 */
	@Action
	@Return(log = "取消消息中心内部用户表信息编辑")
	public String cancel(PageStorage ps,// 页面对象
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	) {
		ps.removeAttribute(EDIT_KEY); // 清除EDIT_KEY的值后editWin不显示
		return "";
	}
}
