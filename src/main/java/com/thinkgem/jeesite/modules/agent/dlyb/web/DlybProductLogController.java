/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.web;

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
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductLog;
import com.thinkgem.jeesite.modules.agent.dlyb.service.DlybProductLogService;

import java.util.List;

/**
 * 动力越博商品日志Controller
 * @author 罗天文
 * @version 2018-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/dlyb/dlybProductLog")
public class DlybProductLogController extends BaseController {

	@Autowired
	private DlybProductLogService dlybProductLogService;
	
	@ModelAttribute
	public DlybProductLog get(@RequestParam(required=false) String id) {
		DlybProductLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dlybProductLogService.get(id);
		}
		if (entity == null){
			entity = new DlybProductLog();
		}
		return entity;
	}
	
	@RequiresPermissions("dlyb:dlybProductLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(DlybProductLog dlybProductLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DlybProductLog> page = dlybProductLogService.findPage(new Page<DlybProductLog>(request, response), dlybProductLog); 
		model.addAttribute("page", page);
		return "agent/dlyb/dlybProductLogList";
	}

	@RequiresPermissions("dlyb:dlybProductLog:view")
	@RequestMapping(value = "form")
	public String form(DlybProductLog dlybProductLog, Model model) {
		model.addAttribute("dlybProductLog", dlybProductLog);
		return "agent/dlyb/dlybProductLogForm";
	}

	@RequiresPermissions("dlyb:dlybProductLog:edit")
	@RequestMapping(value = "save")
	public String save(DlybProductLog dlybProductLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dlybProductLog)){
			return form(dlybProductLog, model);
		}
		dlybProductLogService.save(dlybProductLog);
		addMessage(redirectAttributes, "保存动力越博商品日志成功");
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProductLog/?repage";
	}
	
	@RequiresPermissions("dlyb:dlybProductLog:edit")
	@RequestMapping(value = "delete")
	public String delete(DlybProductLog dlybProductLog, RedirectAttributes redirectAttributes) {
		dlybProductLogService.delete(dlybProductLog);
		addMessage(redirectAttributes, "删除动力越博商品日志成功");
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProductLog/?repage";
	}
	@RequiresPermissions("dlyb:dlybProductLog:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(DlybProductLog aSimpleOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "商品变动数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";

			if(null==aSimpleOrder){
				aSimpleOrder=new DlybProductLog();
			}
			List<DlybProductLog> list=dlybProductLogService.findList(aSimpleOrder);
			new ExportExcel("商品变动数据", DlybProductLog.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProductLog/?repage";
	}
}