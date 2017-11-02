/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.order;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.agent.order.entity.Order;
import com.thinkgem.jeesite.modules.agent.order.service.OrderService;
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
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

	private static final Random RANDOM = new Random();
	public static String generateGUID()
	{
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "list")
	public String list( HttpServletRequest request, Model model) {

		return "agent/order/orderList";
	}
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "qf")
	public String qf( HttpServletRequest request, Model model) {
		String token=generateGUID();
		request.getSession().setAttribute("otoken",token);
		model.addAttribute("otoken",token);
		return "agent/order/orderForm";
	}

	@RequiresPermissions("order:order:edit")
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
			orderService.save(order);
		}
		return renderString(response,map);
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "query")
	public String query( HttpServletRequest request, Model model) {

		return "agent/order/orderQuery";
	}
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "data")
	public String data( HttpServletRequest request, Model model) {

		return "agent/order/orderData";
	}
}