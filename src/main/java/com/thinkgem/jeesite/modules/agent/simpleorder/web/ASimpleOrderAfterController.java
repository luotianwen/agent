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
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.ASimpleOrderAfter;
import com.thinkgem.jeesite.modules.agent.simpleorder.service.ASimpleOrderAfterService;

/**
 * 订单售后Controller
 * @author 罗天文
 * @version 2018-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/simpleorder/aSimpleOrderAfter")
public class ASimpleOrderAfterController extends BaseController {

	@Autowired
	private ASimpleOrderAfterService aSimpleOrderAfterService;
	
	@ModelAttribute
	public ASimpleOrderAfter get(@RequestParam(required=false) String id) {
		ASimpleOrderAfter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = aSimpleOrderAfterService.get(id);
		}
		if (entity == null){
			entity = new ASimpleOrderAfter();
		}
		return entity;
	}
	
	@RequiresPermissions("simpleorder:aSimpleOrderAfter:view")
	@RequestMapping(value = {"list", ""})
	public String list(ASimpleOrderAfter aSimpleOrderAfter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ASimpleOrderAfter> page = aSimpleOrderAfterService.findPage(new Page<ASimpleOrderAfter>(request, response), aSimpleOrderAfter); 
		model.addAttribute("page", page);
		return "agent/simpleorder/aSimpleOrderAfterList";
	}

	@RequiresPermissions("simpleorder:aSimpleOrderAfter:view")
	@RequestMapping(value = "form")
	public String form(ASimpleOrderAfter aSimpleOrderAfter, Model model) {
		model.addAttribute("aSimpleOrderAfter", aSimpleOrderAfter);
		return "agent/simpleorder/aSimpleOrderAfterForm";
	}

	@RequiresPermissions("simpleorder:aSimpleOrderAfter:edit")
	@RequestMapping(value = "save")
	public String save(ASimpleOrderAfter aSimpleOrderAfter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, aSimpleOrderAfter)){
			return form(aSimpleOrderAfter, model);
		}
		aSimpleOrderAfterService.save(aSimpleOrderAfter);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/aSimpleOrderAfter/?repage";
	}
	
	@RequiresPermissions("simpleorder:aSimpleOrderAfter:edit")
	@RequestMapping(value = "delete")
	public String delete(ASimpleOrderAfter aSimpleOrderAfter, RedirectAttributes redirectAttributes) {
		aSimpleOrderAfterService.delete(aSimpleOrderAfter);
		addMessage(redirectAttributes, "删除订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/aSimpleOrderAfter/?repage";
	}

}