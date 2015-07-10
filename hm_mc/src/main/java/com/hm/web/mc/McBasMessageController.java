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
import com.hm.domain.mc.McBasMessage;
import com.hm.svc.mc.IMcBasMessageService;
import com.hm.util.CommonParam;

/**
 * 消息中心_短信表 Controller 实现<br />
 * 2014年12月08日 15:49:27
 * 
 * @author zuoxp
 * 
 */
@Winlet("mc/basMcMessage")
public class McBasMessageController {

	@Autowired
	private IMcBasMessageService service;

	public static final String EDIT_KEY = "edit_key";

	// //////////////////////////////////////////////////////////////////////////
	//
	// 查询窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 查询窗口
	 * 
	 * @return 前台页面的jspx页面的名字，例如下面的是searchMcMessage.jspx
	 */
	@Window
	@Return(log = "显示消息中心_短信表信息查询窗口")
	public String searchWin(UserProfile up, HttpServletRequest request) {
		return "searchMcMessage";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * 
	 * @param Receiver
	 *            短信接收号码多个电话可按照”,”分隔
	 * @param pageId
	 *            分页参数
	 * @param sortBy
	 *            排序字段
	 * @param sort
	 *            排序顺序
	 * @param model
	 *            模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是listMcMessage.jspx
	 */
	@Window
	@Return(log = "显示消息中心_短信表信息列表")
	public String listWin(@RequestParam(value = "searchReceiver", required = false) java.lang.String Receiver,// 短信接收号码多个电话可按照”,”分隔
			@RequestParam(value = "page", required = false) Integer pageId,// 分页参数
			@RequestParam(value = "sortby", required = false) String sortBy,// 排序字段
			@RequestParam(value = "sort", required = false) String sort,// 排序顺序
			UserProfile up, // 用户
			Model model, // 模型对象
			HttpServletRequest request // 请求
	) {
		int pageNum = pageId == null ? 1 : pageId;
		int pageSize = 10;

		model.addAttribute("basMcMessage", service.findByReceiver(Receiver, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		return "listMcMessage";
	}

	/**
	 * 转到新建窗口
	 * 
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", cache = true, log = "启动添加消息中心_短信表信息")
	public String addBasMcMessage(UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, new Long(0)); // PageStorage中的EDIT_KEY的值为0L表示添加消息中心_短信表
		return "";
	}

	/**
	 * 根据主键转到编辑窗口
	 * 
	 * @param MessageId
	 *            短信ID
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", log = "启动编辑消息中心_短信表信息")
	public String editBasMcMessage(@RequestParam(value = "messageId") java.lang.Long MessageId,// 短信ID
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, MessageId); // PageStorage中的EDIT_KEY的值为不为零的数值表示编辑消息中心_短信表
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
	 * @return 前台页面的jspx页面的名字，例如下面的是editMcMessage.jspx
	 */
	@Window
	@Return({ @Code(value = "", log = "消息中心_短信表信息编辑窗口不用显示"),// 提示信息
			@Code(value = "new", log = "显示新建消息中心_短信表", view = "editMcMessage", title = "新建消息中心_短信表"),// 提示信息
			@Code(value = "editMcMessage", log = "显示编辑消息中心_短信表", title = "编辑消息中心_短信表"),// 提示信息
			@Code(value = "notfound", log = "找不到要编辑的消息中心_短信表", view = "") // 提示信息
	})
	public String editWin(PageStorage ps, // 页面对象
			UserProfile up, // 用户
			Model model,// 模型对象
			HttpServletRequest request // 请求
	) {
		Long messageId = ps.getAttribute(EDIT_KEY); // 从PageStorage中取出EDIT_KEY的值

		if (messageId == null) // EDIT_KEY的值为NULL表示无需显示消息中心_短信表信息编辑窗口
			return "";

		if (messageId <= 0) { // EDIT_KEY的值<=0表示添加消息中心_短信表信息
			model.addAttribute("basMcMessage", new McBasMessage());
			return "new";
		}

		// EDIT_KEY的值>0表示编辑消息中心_短信表信息
		McBasMessage basMcMessage = service.find(messageId);
		if (basMcMessage == null) { // 数据库中不存在对应的消息中心_短信表信息，清除EDIT_KEY的值
			ps.removeAttribute(EDIT_KEY);
			return "notfound";
		}

		model.addAttribute("basMcMessage", basMcMessage);
		return "editMcMessage";
	}

	/**
	 * 保存操作
	 * 
	 * @param basMcMessage
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
	@Return({ @Code(value = "", log = "成功保存消息中心_短信表信息编辑", update = "listWin"),// 提示信息
			@Code(value = "vf", log = "AJAX表单字段校验", view = ""),// 提示信息
			@Code(value = "error", log = "表单字段校验出错", view = "") // 提示信息
	})
	// 验证
	@Validates({ @Validate(name = "receiver", id = "ne", error = "短信接收号码多个电话可按照”,”分隔不能为空。"),// 短信接收号码多个电话可按照”,”分隔
			@Validate(name = "receiver", id = "maxlen", args = { "256" }, error = "短信接收号码多个电话可按照”,”分隔长度不能超过256。"),// 短信接收号码多个电话可按照”,”分隔
			@Validate(name = "receiver", id = "regexp", args = { "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$" }, error = "格式不正确"), @Validate(name = "content", id = "ne", error = "短信内容不能为空。"),// 短信内容
			@Validate(name = "content", id = "maxlen", args = { "2000" }, error = "短信内容长度不能超过2000。") // 短信内容
	})
	public String save(@Valid McBasMessage basMcMessage, // 保存校验的对象
			BindingResult bindingResult,// 返回对象
			Validation v,// 校验对象
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		if (v.isValidateField()) // AJAX数据校验过程中执行save()方法，而不是提交表单保存消息中心_短信表信息
			return "vf";

		if (v.hasError()) // 提交表单保存消息中心_短信表信息，但是表单中存在数据校验错误
			return "error";

		CommonParam commparam = new CommonParam();

		basMcMessage.setMessageId((Long) ps.getAttribute(EDIT_KEY));
		basMcMessage.setStatus(McConstantUtil.SMS_STATUS_TO_SEND);
		basMcMessage.setKey(commparam.getString("SMS_KEY"));
		service.createOrUpdate(basMcMessage);
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
	@Return(log = "取消消息中心_短信表信息编辑")
	public String cancel(PageStorage ps,// 页面对象
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	) {
		ps.removeAttribute(EDIT_KEY); // 清除EDIT_KEY的值后editWin不显示
		return "";
	}

}
