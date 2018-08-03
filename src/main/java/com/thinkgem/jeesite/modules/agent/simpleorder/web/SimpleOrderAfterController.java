/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
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
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrderAfter;
import com.thinkgem.jeesite.modules.agent.simpleorder.service.SimpleOrderAfterService;

import java.util.List;

/**
 * 订单售后Controller
 * @author 罗天文
 * @version 2018-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/simpleorder/simpleOrderAfter")
public class SimpleOrderAfterController extends BaseController {

	@Autowired
	private SimpleOrderAfterService simpleOrderAfterService;
	
	@ModelAttribute
	public SimpleOrderAfter get(@RequestParam(required=false) String id) {
		SimpleOrderAfter entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = simpleOrderAfterService.get(id);
		}
		if (entity == null){
			entity = new SimpleOrderAfter();
		}
		return entity;
	}
	
	@RequiresPermissions("simpleorder:simpleOrderAfter:view")
	@RequestMapping(value = {"list", ""})
	public String list(SimpleOrderAfter simpleOrderAfter, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SimpleOrderAfter> page = simpleOrderAfterService.findPage(new Page<SimpleOrderAfter>(request, response), simpleOrderAfter); 
		model.addAttribute("page", page);
		return "agent/simpleorder/simpleOrderAfterList";
	}

	@RequiresPermissions("simpleorder:simpleOrderAfter:view")
	@RequestMapping(value = "form")
	public String form(SimpleOrderAfter simpleOrderAfter, Model model) {
		model.addAttribute("simpleOrderAfter", simpleOrderAfter);
		return "agent/simpleorder/simpleOrderAfterForm";
	}

	@RequiresPermissions("simpleorder:simpleOrderAfter:edit")
	@RequestMapping(value = "backaddress")
	public String backaddress(SimpleOrderAfter simpleOrderAfter, Model model, RedirectAttributes redirectAttributes) {

		simpleOrderAfterService.backaddress(simpleOrderAfter);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrderAfter/?repage";
	}
	@RequiresPermissions("simpleorder:simpleOrderAfter:edit")
	@RequestMapping(value = "backcourier")
	public String backcourier(SimpleOrderAfter simpleOrderAfter, Model model, RedirectAttributes redirectAttributes) {

		simpleOrderAfterService.backcourier(simpleOrderAfter);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrderAfter/?repage";
	}

	@RequiresPermissions("simpleorder:simpleOrderAfter:edit")
	@RequestMapping(value = "save")
	public String save(SimpleOrderAfter simpleOrderAfter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, simpleOrderAfter)){
			return form(simpleOrderAfter, model);
		}
		simpleOrderAfterService.save(simpleOrderAfter);
		addMessage(redirectAttributes, "保存订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrderAfter/?repage";
	}
	
	@RequiresPermissions("simpleorder:simpleOrderAfter:edit")
	@RequestMapping(value = "delete")
	public String delete(SimpleOrderAfter simpleOrderAfter, RedirectAttributes redirectAttributes) {
		simpleOrderAfterService.delete(simpleOrderAfter);
		addMessage(redirectAttributes, "删除订单售后成功");
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrderAfter/?repage";
	}
	@RequiresPermissions("simpleorder:simpleOrderAfter:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(SimpleOrderAfter aSimpleOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "售后数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";

			if(null==aSimpleOrder){
				aSimpleOrder=new SimpleOrderAfter();
			}
			List<SimpleOrderAfter> list=simpleOrderAfterService.findList(aSimpleOrder);
			new ExportExcel(null, SimpleOrderAfter.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/simpleorder/simpleOrderAfter/?repage";
	}
}