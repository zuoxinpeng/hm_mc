package com.hm.web.imo;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.hm.domain.mc.McBasImo;
import com.hm.domain.mc.McBasImoFail;
import com.hm.svc.imo.IMcBasImoFailService;
import com.hm.svc.imo.IMcBasImoService;
import com.hm.util.CommonParam;



/**
 *消息中心_IMO表 Controller 实现<br />
 *2015年01月06日 10:10:48
 * @author 王凯
 *
 */
@Winlet("mc/basMcImo")
public class McBasImoController
{
	@Autowired
	private IMcBasImoService mcBasImoService;
	
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
	 * @return  前台页面的jspx页面的名字，例如下面的是searchMcImo.jspx
	 */
	@Window
	@Return(log = "显示消息中心_IMO表信息查询窗口")
	public String searchWin(UserProfile up,HttpServletRequest request)
	{
		return "searchMcImo";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 列表显示窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 列表显示窗口
	 * @param Receiver IMO接收人（邮箱的前缀拼音组成）
	 * @param pageId 分页参数
	 * @param sortBy 排序字段
	 * @param sort 排序顺序
	 * @param model 模型对象
	 * @return 前台页面的jspx页面的名字，例如下面的是listMcImo.jspx
	 */
	@Window
	@Return(log = "显示消息中心_IMO表信息列表")
	public String listWin(
			@RequestParam(value = "searchReceiver", required = false) java.lang.String receiver,// IMO接收人（邮箱的前缀拼音组成）
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
		model.addAttribute("basImo", mcBasImoService.findByReceiver(receiver,  sortBy == null ? "createTime" : sortBy, sort == null ? "desc" : sort, pageNum, pageSize));
		return "listMcImo";
	}

	/**
	 * 转到新建窗口
	 * @param ps 页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", cache = true, log = "启动添加消息中心_IMO表信息")
	public String addBasImo(
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
			)
	{
		ps.setAttribute(EDIT_KEY, new Long(0)); // PageStorage中的EDIT_KEY的值为0L表示添加消息中心_IMO表
		return "";
	}

	/**
	 * 根据主键转到编辑窗口
	 * @param Id IMOID
	 * @param ps 页面对象
	 * @return 通过 update 转到新建窗口 editWin
	 */
	@Action
	@Return(update = "editWin", log = "启动编辑消息中心_IMO表信息")
	public String editBasImo(
			@RequestParam(value = "imoId") java.lang.Long imoId,// IMOID
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	)
	{
		ps.setAttribute(EDIT_KEY, imoId); // PageStorage中的EDIT_KEY的值为不为零的数值表示编辑消息中心_IMO表
		return "";
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
	 * @return  前台页面的jspx页面的名字，例如下面的是edit.jspx
	 */
	@Window
	@Return({ @Code(value = "", log = "消息中心_IMO表信息编辑窗口不用显示"),// 提示信息
			@Code(value = "new", log = "显示新建消息中心_IMO表", view = "editMcImo", title = "新建消息中心_IMO表"),// 提示信息
			@Code(value = "editMcImo", log = "显示编辑消息中心_IMO表", title = "编辑消息中心_IMO表"),// 提示信息
			@Code(value = "notfound", log = "找不到要编辑的消息中心_IMO表", view = "") // 提示信息
	})
	public String editWin(PageStorage ps, // 页面对象
			UserProfile up, // 用户
			Model model,// 模型对象
			HttpServletRequest request // 请求
	)
	{
		Long id = ps.getAttribute(EDIT_KEY); // 从PageStorage中取出EDIT_KEY的值

		if (id == null) // EDIT_KEY的值为NULL表示无需显示消息中心_IMO表信息编辑窗口
			return "";

		if (id <= 0)
		{ // EDIT_KEY的值<=0表示添加消息中心_IMO表信息
			model.addAttribute("basImo", new McBasImo());
			return "new";
		}

		// EDIT_KEY的值>0表示编辑消息中心_IMO表信息
		McBasImo basImo = mcBasImoService.find(id);
		if (basImo == null)
		{ // 数据库中不存在对应的消息中心_IMO表信息，清除EDIT_KEY的值
			ps.removeAttribute(EDIT_KEY);
			return "notfound";
		}

		model.addAttribute("basImo", basImo);
		return "editMcImo";
	}

	/**
	 * 保存操作
	 * @param basImo 保存校验的对象
	 * @param bindingResult 返回对象
	 * @param v 校验对象
	 * @param ps 页面对象
	 * @return
	 */
	@Action
	@Return({ @Code(value = "", log = "成功保存消息中心_IMO表信息编辑", update = "listWin"),// 提示信息
			@Code(value = "vf", log = "AJAX表单字段校验", view = ""),// 提示信息
			@Code(value = "error", log = "表单字段校验出错", view = "") // 提示信息
	})
	// 验证
	@Validates({
			@Validate(name = "receiver", id = "ne", error = "IMO接收人不能为空。"),// IMO接收人（邮箱的前缀拼音组成）^[A-Za-z]+$
			@Validate(name = "receiver", id = "maxlen", args={"256"}, error = "IMO接收人长度不能超过256。"),// IMO接收人（邮箱的前缀拼音组成）
			@Validate(name = "receiver", id = "regexp", args = { "^[A-Za-z]+((,[A-Za-z]+)*)[A-Za-z]$" }, error = "IMO接收人格式错误"),//
			@Validate(name = "subject", id = "ne", error = "IMO主题不能为空。"),// IMO主题
			@Validate(name = "subject", id = "maxlen", args={"256"}, error = "IMO主题长度不能超过256。"),// IMO主题
			@Validate(name = "content", id = "ne", error = "IMO内容不能为空。"),// IMO内容
			@Validate(name = "content", id = "maxlen", args={"4000"}, error = "IMO内容长度不能超过4000。"),// IMO内容
	})
	public String save(@Valid McBasImo basImo, // 保存校验的对象
			BindingResult bindingResult,// 返回对象
			Validation v,// 校验对象
			UserProfile up, // 用户
			PageStorage ps,// 页面对象
			HttpServletRequest request // 请求
	)
	{
		if (v.isValidateField()) // AJAX数据校验过程中执行save()方法，而不是提交表单保存消息中心_IMO表信息
			return "vf";

		if (v.hasError()) // 提交表单保存消息中心_IMO表信息，但是表单中存在数据校验错误
			return "error";

		CommonParam commparam = new CommonParam();
		
		basImo.setImoId((Long) ps.getAttribute(EDIT_KEY));
		basImo.setStatus(McConstantUtil.SMS_STATUS_TO_SEND);
		basImo.setKey(commparam.getString("SYNC_KEY"));
		
		//保存之前设置IMO的接收人为员工id
		String receiver = basImo.getReceiver();
		String[] str = receiver.split(",");
		
		String receiverInfo  = mcBasImoService.getUserInfoByAccounts(receiver);
		JSONArray receiverJson = JSONArray.fromObject(receiverInfo);
		for (int i = 0; i < str.length; i++) {
			 JSONObject jsonObj  = (JSONObject) receiverJson.get(i);
			 String name = null;
			 if(jsonObj.has("uid")){
				 name = jsonObj.getString("uid");
				 basImo.setReceiver(str[i]);
				 basImo.setReceiverUid(name);
				 mcBasImoService.create(basImo);
			 }else{
				 McBasImoFail mcBasImoFail = new McBasImoFail(str[i],"0",basImo.getSubject(),basImo.getContent(),
						 basImo.getKey(),2L,basImo.getGrpId(),"IMO接收人不存在",basImo.getSendTime(),new Date(), null);
				 mcBasImoFailService.create(mcBasImoFail);
			 }
		}
		
		ps.removeAttribute(EDIT_KEY);
		return "";
	}

	/**
	 * 取消操作
	 * @param ps 页面对象
	 * @return
	 */
	@Action
	@Return(log = "取消消息中心_IMO表信息编辑")
	public String cancel(PageStorage ps,// 页面对象
			UserProfile up, // 用户
			HttpServletRequest request // 请求
	)
	{
		ps.removeAttribute(EDIT_KEY); // 清除EDIT_KEY的值后editWin不显示
		return "";
	}

}

