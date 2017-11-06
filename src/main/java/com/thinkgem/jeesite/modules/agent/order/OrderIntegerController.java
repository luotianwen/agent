/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.order;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;
import com.thinkgem.jeesite.modules.agent.order.entity.Order;
import com.thinkgem.jeesite.modules.agent.order.service.OrderService;
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
@RequestMapping(value = "${adminPath}/order")
public class OrderIntegerController extends BaseController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private StockService stockService;
	private static final Random RANDOM = new Random();
	public static String generateGUID()
	{
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}
	@RequiresPermissions("order:order:mview")
	@RequestMapping(value = "list")
	public String list( HttpServletRequest request, Model model) {

		return "agent/order/orderList";
	}
	@RequiresPermissions("order:order:mview")
	@RequestMapping(value = "qf")
	public String qf( HttpServletRequest request, Model model) {
		String token=generateGUID();
		request.getSession().setAttribute("otoken",token);
		model.addAttribute("otoken",token);
		return "agent/order/orderForm";
	}

	@RequiresPermissions("order:order:medit")
	@RequestMapping(value = "qfsave")
	@ResponseBody
	public String qfsave(Order order,String otoken, HttpServletRequest request,HttpServletResponse response, Model model) {
		Map map=new HashMap();
		map.put("status",0);
		if (!beanValidator(model, order)){
			map.put("status",1);
			map.put("message","验证未通过");
		}
		String  mtoken=(String)request.getSession().getAttribute("otoken");
		if(null==mtoken||null==otoken||"".equals(otoken)||!mtoken.equals(otoken)){
			map.put("status",1);
			map.put("message","别重复提交");
		}
		else{
			try {
				User user = UserUtils.getUser();
				Agent agent = agentService.getUserId(user.getId());
				if (null == agent) {
					map.put("status", 1);
					map.put("message", "代理不存在");
					return renderString(response, map);
				}

				double dd = (agent.getDiscount());
				Stock stock = new Stock();
				stock.setArticleno(order.getArticleno());
				stock.setSize(order.getSize());
				stock.setSex(order.getSex());
				List<Stock> slist = stockService.findList2(stock);
				if (null == slist || slist.size() == 0) {
					stock.setSex("中");
					slist = stockService.findList2(stock);
					if (null == slist || slist.size() == 0) {
						map.put("status", 1);
						map.put("message", "此货号不存在");
						return renderString(response, map);
					}
				}
				Stock s = slist.get(0);
				int num = order.getNum();
				double sd = (s.getDiscount());
				int m = (s.getMarketprice());
				double zd=sd+dd;
				if(sd>=9.6){
					zd=sd;
				}
				else{
					if(zd>=9.6){
						zd=9.6;
					}
				}
				double p = num * m * zd / 10;

				BigDecimal b = new BigDecimal(p);
				int f1 = b.setScale(0, RoundingMode.HALF_UP).intValue();
				order.setAgentid(agent.getId());
				order.setMoney(num * m);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
				String onumber = sdf.format(new Date());
				order.setOnumber(onumber);
				order.setDiscount(zd);
				String remarks = order.getRemarks();
				if (order.getCourier().contains("顺丰")) {
					order.setRemarks(remarks + "售价:" + f1 + ",顺丰快递+15元");
					f1 = f1 + 15;
				} else {
					order.setRemarks(remarks + "售价:" + f1 + ",其他快递+13元");
					f1 = f1 + 13;
				}
				order.setDiscountmoney(f1);
				order.setState("1");
				order.setPaystate("0");
				orderService.save(order);
				request.getSession().removeAttribute("otoken");
			}catch (Exception e){
				e.printStackTrace();
				map.put("status", 1);
				map.put("message", "下单失败!");
				return renderString(response, map);
			}

		}
		return renderString(response,map);
	}

	@RequiresPermissions("order:order:mview")
	@RequestMapping(value = "query")
	public String query( HttpServletRequest request, Model model) {

		return "agent/order/orderQuery";
	}
	@RequiresPermissions("order:order:mview")
	@RequestMapping(value = "data")
	public String data( Order order, Model model) {
		User user = UserUtils.getUser();
		Agent agent=agentService.getUserId(user.getId());
		if(null==order){
			  order=new Order();
		}
		order.setAgentid(agent.getId());
	    List<Order>orders=orderService.findList(order);
		for (Order o:orders ) {
			if(StringUtils.isNotEmpty(o.getDelivernumber()))
			  o.setDelivernumber(o.getCourier()+"_"+o.getDelivernumber());
		}
		model.addAttribute("orders", orders);
		return "agent/order/orderData";
	}
}