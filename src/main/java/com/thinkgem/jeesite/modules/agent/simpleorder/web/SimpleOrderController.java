/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.agent.simpleorder.service.SimpleOrderService;
import sun.management.resources.agent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 下单管理Controller
 * @author 罗天文
 * @version 2018-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/simpleorder/simpleOrder")
public class SimpleOrderController extends BaseController {

	@Autowired
	private SimpleOrderService simpleOrderService;
	
	@ModelAttribute
	public SimpleOrder get(@RequestParam(required=false) String id) {
		SimpleOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = simpleOrderService.get(id);
		}
		if (entity == null){
			entity = new SimpleOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("simpleorder:simpleOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SimpleOrder simpleOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SimpleOrder> page = simpleOrderService.findPage(new Page<SimpleOrder>(request, response), simpleOrder); 
		model.addAttribute("page", page);
		model.addAttribute("simpleOrder2", simpleOrderService.sum(simpleOrder));
		return "agent/simpleorder/simpleOrderList";
	}

	@RequiresPermissions("simpleorder:simpleOrder:view")
	@RequestMapping(value = "form")
	public String form(SimpleOrder simpleOrder, Model model) {
		model.addAttribute("simpleOrder", simpleOrder);
		return "agent/simpleorder/simpleOrderForm";
	}
	@RequiresPermissions("simpleorder:simpleOrder:view")
	@RequestMapping(value = "deliverform")
	public String deliverform(SimpleOrder simpleOrder, Model model) {
		simpleOrder.setState("3");
		model.addAttribute("simpleOrder", simpleOrder);
		return "agent/simpleorder/simpleOrderdeliverForm";
	}

	@RequiresPermissions("simpleorder:simpleOrder:edit")
	@RequestMapping(value = "save")
	public String save(SimpleOrder simpleOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, simpleOrder)){
			return form(simpleOrder, model);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String onumber = sdf.format(new Date());
		simpleOrder.setOrderId(onumber);
		simpleOrderService.save(simpleOrder);
		addMessage(redirectAttributes, "保存下单管理成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrder/?repage";
	}
	@RequiresPermissions("simpleorder:simpleOrder:edit")
	@RequestMapping(value = "deliver")
	public String deliver(SimpleOrder simpleOrder, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (!beanValidator(model, simpleOrder)){
			return deliverform(simpleOrder, model);
		}
		simpleOrderService.deliver(simpleOrder);
		addMessage(redirectAttributes, "保存下单管理成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrder/?repage";
	}
	@RequiresPermissions("simpleorder:simpleOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(SimpleOrder simpleOrder, RedirectAttributes redirectAttributes) {
		simpleOrderService.delete(simpleOrder);
		addMessage(redirectAttributes, "删除下单管理成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrder/?repage";
	}
	@RequiresPermissions("simpleorder:simpleOrder:edit")
	@RequestMapping(value = "isaccount")
	public String isaccount(SimpleOrder simpleOrder, RedirectAttributes redirectAttributes) {
		simpleOrderService.isaccount(simpleOrder);
		addMessage(redirectAttributes, "对账成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrder/?repage";
	}
	@RequiresPermissions("simpleorder:simpleOrder:edit")
	@RequestMapping(value = "account")
	public String account(String ids, RedirectAttributes redirectAttributes) {
		simpleOrderService.account(ids);
		addMessage(redirectAttributes, "对账成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrder/?repage";
	}
	@RequiresPermissions("simpleorder:simpleOrder:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(SimpleOrder aSimpleOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "订单数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";

			if(null==aSimpleOrder){
				aSimpleOrder=new SimpleOrder();
			}
			List<SimpleOrder> list=simpleOrderService.findList(aSimpleOrder);
			new ExportExcel("订单数据", SimpleOrder.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/simpleorder/simpleOrder/list?repage";
	}
}