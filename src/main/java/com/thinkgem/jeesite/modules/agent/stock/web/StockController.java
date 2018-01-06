/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.stock.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import com.thinkgem.jeesite.modules.agent.stock.entity.Stock;
import com.thinkgem.jeesite.modules.agent.stock.service.StockService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 库存Controller
 * @author luotianwen
 * @version 2017-10-29
 */
@Controller
@RequestMapping(value = "${adminPath}/stock/stock")
public class StockController extends BaseController {
	@Autowired
	private AgentService agentService;
	@Autowired
	private StockService stockService;
	
	@ModelAttribute
	public Stock get(@RequestParam(required=false) String id) {
		Stock entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = stockService.get(id);
		}
		if (entity == null){
			entity = new Stock();
		}
		return entity;
	}
	
	@RequiresPermissions("stock:stock:view")
	@RequestMapping(value = {"list", ""})
	public String list(Stock stock, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Stock> page = stockService.findPage(new Page<Stock>(request, response), stock); 
		model.addAttribute("page", page);
		return "agent/stock/stockList";
	}

	@RequiresPermissions("stock:stock:view")
	@RequestMapping(value = "form")
	public String form(Stock stock, Model model) {
		model.addAttribute("stock", stock);
		return "agent/stock/stockForm";
	}

	@RequiresPermissions("stock:stock:edit")
	@RequestMapping(value = "save")
	public String save(Stock stock, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, stock)){
			return form(stock, model);
		}
		stockService.save(stock);
		addMessage(redirectAttributes, "保存库存成功");
		return "redirect:"+Global.getAdminPath()+"/stock/stock/?repage";
	}
	
	@RequiresPermissions("stock:stock:edit")
	@RequestMapping(value = "delete")
	public String delete(Stock stock, RedirectAttributes redirectAttributes) {
		stockService.delete(stock);
		addMessage(redirectAttributes, "删除库存成功");
		return "redirect:"+Global.getAdminPath()+"/stock/stock/?repage";
	}
	@RequiresPermissions("stock:stock:mview")
	@RequestMapping(value = {"query"})
	public String query(Stock stock, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "agent/stock/mstockList";
	}

	@RequiresPermissions("stock:stock:mview")
	@RequestMapping(value = {"data"})
	public String data(Stock stock, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isEmpty(stock.getArticleno())){
			return "agent/stock/data";
		}
		List<Stock> sList = Lists.newArrayList();
		User user =UserUtils.getUser();
		Agent agent=agentService.getUserId(user.getId());
		if(null!=agent&&!StringUtils.isEmpty(agent.getDiscountid())) {
			Page<Stock> page = stockService.findPage2(new Page<Stock>(request, response), stock);
			List<Stock> list=page.getList();
			if (null != list && list.size() > 0) {
				for (Stock s : list) {
					double sd=(s.getDiscount());
					int sm=(s.getMarketprice());
					double ad=(agent.getDiscount());
					double zd=sd+ad;
					if(sd>=9.6){
						zd=sd;
					}
					else{
						if(zd>=9.6){
							zd=9.6;
						}
					}
					double p1=sm*zd/10;
					BigDecimal b   =   new   BigDecimal(p1);
					int   p   =   b.setScale(0,   RoundingMode.HALF_UP).intValue();
					s.setDiscount(zd);
					s.setPrice(p);
					sList.add(s);
				}
			}
			page.setList(sList);
			model.addAttribute("page", page);
		}
		return "agent/stock/data";
	}
}