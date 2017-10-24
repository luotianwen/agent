/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.product.web;

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
import com.thinkgem.jeesite.modules.agent.product.entity.Product;
import com.thinkgem.jeesite.modules.agent.product.service.ProductService;

/**
 * 商品管理Controller
 * @author luotianwen
 * @version 2017-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/product/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	
	@ModelAttribute
	public Product get(@RequestParam(required=false) String id) {
		Product entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productService.get(id);
		}
		if (entity == null){
			entity = new Product();
		}
		return entity;
	}
	
	@RequiresPermissions("product:product:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product); 
		model.addAttribute("page", page);
		return "agent/product/productList";
	}

	@RequiresPermissions("product:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {
		model.addAttribute("product", product);
		return "agent/product/productForm";
	}

	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "save")
	public String save(Product product, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, product)){
			return form(product, model);
		}
		productService.save(product);
		addMessage(redirectAttributes, "保存商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/product/product/?repage";
	}
	
	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "delete")
	public String delete(Product product, RedirectAttributes redirectAttributes) {
		productService.delete(product);
		addMessage(redirectAttributes, "删除商品管理成功");
		return "redirect:"+Global.getAdminPath()+"/product/product/?repage";
	}

}