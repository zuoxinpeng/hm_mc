package com.hm.web.mc;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.winlet.UserProfile;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;
import com.hm.svc.imo.IMcBasImoFailService;
import com.hm.svc.imo.IMcBasImoSuccessService;
import com.hm.svc.mc.IMcBasEmailFailService;
import com.hm.svc.mc.IMcBasEmailSuccessService;
import com.hm.svc.mc.IMcBasMessageFailService;
import com.hm.svc.mc.IMcBasMessageSuccessService;
import com.hm.util.Converts;

@Winlet("mc/messageReport")
public class McBasReportController {

	@Autowired
	private IMcBasMessageSuccessService basMcMessageSuccessService;

	@Autowired
	private IMcBasMessageFailService basMcMessageFailService;

	@Autowired
	private IMcBasEmailSuccessService basMcEmailSuccessService;

	@Autowired
	private IMcBasEmailFailService basMcEmailFailService;

	@Autowired
	private IMcBasImoSuccessService mcBasImoSuccessService;

	@Autowired
	private IMcBasImoFailService mcBasImoFailService;

	// //////////////////////////////////////////////////////////////////////////
	//
	// 查询窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 查询窗口
	 * 
	 * @return 前台页面的jspx页面的名字，例如下面的是search.jspx
	 */
	@Window
	@Return(log = "显示消息中心_信息查询窗口")
	public String searchWin(UserProfile up, HttpServletRequest request) {
		return "searchMessageReport";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * 
	 * @param sendTimeStart
	 *            发送开始时间1
	 * @param sendTimeEnd
	 *            发送开始时间2
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
	@Return(log = "显示消息中心_信息列表")
	public String listWin(@RequestParam(value = "sendTimeStart", required = false) java.lang.String sendTimeStart,// 发送开始时间
			@RequestParam(value = "sendTimeEnd", required = false) java.lang.String sendTimeEnd,// 发送开始时间
			@RequestParam(value = "sortby", required = false) String sortBy,// 排序字段
			@RequestParam(value = "sort", required = false) String sort,// 排序顺序
			UserProfile up, // 用户
			Model model, // 模型对象
			HttpServletRequest request // 请求
	) {

		Date startDate = null;
		Date endDate = null;

		if (!StringUtils.isBlank(sendTimeStart)) {
			startDate = Converts.StrToDate(sendTimeStart);
		}
		if (!StringUtils.isBlank(sendTimeEnd)) {
			endDate = Converts.StrToDate(sendTimeEnd);
		}
		if (StringUtils.isBlank(sendTimeStart) && StringUtils.isBlank(sendTimeEnd)) {
			model.addAttribute("date", "所有历史发送");
		} else {
			model.addAttribute("date", sendTimeStart + " 至 " + sendTimeEnd);
		}

		// 查询出成功信息的条数和失败的条数并添加到reuquest域中
		model.addAttribute("basMcMessageSuccessSize", basMcMessageSuccessService.findByTime(startDate, endDate, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort).size());
		model.addAttribute("basMcMessageFailSize", basMcMessageFailService.findByTime(startDate, endDate, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort).size());
		model.addAttribute("basMcEmailSuccessSize", basMcEmailSuccessService.findByTime(startDate, endDate, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort).size());
		model.addAttribute("basMcEmailFailSize", basMcEmailFailService.findByTime(startDate, endDate, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort).size());

		model.addAttribute("basMcBasImoSuccessSize", mcBasImoSuccessService.findByTime(startDate, endDate, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort).size());// IMO发送成功条数
		model.addAttribute("basMcBasImoFailSize", mcBasImoFailService.findByTime(startDate, endDate, sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort).size());// IMO发送失败条数

		return "listMessageReport";
	}

}
