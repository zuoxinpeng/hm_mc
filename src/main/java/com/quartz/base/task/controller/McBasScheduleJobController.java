package com.quartz.base.task.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.winlet.PageStorage;
import com.aggrepoint.winlet.UserProfile;
import com.aggrepoint.winlet.form.Validate;
import com.aggrepoint.winlet.form.Validates;
import com.aggrepoint.winlet.form.Validation;
import com.aggrepoint.winlet.spring.annotation.AccessRule;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Code;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;
import com.quartz.base.task.domain.ScheduleJob;
import com.quartz.base.task.service.IMcBasScheduleJobService;
import com.quartz.base.task.service.JobTaskService;

 /**
 *
 * McBasScheduleJobController
 * 
 * ZUOXP
 * 2015-1-19 上午11:32:30
 * 
 * @version 1.0.0
 *
 */
@Winlet("job/mcBasScheduleJob")
@AccessRule("!anonymous")
public class McBasScheduleJobController {
	@Autowired
	private IMcBasScheduleJobService mcBasScheduleJobServiceservice;
	@Autowired
	private JobTaskService jobTaskService;

	public static final String EDIT_KEY = "edit_key";

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * @param Name 任务名称
	 * @param Group 任务分组
	 * @param Status 任务状态 0禁用 1启用 2删除
	 * @param pageId 分页参数
	 * @param sortBy 排序字段
	 * @param sort 排序顺序
	 * @param model 模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是list.jspx
	 */
	@Window
	@Return(log = "显示调度任务信息列表")
	public String listWin(
			@RequestParam(value = "searchName", required = false) java.lang.String jobName,// 任务名称
			@RequestParam(value = "searchGroup", required = false) java.lang.String jobGroup,// 任务分组
			@RequestParam(value = "searchStatus", required = false) java.lang.String jobStatus,// 任务状态 0禁用 1启用 2删除
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
	    
	  //获取所有计划中的任务列表
		try {
			List<ScheduleJob> allJobList = jobTaskService.getAllJob();
			model.addAttribute("allJobList", allJobList);
			
			//所有正在运行的job
			List<ScheduleJob> runningJobList = jobTaskService.getRunningJob();
			model.addAttribute("runningJobList", runningJobList);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	    
		model.addAttribute("mcBasScheduleJob", mcBasScheduleJobServiceservice.findByNameGroupStatus(jobName, jobGroup, jobStatus,  sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		return "listMcBasScheduleJob";
	}


	
	
	
	

	// //////////////////////////////////////////////////////////////////////////
	//
	// 查询窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 查询窗口
	 * 
	 * @return 前台页面的jspx页面的名字，例如下面的是searchMcBasScheduleJob.jspx
	 */
	@Window
	@Return(log = "显示调度任务信息查询窗口")
	public String searchWin(UserProfile up, HttpServletRequest request) {
		return "searchMcBasScheduleJob";
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
	 * @return 前台页面的jspx页面的名字，例如下面的是editScheduleJobCron.jspx
	 */
	@Window
	@Return({
			@Code(value = "", log = "调度任务信息编辑窗口不用显示"),// 提示信息
			@Code(value = "new", log = "显示新建调度任务", view = "editMcBasScheduleJob", title = "新建调度任务"),// 提示信息
			@Code(value = "editScheduleJobCron", log = "显示编辑调度任务", title = "编辑调度任务"),// 提示信息
			@Code(value = "notfound", log = "找不到要编辑的调度任务", view = "") // 提示信息
	})
	public String editWin(PageStorage ps, // 页面对象
			UserProfile up, // 用户
			Model model,// 模型对象
			HttpServletRequest request // 请求
	) {
		Long jobId = ps.getAttribute(EDIT_KEY); // 从PageStorage中取出EDIT_KEY的值

		if (jobId == null) // EDIT_KEY的值为NULL表示无需显示调度任务信息编辑窗口
			return "";

		if (jobId <= 0) { // EDIT_KEY的值<=0表示添加调度任务信息
			model.addAttribute("mcBasScheduleJob", new ScheduleJob());
			return "new";
		}

		// EDIT_KEY的值>0表示编辑调度任务信息
		ScheduleJob mcBasScheduleJob = mcBasScheduleJobServiceservice.find(jobId);
		if (mcBasScheduleJob == null) { // 数据库中不存在对应的调度任务信息，清除EDIT_KEY的值
			ps.removeAttribute(EDIT_KEY);
			return "notfound";
		}

		model.addAttribute("mcBasScheduleJob", mcBasScheduleJob);
		return "editScheduleJobCron";
	}

	/**
	 * 转到新建窗口(添加任务)
	 * 
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", cache = true, log = "启动添加调度任务信息")
	public String addMcBasScheduleJob(UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, new Long(0)); // PageStorage中的EDIT_KEY的值为0L表示添加调度任务
		return "";
	}

	/**
	 * 根据主键转到编辑窗口(更新任务)
	 * 
	 * @param JobId
	 *            任务ID
	 * @param ps
	 *            页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", log = "启动编辑调度任务信息")
	public String editMcBasScheduleJob(
			@RequestParam(value = "jobId") java.lang.Long JobId,// 任务ID
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		ps.setAttribute(EDIT_KEY, JobId); // PageStorage中的EDIT_KEY的值为不为零的数值表示编辑调度任务
		return "";
	}

	/**
	 * 保存操作
	 * 
	 * @param mcBasScheduleJob
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
	@Return({ @Code(value = "", log = "成功保存调度任务信息编辑", update = "listWin"),// 提示信息
			@Code(value = "vf", log = "AJAX表单字段校验", view = ""),// 提示信息
			@Code(value = "error", log = "表单字段校验出错", view = "") // 提示信息
	})
	// 验证
	@Validates({
			@Validate(name = "jobId", id = "ne", error = "任务ID不能为空。"),// 任务ID
			@Validate(name = "jobId", id = "float", error = "任务ID必须为数字。"),// 任务ID
			@Validate(name = "jobName", id = "ne", error = "任务名称不能为空。"),// 任务名称
			@Validate(name = "jobName", id = "maxlen", args = { "256" }, error = "任务名称长度不能超过256。"),// 任务名称
			@Validate(name = "jobGroup", id = "ne", error = "任务分组不能为空。"),// 任务分组
			@Validate(name = "jobGroup", id = "maxlen", args = { "256" }, error = "任务分组长度不能超过256。"),// 任务分组
			@Validate(name = "cronExpression", id = "ne", error = "cron表达式（任务运行时间表达式）不能为空。"),// cron表达式（任务运行时间表达式）
			@Validate(name = "cronExpression", id = "maxlen", args = { "256" }, error = "cron表达式（任务运行时间表达式）长度不能超过256。"),// cron表达式（任务运行时间表达式）
			@Validate(name = "beanClass", id = "ne", error = "任务执行时调用哪个类的方法 包名+类名不能为空。"),// 任务执行时调用哪个类的方法
			@Validate(name = "beanClass", id = "maxlen", args = { "256" }, error = "任务执行时调用哪个类的方法 包名+类名长度不能超过256。"),// 任务执行时调用哪个类的方法
			@Validate(name = "methodName", id = "ne", error = "任务调用的方法名不能为空。"),// 任务调用的方法名
			@Validate(name = "methodName", id = "maxlen", args = { "256" }, error = "任务调用的方法名长度不能超过256。"),// 任务调用的方法名
			@Validate(name = "springId", id = "ne", error = "spring bean不能为空。"),// spring
			@Validate(name = "springId", id = "maxlen", args = { "256" }, error = "spring bean长度不能超过256。"),// spring
	})
	public String save(@Valid ScheduleJob mcBasScheduleJob, // 保存校验的对象
			BindingResult bindingResult,// 返回对象
			Validation v,// 校验对象
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	) {
		if (v.isValidateField()) // AJAX数据校验过程中执行save()方法，而不是提交表单保存调度任务信息
			return "vf";

		if (v.hasError()) // 提交表单保存调度任务信息，但是表单中存在数据校验错误
			return "error";

		mcBasScheduleJob.setJobId((Long) ps.getAttribute(EDIT_KEY));
		//service.createOrUpdate(mcBasScheduleJob);
		//mcBasScheduleJobServiceservice.create(mcBasScheduleJob);
		try {
			jobTaskService.addJob(mcBasScheduleJob);
			jobTaskService.addTask(mcBasScheduleJob);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		ps.removeAttribute(EDIT_KEY);
		return "";
	}
	//--------------------------------------------------------
	@Action
	@Return({ @Code(value = "", log = "成功保存调度任务信息编辑",update = "listWin",msg = "修改时间表达式成功"),// 提示信息
		@Code(value = "vf", log = "AJAX表单字段校验", view = ""),// 提示信息
		@Code(value = "error", log = "表单字段校验出错", view = "") // 提示信息
	})
	public String updateCronExpression(@RequestParam(value = "jobId") java.lang.Long jobId,String cronExpression,
			PageStorage ps,// 页面对象
			HttpServletRequest request){
		try {
			jobTaskService.updateCron(jobId,cronExpression);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		ps.removeAttribute(EDIT_KEY);
		return "";
	}
	//--------------------------------------------------------
	
	@Window
	@Return(@Code(value = "showCron", log = "显示时间表达式"))
	public String showCron() {
		return "showCron";
	}
	
	
	@Action
	@Return({ @Code(value = "", log = "改变状态",update = "listWin")})
	public String changeJobStatus(HttpServletRequest request,HttpServletResponse response){
		try {
			if(!StringUtils.isEmpty(request.getParameter("jobId"))){
				Long jobId = Long.valueOf(request.getParameter("jobId")) ;
				jobTaskService.changeStatus(jobId, request.getParameter("cmd"));
				String result = "改变任务状态成功";
				response.setContentType("application/text;charset=UTF-8");
				response.getWriter().print(result);
				response.getWriter().flush();
		        response.getWriter().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			String result = "改变任务状态失败";
			try {
				response.setContentType("application/text;charset=UTF-8");
				response.getWriter().print(result);
				response.getWriter().flush();
		        response.getWriter().close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		return "";
	}

	/** 
	 * 根据主键删除信息
	 * @param Id 任务ID
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return({ @Code(value = "", update = "editWin", log = "成功删除调度任务信息"), // 提示信息
			@Code(value = "notfound", view = "", log = "找不到要删除的调度任务信息") // 提示信息
	})
	public String delMcBasScheduleJob(
			@RequestParam(value = "jobId") java.lang.Long jobId,// 任务ID
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	)
	{
		return mcBasScheduleJobServiceservice.deleteById(jobId) > 0 ? "" : "notfound";
	}
	
	


	/**
	 * 取消操作
	 * 
	 * @param ps
	 *            页面对象
	 * @return
	 */
	@Action
	@Return(log = "取消调度任务信息编辑")
	public String cancel(PageStorage ps,// 页面对象
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	) {
		ps.removeAttribute(EDIT_KEY); // 清除EDIT_KEY的值后editWin不显示
		return "";
	}

}
