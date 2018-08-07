/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductStock;
import com.thinkgem.jeesite.modules.agent.dlyb.service.DlybProductStockService;

/**
 * 动力越博库存Controller
 * @author 罗天文
 * @version 2018-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/dlyb/dlybProductStock")
public class DlybProductStockController extends BaseController {

	@Autowired
	private DlybProductStockService dlybProductStockService;
	
	@ModelAttribute
	public DlybProductStock get(@RequestParam(required=false) String id) {
		DlybProductStock entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dlybProductStockService.get(id);
		}
		if (entity == null){
			entity = new DlybProductStock();
		}
		return entity;
	}
	
	@RequiresPermissions("dlyb:dlybProductStock:view")
	@RequestMapping(value = {"list", ""})
	public String list(DlybProductStock dlybProductStock, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DlybProductStock> page = dlybProductStockService.findPage(new Page<DlybProductStock>(request, response), dlybProductStock); 
		model.addAttribute("page", page);
		return "agent/dlyb/dlybProductStockList";
	}

	@RequiresPermissions("dlyb:dlybProductStock:view")
	@RequestMapping(value = "form")
	public String form(DlybProductStock dlybProductStock, Model model) {
		model.addAttribute("dlybProductStock", dlybProductStock);
		return "agent/dlyb/dlybProductStockForm";
	}

	@RequiresPermissions("dlyb:dlybProductStock:edit")
	@RequestMapping(value = "save")
	public String save(DlybProductStock dlybProductStock, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dlybProductStock)){
			return form(dlybProductStock, model);
		}
		dlybProductStockService.save(dlybProductStock);
		addMessage(redirectAttributes, "保存动力越博库存成功");
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProductStock/?repage";
	}
	
	@RequiresPermissions("dlyb:dlybProductStock:edit")
	@RequestMapping(value = "delete")
	public String delete(DlybProductStock dlybProductStock, RedirectAttributes redirectAttributes) {
		dlybProductStockService.delete(dlybProductStock);
		addMessage(redirectAttributes, "删除动力越博库存成功");
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProductStock/?repage";
	}

}