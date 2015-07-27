package com.hm.web.tem;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Code;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;
import com.hm.domain.tem.McBasSystem;
import com.hm.domain.tem.McSysTemplate;
import com.hm.domain.tem.McTemplate;
import com.hm.svc.tem.IMcBasSystemService;
import com.hm.svc.tem.IMcSysTemplateService;
import com.hm.svc.tem.IMcTemplateService;
import com.hm.util.Converts;

/**
 * 消息中心_模板表 Controller 实现<br />
 * 2014年12月02日 13:32:29
 * 
 * @author 王凯
 *
 */
@Winlet("template/templateAllot")
public class TemplateAllotController {

	private static final Logger log = LoggerFactory.getLogger(TemplateAllotController.class);

	@Autowired
	private IMcTemplateService mcTemplateService;

	@Autowired
	private IMcBasSystemService mcBasSystemService;

	@Autowired
	private IMcSysTemplateService mcSysTemplateService;

	public static final String EDIT_KEY = "edit_key";

	// //////////////////////////////////////////////////////////////////////////
	//
	// 查询窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 查询窗口
	 * 
	 * @return 前台页面的jspx页面的名字，例如下面的是templateAllotSearch.jspx
	 */
	@Window
	@Return({ @Code(value = "templateAllotSearch", log = "显示消息中心_模板表信息查询窗口") })
	public String searchWin() {
		return "templateAllotSearch";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * 
	 * @param pageId
	 *            分页参数
	 * @param sortBy
	 *            排序字段
	 * @param sort
	 *            排序顺序
	 * @param model
	 *            模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是list.jspx
	 */
	@Window
	@Return({ @Code(value = "templateAllotList", log = "显示消息中心_模板表信息列表") })
	public String listWin(@RequestParam(value = "searchTemplateEncode", required = false) java.lang.String templateEncode,// 模板编码唯一
			@RequestParam(value = "page", required = false) Integer pageId,// 分页参数
			@RequestParam(value = "sortby", required = false) String sortBy,// 排序字段
			@RequestParam(value = "sort", required = false) String sort,// 排序顺序
			Model model, // 模型对象
			HttpServletRequest request // 请求
	) {
		int pageNum = pageId == null ? 1 : pageId;
		int pageSize = 10;

		List<McBasSystem> sysList = mcBasSystemService.findBy(null, null);
		model.addAttribute("mcTemplateList", mcTemplateService.findByEncode(templateEncode, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		// model.addAttribute("mcTemplateList",
		// mcTemplateService.findByEncode(null,sortBy == null ? "createTime" :
		// sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		model.addAttribute("mcBasSystemList", sysList); // 将系统查询出来并放入request域
		model.addAttribute("mcSysTemplateList", mcSysTemplateService.findBy(null, null));
		model.addAttribute("sysSize", sysList.size()); // 将系统的长度放入request，用户显示时候的列宽使用

		return "templateAllotList";
	}

	/**
	 * 分配模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Action
	@Return({ @Code(value = "", log = "分配模板操作成功", update = "listWin") })
	// 提示信息
	public String addTemplateAllot(HttpServletRequest request, HttpServletResponse response) {

		Long templateId = Converts.StrToLong(request.getParameter("templateId"));// 获取前台传递的参数
		Long systId = Converts.StrToLong(request.getParameter("systId"));

		McSysTemplate mcSysTemplate = mcSysTemplateService.getById(systId, templateId); // 根据系统id和模板id查询
		McBasSystem mcBasSystem = mcBasSystemService.find(systId);// 根据系统id查询出系统
		McTemplate mcTemplate = mcTemplateService.find(templateId);// 根据模板id查询出模板

		JSONObject obj = new JSONObject();

		if (mcSysTemplate != null) {// 取消分配模板
			mcSysTemplateService.deleteBySysTemplateId(mcSysTemplate.getSysTemplateId());
			// 封装json
			obj.put("state", -1);
			obj.put("sysName", mcBasSystem.getSystName());
			obj.put("templateEncode", mcTemplate.getTemplateEncode());

			try {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print(obj.toString());
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			return "";
		} else {// 给模板分配系统
			mcSysTemplate = new McSysTemplate();
			mcSysTemplate.setSystId(systId);
			mcSysTemplate.setTemplateId(templateId);
			mcSysTemplateService.create(mcSysTemplate);

			obj.put("state", 1);
			obj.put("sysName", mcBasSystem.getSystName());
			obj.put("templateEncode", mcTemplate.getTemplateEncode());

			try {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print(obj.toString());
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			return "";
		}
	}
}
