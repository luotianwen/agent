/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.order.entity.Order;
import com.thinkgem.jeesite.modules.agent.order.dao.OrderDao;

/**
 * 代理订单Service
 * @author luotianwen
 * @version 2017-11-03
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends CrudService<OrderDao, Order> {

	public Order get(String id) {
		return super.get(id);
	}
	
	public List<Order> findList(Order order) {
		return super.findList(order);
	}
	
	public Page<Order> findPage(Page<Order> page, Order order) {
		return super.findPage(page, order);
	}
	
	@Transactional(readOnly = false)
	public void save(Order order) {
		super.save(order);
	}
	
	@Transactional(readOnly = false)
	public void delete(Order order) {
		super.delete(order);
	}
	
}