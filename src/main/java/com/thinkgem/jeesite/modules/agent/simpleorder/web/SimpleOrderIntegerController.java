/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;
import com.thinkgem.jeesite.modules.agent.order.entity.Order;
import com.thinkgem.jeesite.modules.agent.order.service.OrderService;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.agent.simpleorder.service.SimpleOrderService;
import com.thinkgem.jeesite.modules.agent.stock.entity.Stock;
import com.thinkgem.jeesite.modules.agent.stock.service.StockService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代理订单Controller
 * @author luotianwen
 * @version 2017-10-29
 */
@Controller
@RequestMapping(value = "${adminPath}/msimpleorder")
public class SimpleOrderIntegerController extends BaseController {


	@Autowired
	private SimpleOrderService aSimpleOrderService;
	@Autowired
	private AgentService agentService;
	@ModelAttribute
	public SimpleOrder get(@RequestParam(required=false) String id) {
		SimpleOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = aSimpleOrderService.get(id);
		}
		if (entity == null){
			entity = new SimpleOrder();
		}
		return entity;
	}

	@RequiresPermissions("simpleorder:aSimpleOrder:mview")
	@RequestMapping(value = {"list", ""})
	public String list(SimpleOrder aSimpleOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Agent agent=agentService.getUserId(user.getId());
		if(null==aSimpleOrder){
			aSimpleOrder=new SimpleOrder();
		}
		model.addAttribute("agent", agent);
		aSimpleOrder.setAgentid(agent.getId());
		Page<SimpleOrder> page = aSimpleOrderService.findPage(new Page<SimpleOrder>(request, response), aSimpleOrder);
		model.addAttribute("page", page);
		return "agent/simpleorder/aSimpleOrderList";
	}

	@RequiresPermissions("simpleorder:aSimpleOrder:mview")
	@RequestMapping(value = "form")
	public String form(SimpleOrder aSimpleOrder, Model model) {
		model.addAttribute("aSimpleOrder", aSimpleOrder);
		return "agent/simpleorder/aSimpleOrderForm";
	}

	@RequiresPermissions("simpleorder:aSimpleOrder:mview")
	@RequestMapping(value = "save")
	public String save(SimpleOrder aSimpleOrder, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, aSimpleOrder)){
			return form(aSimpleOrder, model);
		}
		User user = UserUtils.getUser();
		Agent agent=agentService.getUserId(user.getId());
		aSimpleOrder.setState("1");
		aSimpleOrder.setAgentid(agent.getId());
		aSimpleOrderService.asave(aSimpleOrder);
		addMessage(redirectAttributes, "保存下单管理成功");
		return "redirect:"+ Global.getAdminPath()+"/msimpleorder/?repage";
	}

	@RequiresPermissions("simpleorder:aSimpleOrder:mview")
	@RequestMapping(value = "delete")
	public String delete(SimpleOrder aSimpleOrder, RedirectAttributes redirectAttributes) {
		aSimpleOrderService.delete(aSimpleOrder);
		addMessage(redirectAttributes, "删除下单管理成功");
		return "redirect:"+Global.getAdminPath()+"/msimpleorder/?repage";
	}

}