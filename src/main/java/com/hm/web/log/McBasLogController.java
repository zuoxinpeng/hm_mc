package com.hm.web.log;

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
import com.hm.domain.mc.McBasLog;
import com.hm.svc.log.IMcBasLogService;

@Winlet("basMcLog")
public class McBasLogController {

	@Autowired
	private IMcBasLogService basMcLogService;

	public static final String EDIT_KEY = "edit_key";


	// //////////////////////////////////////////////////////////////////////////
	//
	// 查询窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 查询窗口
	 * @return  前台页面的jspx页面的名字，例如下面的是searchBasMcLog.jspx
	 */
	@Window
	@Return(log = "显示日志表信息查询窗口")
	public String searchWin(UserProfile up,HttpServletRequest request)
	{
		return "searchBasMcLog";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * @param content 日志内容
	 * @param priority 日志重要程度（1.重要、2.一般、3.可忽略）
	 * @param pageId 分页参数
	 * @param sortBy 排序字段
	 * @param sort 排序顺序
	 * @param model 模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是listBasMcLog.jspx
	 */
	@Window
	@Return(log = "显示日志表信息列表")
	public String listWin(
			@RequestParam(value = "searchContent", required = false) java.lang.String content,// 日志内容
			@RequestParam(value = "searchPriority", required = false) java.lang.Long priority,// 日志重要程度（1.重要、2.一般、3.可忽略）
			@RequestParam(value = "page", required = false) Integer pageId,// 分页参数
			@RequestParam(value = "sortby", required = false) String sortBy,// 排序字段
			@RequestParam(value = "sort", required = false) String sort,// 排序顺序
			UserProfile up, // 用户
			Model model, // 模型对象
			HttpServletRequest request // 请求
	)
	{
	    int pageNum = pageId == null ? 1 : pageId;
	    int pageSize = 10;
		model.addAttribute("basMcLog", basMcLogService.findByContentPriority(content, priority,  sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		return "listBasMcLog";
	}
	

	// //////////////////////////////////////////////////////////////////////////
	//
	// 编辑窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 编辑窗口
	 * @param ps 页面对象
	 * @param model 模型对象
	 * @return  前台页面的jspx页面的名字，例如下面的是editBasMcLog.jspx
	 */
	@Window
	@Return({ @Code(value = "", log = "日志表信息编辑窗口不用显示"),// 提示信息
			@Code(value = "new", log = "显示新建日志表", view = "editBasMcLog", title = "新建日志表"),// 提示信息
			@Code(value = "editBasMcLog", log = "显示编辑日志表", title = "编辑日志表"),// 提示信息
			@Code(value = "notfound", log = "找不到要编辑的日志表", view = "") // 提示信息
	})
	public String editWin(PageStorage ps, // 页面对象
			UserProfile up, // 用户
			Model model,// 模型对象
			HttpServletRequest request // 请求
	)
	{
		Long logId = ps.getAttribute(EDIT_KEY); // 从PageStorage中取出EDIT_KEY的值

		if (logId == null) // EDIT_KEY的值为NULL表示无需显示日志表信息编辑窗口
			return "";

		if (logId <= 0)
		{ // EDIT_KEY的值<=0表示添加日志表信息
			model.addAttribute("basMcLog", new McBasLog());
			return "new";
		}

		// EDIT_KEY的值>0表示编辑日志表信息
		McBasLog basMcLog = basMcLogService.find(logId);
		if (basMcLog == null)
		{ // 数据库中不存在对应的日志表信息，清除EDIT_KEY的值
			ps.removeAttribute(EDIT_KEY);
			return "notfound";
		}

		model.addAttribute("basMcLog", basMcLog);
		return "editBasMcLog";
	}

	/**
	 * 根据主键转到编辑窗口
	 * @param Id 日志ID
	 * @param ps 页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", log = "启动编辑日志表信息")
	public String editBasMcLog(
			@RequestParam(value = "logId") java.lang.Long logId,// 日志ID
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	)
	{
		ps.setAttribute(EDIT_KEY, logId); // PageStorage中的EDIT_KEY的值为不为零的数值表示编辑日志表
		return "";
	}

	/**
	 * 取消操作
	 * @param ps 页面对象
	 * @return
	 */
	@Action
	@Return(log = "取消日志表信息编辑")
	public String cancel(PageStorage ps,// 页面对象
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	)
	{
		ps.removeAttribute(EDIT_KEY); // 清除EDIT_KEY的值后editWin不显示
		return "";
	}


}
