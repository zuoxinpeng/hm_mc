package com.hm.web.tem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.winlet.UserProfile;
import com.aggrepoint.winlet.form.Validate;
import com.aggrepoint.winlet.form.Validates;
import com.aggrepoint.winlet.form.Validation;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Code;
import com.aggrepoint.winlet.spring.annotation.PageRefresh;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;
import com.hm.domain.tem.McTemplate;
import com.hm.domain.tem.McTemplateDetail;
import com.hm.svc.tem.IMcBasSystemService;
import com.hm.svc.tem.IMcSysTemplateService;
import com.hm.svc.tem.IMcTemplateDetailService;
import com.hm.svc.tem.IMcTemplateService;

/**
 * 消息中心_模板表 Controller 实现<br />
 * 2014年11月18日 15:55:00
 * 
 * @author 王凯
 * 
 */
@Winlet("template/mcTemplate")
public class McTemplateController {

	@Autowired
	private IMcTemplateService mcTemplateService;

	@Autowired
	private IMcBasSystemService mcBasSystemService;

	@Autowired
	private IMcTemplateDetailService mcTemplateDetailService;

	@Autowired
	private IMcSysTemplateService mcSysTemplateService;

	public static final String EDIT_KEY = ""; // 用于标识编辑模板明细窗口是否打开

