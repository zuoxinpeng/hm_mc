package com.hm.web.imo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.winlet.PageStorage;
import com.aggrepoint.winlet.UserProfile;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Code;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;
import com.hm.domain.mc.McBasImoFail;
import com.hm.svc.imo.IMcBasImoFailService;

/**
 * 消息中心_IMO表（失败） Controller 实现<br />
 * 2015年01月05日 16:19:55
 * 
 * @author 王凯
 *
 */
@Winlet("mc/basMcImoFail")
public class McBasImoFailController {

	@Autowired
	private IMcBasImoFailService mcBasImoFailService;

	public static final String EDIT_KEY = "edit_key";

	// //////////////////////////////////////////////////////////////////////////
	//
	// 查询窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 查询窗口
	 * 
	 * @return 前台页面的jspx页面的名字，例如下面的是searchMcBasImoFail.jspx
	 */
	@Window
	@Return(log = "显示消息中心_IMO表（失败）信息查询窗口")
	public String searchWin(UserProfile up, HttpServletRequest request) {
		return "searchMcImoFail";
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
	 *            IMO接收人
	 * @param pageId
	 *            分页参数
	 * @param sortBy
	 *            排序字段
	 * @param sort
	 *            排序顺序
	 * @param model
	 *            模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是listMcBasImoFail.jspx
	 */
	@Window
	@Return(log = "显示消息中心_IMO表（失败）信息列表")
	public String listWin(@RequestParam(value = "searchReceiver", required = false) java.lang.String Receiver,// IMO接收人
			@RequestParam(value = "page", required = false) Integer pageId,// 分页参数
			@RequestParam(value = "sortby", required = false) String sortBy,// 排序字段
			@RequestParam(value = "sort", required = false) String sort,// 排序顺序
			UserProfile up, // 用户
			Model model, // 模型对象
			HttpServletRequest request // 请求
	) {
		int pageNum = pageId == null ? 1 : pageId;
		int pageSize = 10;
		model.addAttribute("basImoFail", mcBasImoFailService.findByReceiver(Receiver, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		return "listMcImoFail";
	}

	/**
	 * 根据主键转到编辑窗口
	 * 
	 * @param Id
	 *            IMOID
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", log = "启动编辑消息中心_IMO表（失败）信息")
	public String editBasImoFail(@RequestParam(value = "imoId") java.lang.Long imoId,// IMOID
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, imoId); // PageStorage中的EDIT_KEY的值为不为零的数值表示编辑消息中心_IMO表（失败）
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
	 * @return 前台页面的jspx页面的名字，例如下面的是editMcBasImoFail.jspx
	 */
	@Window
	@Return({ @Code(value = "", log = "消息中心_IMO表（失败）信息编辑窗口不用显示"),// 提示信息
			@Code(value = "new", log = "显示新建消息中心_IMO表（失败）", view = "editMcImoFail", title = "新建消息中心_IMO表（失败）"),// 提示信息
			@Code(value = "editMcImoFail", log = "显示编辑消息中心_IMO表（失败）", title = "编辑消息中心_IMO表（失败）"),// 提示信息
			@Code(value = "notfound", log = "找不到要编辑的消息中心_IMO表（失败）", view = "") // 提示信息
	})
	public String editWin(PageStorage ps, // 页面对象
			UserProfile up, // 用户
			Model model,// 模型对象
			HttpServletRequest request // 请求
	) {
		Long id = ps.getAttribute(EDIT_KEY); // 从PageStorage中取出EDIT_KEY的值

		if (id == null) // EDIT_KEY的值为NULL表示无需显示消息中心_IMO表（失败）信息编辑窗口
			return "";

		if (id <= 0) { // EDIT_KEY的值<=0表示添加消息中心_IMO表（失败）信息
			model.addAttribute("basImoFail", new McBasImoFail());
			return "new";
		}

		// EDIT_KEY的值>0表示编辑消息中心_IMO表（失败）信息
		McBasImoFail basImoFail = mcBasImoFailService.find(id);
		if (basImoFail == null) { // 数据库中不存在对应的消息中心_IMO表（失败）信息，清除EDIT_KEY的值
			ps.removeAttribute(EDIT_KEY);
			return "notfound";
		}

		model.addAttribute("basImoFail", basImoFail);
		return "editMcImoFail";
	}

	/**
	 * 取消操作
	 * 
	 * @param ps
	 *            页面对象
	 * @return
	 */
	@Action
	@Return(log = "取消消息中心_IMO表（失败）信息编辑")
	public String cancel(PageStorage ps,// 页面对象
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	) {
		ps.removeAttribute(EDIT_KEY); // 清除EDIT_KEY的值后editWin不显示
		return "";
	}

}
