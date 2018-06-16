/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;
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
import com.thinkgem.jeesite.modules.agent.agent.entity.Recharge;
import com.thinkgem.jeesite.modules.agent.agent.service.RechargeService;

/**
 * 充值Controller
 * @author 罗天文
 * @version 2018-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/agent/recharge")
public class RechargeController extends BaseController {

	@Autowired
	private RechargeService rechargeService;

	@ModelAttribute
	public Recharge get(@RequestParam(required=false) String id) {
		Recharge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rechargeService.get(id);
		}
		if (entity == null){
			entity = new Recharge();
		}
		return entity;
	}
	
	@RequiresPermissions("agent:recharge:view")
	@RequestMapping(value = {"list", ""})
	public String list(Recharge recharge, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Recharge> page = rechargeService.findPage(new Page<Recharge>(request, response), recharge); 
		model.addAttribute("page", page);
		return "agent/agent/rechargeList";
	}

	@RequiresPermissions("agent:recharge:view")
	@RequestMapping(value = "form")
	public String form(Recharge recharge, Model model) {
		model.addAttribute("recharge", recharge);
		return "agent/agent/rechargeForm";
	}

	@RequiresPermissions("agent:recharge:edit")
	@RequestMapping(value = "save")
	public String save(Recharge recharge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recharge)){
			return form(recharge, model);
		}
		rechargeService.save(recharge);
		addMessage(redirectAttributes, "保存充值成功");
		return "redirect:"+Global.getAdminPath()+"/agent/recharge/?repage";
	}
	
	@RequiresPermissions("agent:recharge:edit")
	@RequestMapping(value = "delete")
	public String delete(Recharge recharge, RedirectAttributes redirectAttributes) {
		rechargeService.delete(recharge);
		addMessage(redirectAttributes, "删除充值成功");
		return "redirect:"+Global.getAdminPath()+"/agent/recharge/?repage";
	}

}