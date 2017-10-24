/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.web;

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
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;

/**
 * 代理设置Controller
 * @author luotianwen
 * @version 2017-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/agent/agent")
public class AgentController extends BaseController {

	@Autowired
	private AgentService agentService;
	
	@ModelAttribute
	public Agent get(@RequestParam(required=false) String id) {
		Agent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = agentService.get(id);
		}
		if (entity == null){
			entity = new Agent();
		}
		return entity;
	}
	
	@RequiresPermissions("agent:agent:view")
	@RequestMapping(value = {"list", ""})
	public String list(Agent agent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Agent> page = agentService.findPage(new Page<Agent>(request, response), agent); 
		model.addAttribute("page", page);
		return "agent/agent/agentList";
	}

	@RequiresPermissions("agent:agent:view")
	@RequestMapping(value = "form")
	public String form(Agent agent, Model model) {
		model.addAttribute("agent", agent);
		return "agent/agent/agentForm";
	}

	@RequiresPermissions("agent:agent:edit")
	@RequestMapping(value = "save")
	public String save(Agent agent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, agent)){
			return form(agent, model);
		}
		agentService.save(agent);
		addMessage(redirectAttributes, "保存代理设置成功");
		return "redirect:"+Global.getAdminPath()+"/agent/agent/?repage";
	}
	
	@RequiresPermissions("agent:agent:edit")
	@RequestMapping(value = "delete")
	public String delete(Agent agent, RedirectAttributes redirectAttributes) {
		agentService.delete(agent);
		addMessage(redirectAttributes, "删除代理设置成功");
		return "redirect:"+Global.getAdminPath()+"/agent/agent/?repage";
	}

}