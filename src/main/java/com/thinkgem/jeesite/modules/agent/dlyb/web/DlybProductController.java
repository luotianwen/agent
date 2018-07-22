/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProduct;
import com.thinkgem.jeesite.modules.agent.dlyb.service.DlybProductService;

import java.util.List;

/**
 * 动力越博商品Controller
 * @author 罗天文
 * @version 2018-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/dlyb/dlybProduct")
public class DlybProductController extends BaseController {

	@Autowired
	private DlybProductService dlybProductService;
	
	@ModelAttribute
	public DlybProduct get(@RequestParam(required=false) String id) {
		DlybProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dlybProductService.get(id);
		}
		if (entity == null){
			entity = new DlybProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("dlyb:dlybProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(DlybProduct dlybProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DlybProduct> page = dlybProductService.findPage(new Page<DlybProduct>(request, response), dlybProduct); 
		model.addAttribute("page", page);
		return "agent/dlyb/dlybProductList";
	}

	@RequiresPermissions("dlyb:dlybProduct:view")
	@RequestMapping(value = "form")
	public String form(DlybProduct dlybProduct, Model model) {
		model.addAttribute("dlybProduct", dlybProduct);
		return "agent/dlyb/dlybProductForm";
	}

	@RequiresPermissions("dlyb:dlybProduct:edit")
	@RequestMapping(value = "save")
	public String save(DlybProduct dlybProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dlybProduct)){
			return form(dlybProduct, model);
		}
		dlybProductService.save(dlybProduct);
		addMessage(redirectAttributes, "保存动力越博商品成功");
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProduct/?repage";
	}
	
	@RequiresPermissions("dlyb:dlybProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(DlybProduct dlybProduct, RedirectAttributes redirectAttributes) {
		dlybProductService.delete(dlybProduct);
		addMessage(redirectAttributes, "删除动力越博商品成功");
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProduct/?repage";
	}

	/**
	 *
	 * @param DlybProduct
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dlyb:dlybProduct:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(DlybProduct DlybProduct, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "商品数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<DlybProduct> page = dlybProductService.findPage(new Page<DlybProduct>(request, response, -1),DlybProduct);
			new ExportExcel("商品数据", DlybProduct.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProduct/?repage";
	}

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dlyb:dlybProduct:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {

		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DlybProduct> list = ei.getDataList(DlybProduct.class);
			dlybProductService.deleteALL();
			for (DlybProduct dlybProduct : list){
				try{
					dlybProductService.save(dlybProduct);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/> "+dlybProduct.getArticleno()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/> "+dlybProduct.getArticleno()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProduct/?repage";
	}

	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dlyb:dlybProduct:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "数据导入模板.xlsx";
			List<DlybProduct> list = Lists.newArrayList();
			list.add(dlybProductService.findList(new DlybProduct()).get(0));
			new ExportExcel("数据", DlybProduct.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/dlyb/dlybProduct/?repage";
	}
}