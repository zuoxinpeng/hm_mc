package com.hm.web.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aggrepoint.winlet.PageStorage;
import com.aggrepoint.winlet.UserEngine;
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
import com.hm.domain.user.PiuUserProfile;
import com.hm.svc.user.IMcBasPiuUserService;
import com.hm.util.RandomUtil;

@Winlet("anonymous/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IMcBasPiuUserService basMcPiuUserService;

	@Window
	@Return(@Code(value = "login", log = "显示消息中心登陆用户表窗口"))
	public String loginWin() {
		return "login";
	}

	@Window
	@Return(@Code(value = "header", log = "加载页面头部信息"))
	public String headerWin(UserProfile up, HttpServletRequest request) {
		request.setAttribute("done", RandomUtil.generate(18));
		request.setAttribute("doneHZ", RandomUtil.generate(18));
		return "header";
	}

	/**
	 * 登陆验证
	 * 
	 * @param basMcPiuUser
	 * @param v
	 * @return
	 */
	@Action
	@Return({ @Code(value = "", log = "表单字段验证出错", update = ""), @Code(value = "success", update = "page", log = "用户登录成功"), @Code(value = "vf", log = "AJAX表单字段校验", view = ""),
			@Code(value = "error", log = "表单字段校验出错", view = ""), @Code(value = "statusError", log = "用户被禁用", msg = "用户被禁用不能登录", view = "") // 用户被禁用则提示不能登录
	})
	@Validates({ @Validate(name = "loginId", id = "ne", error = "用户名不能为空"), @Validate(name = "password", id = "ne", error = "密码不能为空") })
	public String loginCheck(McBasPiuUser puser, PageStorage ps, Validation v, HttpServletRequest req, UserEngine ue) {

		if (v.isValidateField()) {// AJAX数据校验过程中执行save()方法，而不是提交表单保存信息
			return "vf";
		}

		if (v.hasError()) {
			return "error";
		}

		McBasPiuUser info = basMcPiuUserService.getByIdPassword(puser.getLoginId(), puser.getPassword());// 根据用户名密码查询是否存在
		if (info == null) {
			v.addError("loginId", "用户名或密码输入错误。");
		}
		if (v.hasError()) { // 提交表单保存信息，但是表单中存在数据校验错误
			return "error";
		}

		if (info.getAccountStatus() == 2) {// 用户被禁用则不能登录
			return "statusError";
		}

		PiuUserProfile piu = new PiuUserProfile();
		piu.setLoginId(info.getLoginId());
		piu.setName(info.getName());
		piu.setPassword(info.getPassword());
		piu.setEndTime(new Date());
		ue.setUser(req, piu);

		// 写入最后登陆IP与登陆时间

		info.setLastLoginIp(getRequestIP(req));
		info.setLastLoginTime(new Date());
		basMcPiuUserService.update(info);

		logger.info("[===============登陆用户：" + info.getLoginId() + " ===============]");
		logger.info("[===============登陆ip：" + getRequestIP(req) + " ===============]");
		logger.info("[=============== 登陆时间：" + new Date() + " ===============]");
		return "success"; // 如果成功进入index页面
	}

	/**
	 * 登陆退出注销
	 * 
	 * @return
	 */
	@Action
	@Return({ @Code(value = "", log = "退出登陆操作成功") })
	public String logout(HttpServletRequest req, UserEngine ue) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:/messagecenter/site/login";
	}

	/**
	 * 获取登录IP
	 * 
	 * @param request
	 * @return
	 */
	public String getRequestIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		return ip;
	}

}
