/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.service;

import java.util.List;

import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrderAfter;
import com.thinkgem.jeesite.modules.agent.simpleorder.dao.SimpleOrderAfterDao;

/**
 * 订单售后Service
 * @author 罗天文
 * @version 2018-08-03
 */
@Service
@Transactional(readOnly = true)
public class SimpleOrderAfterService extends CrudService<SimpleOrderAfterDao, SimpleOrderAfter> {

	public SimpleOrderAfter get(String id) {
		return super.get(id);
	}
	
	public List<SimpleOrderAfter> findList(SimpleOrderAfter simpleOrderAfter) {
		return super.findList(simpleOrderAfter);
	}
	
	public Page<SimpleOrderAfter> findPage(Page<SimpleOrderAfter> page, SimpleOrderAfter simpleOrderAfter) {
		return super.findPage(page, simpleOrderAfter);
	}
	
	@Transactional(readOnly = false)
	public void save(SimpleOrderAfter simpleOrderAfter) {
		super.save(simpleOrderAfter);
	}
	
	@Transactional(readOnly = false)
	public void delete(SimpleOrderAfter simpleOrderAfter) {
		super.delete(simpleOrderAfter);
	}
	@Transactional(readOnly = false)
	public void aftersave(SimpleOrderAfter aSimpleOrderAfter) {
		super.save(aSimpleOrderAfter);
		SimpleOrder simpleOrder=new SimpleOrder();
		simpleOrder.setOrderId(aSimpleOrderAfter.getOrderId());
		aSimpleOrderService.aftersave(simpleOrder);
	}


	@Autowired
	private SimpleOrderService aSimpleOrderService;
	@Transactional(readOnly = false)
	public void backaddress(SimpleOrderAfter simpleOrderAfter) {
		SimpleOrder simpleOrder=new SimpleOrder();
		simpleOrder.setOrderId(simpleOrderAfter.getOrderId());
		aSimpleOrderService.aftersaveok(simpleOrder);
		dao.backaddress(simpleOrderAfter);
	}
	@Transactional(readOnly = false)
	public void backcourier(SimpleOrderAfter simpleOrderAfter) {
		SimpleOrder simpleOrder=new SimpleOrder();
		simpleOrder.setOrderId(simpleOrderAfter.getOrderId());
		aSimpleOrderService.aftersavepass(simpleOrder);
		dao.backcourier(simpleOrderAfter);
	}
	@Transactional(readOnly = false)
	public void courier(SimpleOrderAfter simpleOrderAfter) {
		dao.courier(simpleOrderAfter);
	}
}