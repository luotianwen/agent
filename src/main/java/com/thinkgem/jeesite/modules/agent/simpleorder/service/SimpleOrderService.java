/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.agent.simpleorder.dao.SimpleOrderDao;

/**
 * 下单管理Service
 * @author 罗天文
 * @version 2018-06-10
 */
@Service
@Transactional(readOnly = true)
public class SimpleOrderService extends CrudService<SimpleOrderDao, SimpleOrder> {

	public SimpleOrder get(String id) {
		return super.get(id);
	}
	
	public List<SimpleOrder> findList(SimpleOrder simpleOrder) {
		return super.findList(simpleOrder);
	}
	
	public Page<SimpleOrder> findPage(Page<SimpleOrder> page, SimpleOrder simpleOrder) {
		return super.findPage(page, simpleOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(SimpleOrder simpleOrder) {
		super.save(simpleOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(SimpleOrder simpleOrder) {
		super.delete(simpleOrder);
	}
	
}