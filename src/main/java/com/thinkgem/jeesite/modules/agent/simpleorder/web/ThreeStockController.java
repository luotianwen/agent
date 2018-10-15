/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.web;

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
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.ThreeStock;
import com.thinkgem.jeesite.modules.agent.simpleorder.service.ThreeStockService;

/**
 * 仓库管理Controller
 * @author 罗天文
 * @version 2018-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/simpleorder/threeStock")
public class ThreeStockController extends BaseController {

	@Autowired
	private ThreeStockService threeStockService;
	
	@ModelAttribute
	public ThreeStock get(@RequestParam(required=false) String id) {
		ThreeStock entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = threeStockService.get(id);
		}
		if (entity == null){
			entity = new ThreeStock();
		}
		return entity;
	}
	
	@RequiresPermissions("simpleorder:threeStock:view")
	@RequestMapping(value = {"list", ""})
	public String list(ThreeStock threeStock, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ThreeStock> page = threeStockService.findPage(new Page<ThreeStock>(request, response), threeStock); 
		model.addAttribute("page", page);
		return "agent/simpleorder/threeStockList";
	}

	@RequiresPermissions("simpleorder:threeStock:view")
	@RequestMapping(value = "form")
	public String form(ThreeStock threeStock, Model model) {
		model.addAttribute("threeStock", threeStock);
		return "agent/simpleorder/threeStockForm";
	}

	@RequiresPermissions("simpleorder:threeStock:edit")
	@RequestMapping(value = "save")
	public String save(ThreeStock threeStock, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, threeStock)){
			return form(threeStock, model);
		}
		threeStockService.save(threeStock);
		addMessage(redirectAttributes, "保存仓库管理成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/threeStock/?repage";
	}
	
	@RequiresPermissions("simpleorder:threeStock:edit")
	@RequestMapping(value = "delete")
	public String delete(ThreeStock threeStock, RedirectAttributes redirectAttributes) {
		threeStockService.delete(threeStock);
		addMessage(redirectAttributes, "删除仓库管理成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/threeStock/?repage";
	}

}