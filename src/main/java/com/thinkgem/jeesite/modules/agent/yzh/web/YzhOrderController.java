/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.yzh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.agent.simpleorder.service.SimpleOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.yzh.entity.YzhOrder;
import com.thinkgem.jeesite.modules.agent.yzh.service.YzhOrderService;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 云中鹤订单管理Controller
 * @author 云中鹤订单管理
 * @version 2020-06-02
 */
@Controller
@RequestMapping(value = "${adminPath}/yzh/yzhOrder")
public class YzhOrderController extends BaseController {

	@Autowired
	private YzhOrderService yzhOrderService;
	
	@ModelAttribute
	public YzhOrder get(@RequestParam(required=false) String id) {
		YzhOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = yzhOrderService.get(id);
		}
		if (entity == null){
			entity = new YzhOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("yzh:yzhOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(YzhOrder yzhOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YzhOrder> page = yzhOrderService.findPage(new Page<YzhOrder>(request, response), yzhOrder); 
		model.addAttribute("page", page);
		return "agent/yzh/yzhOrderList";
	}
	@Autowired
	private SimpleOrderService simpleOrderService;

	private static final String province= "api/area/province.php";
	private static final String city= "api/area/city.php";
	private static final String county= "api/area/county.php";


	private List<YzhProvice.Resultdatabean> getProvvice(){
		String WID=Global.getConfig("yzh.WID");
		String accessToken=Global.getConfig("yzh.accessToken");
		String yzhURL=Global.getConfig("yzh.Url");
		long timestamp=System.currentTimeMillis();
		Map map = new HashMap();
		map.put("wid", WID);
		//MD5(wid + accessToken + 时间戳)，值再转大写
		map.put("token", Cont.md5(WID+accessToken+timestamp).toUpperCase());
		map.put("timestamp", timestamp+"");


		String str = Cont.post(yzhURL+province, map).toLowerCase();
		ObjectMapper objectMapper = new ObjectMapper();
		YzhProvice j = null;
		try {
			j = objectMapper.readValue(str, YzhProvice.class);
			return j.getResult_data();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<YzhProvice.Resultdatabean> getCity(String code){
		String WID=Global.getConfig("yzh.WID");
		String accessToken=Global.getConfig("yzh.accessToken");
		String yzhURL=Global.getConfig("yzh.Url");
		long timestamp=System.currentTimeMillis();
		Map map = new HashMap();
		map.put("wid", WID);
		//MD5(wid + accessToken + 时间戳)，值再转大写
		map.put("token", Cont.md5(WID+accessToken+timestamp).toUpperCase());
		map.put("timestamp", timestamp+"");

		map.put("province", code);
		String str = Cont.post(yzhURL+city, map).toLowerCase();
		ObjectMapper objectMapper = new ObjectMapper();
		YzhProvice j = null;
		try {
			j = objectMapper.readValue(str, YzhProvice.class);
			return j.getResult_data();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<YzhProvice.Resultdatabean> getCounty(String code){
		String WID=Global.getConfig("yzh.WID");
		String accessToken=Global.getConfig("yzh.accessToken");
		String yzhURL=Global.getConfig("yzh.Url");
		long timestamp=System.currentTimeMillis();
		Map map = new HashMap();
		map.put("wid", WID);
		//MD5(wid + accessToken + 时间戳)，值再转大写
		map.put("token", Cont.md5(WID+accessToken+timestamp).toUpperCase());
		map.put("timestamp", timestamp+"");

		map.put("city", code);
		String str = Cont.post(yzhURL+county, map).toLowerCase();
		ObjectMapper objectMapper = new ObjectMapper();
		YzhProvice j = null;
		try {
			j = objectMapper.readValue(str, YzhProvice.class);
			return j.getResult_data();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequiresPermissions("yzh:yzhOrder:view")
	@RequestMapping(value = "yunOrder")
	public String yunOrder(YzhOrder yzhOrder2, Model model) {
		SimpleOrder o=simpleOrderService.get(yzhOrder2.getId());
		YzhOrder yzhOrder=new YzhOrder();
		yzhOrder.setThirdorder(o.getOrderId());
		yzhOrder.setAmount(o.getNum());
		yzhOrder.setAddress(o.getAddress());
		yzhOrder.setMobile(o.getPhone());
		yzhOrder.setReceivername(o.getConsignee());
		yzhOrder.setIsaccount("0");
		List<YzhProvice.Resultdatabean> ps= (List<YzhProvice.Resultdatabean>) CacheUtils.get("provices");
		if(null==ps){
			ps=this.getProvvice();
			CacheUtils.put("provices",ps);
		}
		model.addAttribute("provices",ps);

		model.addAttribute("yzhOrder", yzhOrder);

		return "agent/yzh/yzhOrderForm";
	}

	@RequiresPermissions("yzh:yzhOrder:view")
	@ResponseBody
	@RequestMapping(value = "city")
	public List<YzhProvice.Resultdatabean> city(String code, Model model) {
		List<YzhProvice.Resultdatabean> ps= (List<YzhProvice.Resultdatabean>) CacheUtils.get("city-"+code);
		if(null==ps){
			ps=this.getCity(code);
			CacheUtils.put("city-"+code,ps);
		}
		return  ps;
	}
	@RequiresPermissions("yzh:yzhOrder:view")
	@ResponseBody
	@RequestMapping(value = "county")
	public List<YzhProvice.Resultdatabean> county(String code, Model model) {
		List<YzhProvice.Resultdatabean> ps= (List<YzhProvice.Resultdatabean>) CacheUtils.get("county-"+code);
		if(null==ps){
			ps=this.getCounty(code);
			CacheUtils.put("county-"+code,ps);
		}
		return  ps;
	}

	@RequiresPermissions("yzh:yzhOrder:view")
	@RequestMapping(value = "after")
	public String after(YzhOrder yzhOrder, Model model) {
		model.addAttribute("yzhOrder", yzhOrder);
		return "agent/yzh/yzhOrderFormafter";
	}
	@RequiresPermissions("yzh:yzhOrder:view")
	@RequestMapping(value = "saveafter")
	public String saveafter(YzhOrder yzhOrder, Model model) {

				yzhOrderService.saveafter(yzhOrder);
		return "redirect:"+Global.getAdminPath()+"/yzh/yzhOrder/?repage";
	}
	@RequiresPermissions("yzh:yzhOrder:view")
	@RequestMapping(value = "returnedAddress")
	public String returnedAddress(YzhOrder yzhOrder, Model model) {

		yzhOrderService.returnedAddress(yzhOrder);
		return "redirect:"+Global.getAdminPath()+"/yzh/yzhOrder/?repage";
	}

	@RequiresPermissions("yzh:yzhOrder:view")
	@RequestMapping(value = "form")
	public String form(YzhOrder yzhOrder, Model model) {
		model.addAttribute("yzhOrder", yzhOrder);
		return "agent/yzh/yzhOrderForm";
	}
	@RequiresPermissions("yzh:yzhOrder:view")
	@RequestMapping(value = "kd")
	public String kd(YzhOrder yzhOrder, Model model) throws Exception {
		yzhOrderService.kd(yzhOrder);
		return "redirect:"+Global.getAdminPath()+"/yzh/yzhOrder/?repage";
	}

	@RequiresPermissions("yzh:yzhOrder:edit")
	@RequestMapping(value = "save")
	public String save(YzhOrder yzhOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, yzhOrder)){
			return form(yzhOrder, model);
		}
		yzhOrderService.save(yzhOrder);
		addMessage(redirectAttributes, "保存云中鹤订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/yzh/yzhOrder/?repage";
	}
	
	@RequiresPermissions("yzh:yzhOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(YzhOrder yzhOrder, RedirectAttributes redirectAttributes) {
		yzhOrderService.delete(yzhOrder);
		addMessage(redirectAttributes, "删除云中鹤订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/yzh/yzhOrder/?repage";
	}

}