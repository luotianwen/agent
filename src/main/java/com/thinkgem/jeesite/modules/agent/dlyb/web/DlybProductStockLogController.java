/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductStockLog;
import com.thinkgem.jeesite.modules.agent.dlyb.service.DlybProductStockLogService;

import java.util.Date;

/**
 * 动力越博库存日志Controller
 * @author 罗天文
 * @version 2018-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/dlyb/dlybProductStockLog")
public class DlybProductStockLogController extends BaseController {

	@Autowired
	private DlybProductStockLogService dlybProductStockLogService;
	
	@ModelAttribute
	public DlybProductStockLog get(@RequestParam(required=false) String id) {
		DlybProductStockLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dlybProductStockLogService.get(id);
		}
		if (entity == null){
			entity = new DlybProductStockLog();
		}
		return entity;
	}
	
	@RequiresPermissions("dlyb:dlybProductStockLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(DlybProductStockLog dlybProductStockLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(null==dlybProductStockLog.getBeginCreateDate()){
			dlybProductStockLog.setBeginCreateDate(DateUtils.getBeforeDate(new Date(),1));
		}
		Page<DlybProductStockLog> page = dlybProductStockLogService.findPage(new Page<DlybProductStockLog>(request, response), dlybProductStockLog);
		model.addAttribute("page", page);
		return "agent/dlyb/dlybProductStockLogList";
	}

	@RequiresPermissions("dlyb:dlybProductStockLog:view")
	@RequestMapping(value = "form")
	public String form(DlybProductStockLog dlybProductStockLog, Model model) {
		model.addAttribute("dlybProductStockLog", dlybProductStockLog);
		return "agent/dlyb/dlybProductStockLogForm";
	}

	@RequiresPermissions("dlyb:dlybProductStockLog:edit")
	@RequestMapping(value = "save")
	public String save(DlybProductStockLog dlybProductStockLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dlybProductStockLog)){
			return form(dlybProductStockLog, model);
		}
		dlybProductStockLogService.save(dlybProductStockLog);
		addMessage(redirectAttributes, "保存动力越博库存日志成功");
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProductStockLog/?repage";
	}
	
	@RequiresPermissions("dlyb:dlybProductStockLog:edit")
	@RequestMapping(value = "delete")
	public String delete(DlybProductStockLog dlybProductStockLog, RedirectAttributes redirectAttributes) {
		dlybProductStockLogService.delete(dlybProductStockLog);
		addMessage(redirectAttributes, "删除动力越博库存日志成功");
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProductStockLog/?repage";
	}

}