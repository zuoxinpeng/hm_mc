package com.hm.web.mc;

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
import com.hm.domain.comm.McConstantUtil;
import com.hm.domain.mc.McBasEmail;
import com.hm.svc.mc.IMcBasEmailService;
import com.hm.util.CommonParam;

@Winlet("mc/basMcEmail")
public class McBasEmailController {

	@Autowired
	private IMcBasEmailService basMcEmailService;

	public static final String EDIT_KEY = "edit_key";

	// //////////////////////////////////////////////////////////////////////////
	//
	// 查询窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 查询窗口
	 * 
	 * @return 前台页面的jspx页面的名字，例如下面的是searchMcEmail.jspx
	 */
	@Window
	@Return(log = "显示消息中心_邮件表信息查询窗口")
	public String searchWin(UserProfile up, HttpServletRequest request) {
		return "searchMcEmail";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * 
	 * @param receiver
	 *            邮件接收人
	 * @param pageId
	 *            分页参数
	 * @param sortBy
	 *            排序字段
	 * @param sort
	 *            排序顺序
	 * @param model
	 *            模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是listMcEmail.jspx
	 */
	@Window
	@Return(log = "显示消息中心_邮件表信息列表")
	public String listWin(@RequestParam(value = "searchReceiver", required = false) java.lang.String receiver,// 邮件接收人
			@RequestParam(value = "page", required = false) Integer pageId,// 分页参数
			@RequestParam(value = "sortby", required = false) String sortBy,// 排序字段
			@RequestParam(value = "sort", required = false) String sort,// 排序顺序
			UserProfile up, // 用户
			Model model, // 模型对象
			HttpServletRequest request // 请求
	) {
		int pageNum = pageId == null ? 1 : pageId;
		int pageSize = 10;
		model.addAttribute("basMcEmail", basMcEmailService.findByReceiver(receiver, sortBy == null ? "createTime" : sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort == null ? "desc" : sort, pageNum, pageSize));
		return "listMcEmail";
	}

	/**
	 * 根据主键转到编辑窗口
	 * 
	 * @param mailId
	 *            邮件ID
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", log = "启动编辑消息中心_邮件表信息")
	public String editBasMcEmail(@RequestParam(value = "maileId") java.lang.Long maileId,// 邮件ID
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, maileId); // PageStorage中的EDIT_KEY的值为不为零的数值表示编辑消息中心_邮件表
		return "";
	}

	/**
	 * 转到新建窗口
	 * 
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", cache = true, log = "启动添加消息中心_邮件表信息")
	public String addBasMcEmail(UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, new Long(0)); // PageStorage中的EDIT_KEY的值为0L表示添加消息中心_邮件表
		return "";
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
	 * @return 前台页面的jspx页面的名字，例如下面的是editMcEmail.jspx
	 */
	@Window
	@Return({ @Code(value = "", log = "消息中心_邮件表信息编辑窗口不用显示"),// 提示信息
			@Code(value = "new", log = "显示新建消息中心_邮件表", view = "editMcEmail", title = "新建消息中心_邮件表"),// 提示信息
			@Code(value = "editMcEmail", log = "显示编辑消息中心_邮件表", title = "编辑消息中心_邮件表"),// 提示信息
			@Code(value = "notfound", log = "找不到要编辑的消息中心_邮件表", view = "") // 提示信息
	})
	public String editWin(PageStorage ps, // 页面对象
			UserProfile up, // 用户
			Model model,// 模型对象
			HttpServletRequest request // 请求
	) {
		Long maileId = ps.getAttribute(EDIT_KEY); // 从PageStorage中取出EDIT_KEY的值

		if (maileId == null) // EDIT_KEY的值为NULL表示无需显示消息中心_邮件表信息编辑窗口
			return "";

		if (maileId <= 0) { // EDIT_KEY的值<=0表示添加消息中心_邮件表信息
			model.addAttribute("basMcEmail", new McBasEmail());
			return "new";
		}

		// EDIT_KEY的值>0表示编辑消息中心_邮件表信息
		McBasEmail basMcEmail = basMcEmailService.find(maileId);
		if (basMcEmail == null) { // 数据库中不存在对应的消息中心_邮件表信息，清除EDIT_KEY的值
			ps.removeAttribute(EDIT_KEY);
			return "notfound";
		}

		model.addAttribute("basMcEmail", basMcEmail);
		return "editMcEmail";
	}

	/**
	 * 保存操作
	 * 
	 * @param basMcEmail
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
	@Return({ @Code(value = "", log = "成功保存消息中心_邮件表信息编辑", update = "listWin"),// 提示信息
			@Code(value = "vf", log = "AJAX表单字段校验", view = ""),// 提示信息
			@Code(value = "error", log = "表单字段校验出错", view = "") // 提示信息
	})
	// 验证
	@Validates({ @Validate(name = "receiver", id = "ne", error = "邮件接收人不能为空。"),// 邮件接收人
			@Validate(name = "receiver", id = "maxlen", args = { "256" }, error = "邮件接收人长度不能超过256。"),// 邮件接收人
			@Validate(name = "receiver", id = "regexp", args = { "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$" }, error = "格式错误"),// 邮件接收人
			@Validate(name = "subject", id = "ne", error = "邮件主题不能为空。"),// 邮件主题
			@Validate(name = "subject", id = "maxlen", args = { "256" }, error = "邮件主题长度不能超过256。"),// 邮件主题
			@Validate(name = "content", id = "ne", error = "邮件内容不能为空。"),// 邮件内容
			@Validate(name = "content", id = "maxlen", args = { "4000" }, error = "邮件内容长度不能超过4000。"),// 邮件内容
	})
	public String save(@Valid McBasEmail basMcEmail, // 保存校验的对象
			BindingResult bindingResult,// 返回对象
			Validation v,// 校验对象
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		if (v.isValidateField()) // AJAX数据校验过程中执行save()方法，而不是提交表单保存消息中心_邮件表信息
			return "vf";

		if (v.hasError()) // 提交表单保存消息中心_邮件表信息，但是表单中存在数据校验错误
			return "error";

		CommonParam commparam = new CommonParam();

		basMcEmail.setMaileId((Long) ps.getAttribute(EDIT_KEY));
		basMcEmail.setStatus(McConstantUtil.SMS_STATUS_TO_SEND);
		basMcEmail.setKey(commparam.getString("SMS_KEY"));
		basMcEmailService.createOrUpdate(basMcEmail);
		ps.removeAttribute(EDIT_KEY);
		return "";
	}

	/**
	 * 12 取消操作
	 * 
	 * @param ps
	 *            页面对象
	 * @return
	 */
	@Action
	@Return(log = "取消消息中心_邮件表信息编辑")
	public String cancel(PageStorage ps,// 页面对象
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	) {
		ps.removeAttribute(EDIT_KEY); // 清除EDIT_KEY的值后editWin不显示
		return "";
	}

}