	/**
	 * 消息模板查询窗口
	 * 
	 * @return
	 */
	@Window
	@Return({ @Code(value = "mcTemplateSearch", log = "显示消息模板查询窗口") })
	public String searchWin(Model model) {
		return "mcTemplateSearch";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * 
	 * @param Id
	 *            模板主键
	 * @param Encode
	 *            模板编码唯一
	 * @param pageId
	 *            分页参数
	 * @param sortBy
	 *            排序字段
	 * @param sort
	 *            排序顺序
	 * @param model
	 *            模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是list.jspx
	 * 
	 */
	@Window
	@Return({ @Code(value = "mcTemplateList", log = "显示消息模板列表！") })
	public String listWin(
	// @RequestParam(value = "searchSystId", required = false) java.lang.Long
	// systId,// 模板主键
			@RequestParam(value = "searchTemplateEncode", required = false) java.lang.String templateEncode,// 模板编码唯一
			@RequestParam(value = "page", required = false) Integer pageId,// 分页参数
			@RequestParam(value = "sortby", required = false) String sortBy,// 排序字段
			@RequestParam(value = "sort", required = false) String sort,// 排序顺序
			Model model) {// 模型对象
		int pageNum = pageId == null ? 1 : pageId;
		int pageSize = 10;

		model.addAttribute("mcTemplates", mcTemplateService.findByEncode(templateEncode, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		model.addAttribute("mcTemplateDetailList", mcTemplateDetailService.getAllMcTemplateDetail());// 查询模板详情
		return "mcTemplateList";
	}

	/**
	 * 转到新建模板窗口
	 * 
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", cache = true, log = "启动添加模板信息")
	public String addMcTemplate(Model model, HttpSession ps) {
		ps.setAttribute(EDIT_KEY, "add");
		return "";
	}

	/**
	 * 根据主键删除信息
	 * 
	 * @param Id
	 *            模板主键
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return({ @Code(value = "", update = "editWin", log = "成功删除消息中心_模板表信息", msg = "成功删除模板"), // 提示信息
			@Code(value = "notfound", view = "", log = "找不到要删除的消息中心_模板表信息") // 提示信息
	})
	public String delMcTemplate(@RequestParam(value = "templateId") java.lang.Long templateId,// 模板主键
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	) {
		// 判断模板id是否为空，如果不为空先将有关系的表中的数据根据模板id删除
		if (templateId != null) {
			mcTemplateDetailService.deleteByTemplateId(templateId);
			mcSysTemplateService.deleteByTemplateId(templateId);
		}

		return mcTemplateService.deleteById(templateId) > 0 ? "" : "notfound";
	}

	/**
	 * 根据主键转到编辑窗口
	 * 
	 * @param DetailId
	 *            模板明细主键
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", log = "启动编辑模板明细信息")
	public String editMcTemplateDetail(@RequestParam(value = "templateId") java.lang.Long templateId, @RequestParam(value = "templateTypeId") java.lang.Long templateTypeId, Model model, HttpSession ps) {

		McTemplateDetail mcTemplateDetail = mcTemplateDetailService.getByTemplateIdTypeId(templateId, templateTypeId);

		ps.setAttribute("templateId", templateId);
		ps.setAttribute("templateTypeId", templateTypeId);

		if (mcTemplateDetail != null) {
			ps.setAttribute("mcTemplateDetail", mcTemplateDetail);
		}
		ps.setAttribute(EDIT_KEY, "edit");
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
	 * @return 前台页面的jspx页面的名字，例如下面的是edit.jspx
	 */
	@Window
	@Return({ @Code(value = "", log = "添加/编辑窗口不显示"),// 提示信息
			@Code(value = "mcTemplateEdit", log = "添加/编辑窗口显示", title = "添加/编辑窗口显示") })
	public String editWin(@PageRefresh boolean refresh, HttpSession ps, Model model) {

		McTemplateDetail mcTemplateDetail = null;

		if (refresh) {
			ps.removeAttribute(EDIT_KEY);
		}
		if (ps.getAttribute(EDIT_KEY) == null) {
			return "";
		}
		if (ps.getAttribute(EDIT_KEY) != null && ps.getAttribute(EDIT_KEY).equals("add")) {
			model.addAttribute("viewKey", "add");
		}
		if (ps.getAttribute(EDIT_KEY) != null && ps.getAttribute(EDIT_KEY).equals("edit")) {

			model.addAttribute("viewKey", "edit");
			model.addAttribute("templateId", ps.getAttribute("templateId"));
			model.addAttribute("templateTypeId", ps.getAttribute("templateTypeId"));
			if (ps.getAttribute("mcTemplateDetail") != null) {
				mcTemplateDetail = (McTemplateDetail) ps.getAttribute("mcTemplateDetail");
				model.addAttribute("mcTemplateDetail", mcTemplateDetail);
			}
		}

		return "mcTemplateEdit";
	}

	/**
	 * 保存模板操作
	 * 
	 * @param mcTemplate
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
	@Return({ @Code(value = "", log = "保存模板操作成功", msg = "添加模板成功", update = "listWin"),// 提示信息
			@Code(value = "repeat", log = "模板已存在,请重新添加", msg = "模板已存在,请重新添加", view = ""), @Code(value = "vf", log = "AJAX表单字段校验", view = ""), @Code(value = "error", log = "表单字段校验出错", view = "") })
	// 提示信息
	// 验证
	@Validates({ @Validate(name = "templateEncode", id = "ne", error = "模板编码不能为空。"),// 模板编码
			@Validate(name = "templateEncode", id = "maxlen", args = { "20" }, error = "模板编码长度不能超过20。") // 模板编码
	})
	public String saveTemplate(@Valid McTemplate mcTemplate, // 保存校验的对象
			BindingResult bindingResult, Validation v,// 校验对象
			HttpSession ps) {// 页面对象

		if (v.hasError()) // 提交表单保存消息中心_模板表信息，但是表单中存在数据校验错误
			return "error";

		if (v.isValidateField()) // AJAX数据校验过程中执行save()方法，而不是提交表单保存用户信息
			return "vf";

		List<McTemplate> mcTemplateList = mcTemplateService.findByTemplateEncode(mcTemplate.getTemplateEncode());

		if (mcTemplateList.size() > 0) {// 判断模板是否存在，不存在则添加
			return "repeat";
		} else {

			mcTemplateService.createOrUpdate(mcTemplate); // 更新或添加对象
			ps.removeAttribute(EDIT_KEY);
			ps.removeAttribute("viewKey");
			ps.removeAttribute("templateId");
			ps.removeAttribute("templateTypeId");
			ps.removeAttribute("mcTemplateDetail");
			return "";
		}
	}

	/**
	 * 保存模板明细操作
	 * 
	 * @param mcTemplate
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
	@Return({ @Code(value = "", log = "保存模板明细操作成功", msg = "保存模板明细操作成功", update = "listWin"),// 提示信息
			@Code(value = "vf", log = "AJAX表单字段校验", view = ""), @Code(value = "error", log = "表单字段校验出错", view = ""),// 提示信息
			@Code(value = "titleError", log = "标题不能为空", msg = "标题不能为空", view = "") // 提示信息
	})
	// 验证
	@Validates({ @Validate(name = "templateTitle", id = "ne", error = "模板标题不能为空。"),// 模板标题
			@Validate(name = "templateContent", id = "ne", error = "模板内容不能为空。"),// 模板内容
	})
	public String saveTemplateDetail(@Valid McTemplateDetail mcTemplateDetail, // 保存校验的对象
			BindingResult bindingResult, Validation v, HttpSession ps) {

		if (v.isValidateField()) // AJAX数据校验过程中执行save()方法，而不是提交表单保存用户信息
			return "vf";

		if (v.hasError()) // 提交表单保存消息中心_模板表信息，但是表单中存在数据校验错误
			return "error";

		if (mcTemplateDetail.getTemplateTypeId() != 1) { // 如果消息类型不为1，也就是短信进行以下操作
			if (mcTemplateDetail.getTemplateTitle() == null || "".equals(mcTemplateDetail.getTemplateTitle())) {
				return "titleError";
			} else {
				mcTemplateDetailService.createOrUpdate(mcTemplateDetail); // 更新或添加对象
				ps.removeAttribute(EDIT_KEY);
				ps.removeAttribute("viewKey");
				ps.removeAttribute("templateId");
				ps.removeAttribute("templateTypeId");
				ps.removeAttribute("mcTemplateDetail");
				return "";
			}
		} else {
			mcTemplateDetailService.createOrUpdate(mcTemplateDetail); // 更新或添加对象
			ps.removeAttribute(EDIT_KEY);
			ps.removeAttribute("viewKey");
			ps.removeAttribute("templateId");
			ps.removeAttribute("templateTypeId");
			ps.removeAttribute("mcTemplateDetail");
			return "";
		}

	}

	/**
	 * 取消操作
	 * 
	 * @param ps
	 *            页面对象
	 * @return
	 */
	@Action
	@Return(log = "取消消息中心_模板表信息编辑")
	public String cancel(HttpSession ps) {
		ps.removeAttribute(EDIT_KEY); // 清除EDIT_KEY的值后editWin不显示
		ps.removeAttribute("viewKey");
		ps.removeAttribute("templateId");
		ps.removeAttribute("templateTypeId");
		ps.removeAttribute("mcTemplateDetail");
		return "";
	}
}
