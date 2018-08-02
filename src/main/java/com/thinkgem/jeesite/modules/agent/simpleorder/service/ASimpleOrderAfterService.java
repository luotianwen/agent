/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.ASimpleOrderAfter;
import com.thinkgem.jeesite.modules.agent.simpleorder.dao.ASimpleOrderAfterDao;

/**
 * 订单售后Service
 * @author 罗天文
 * @version 2018-08-02
 */
@Service
@Transactional(readOnly = true)
public class ASimpleOrderAfterService extends CrudService<ASimpleOrderAfterDao, ASimpleOrderAfter> {

	public ASimpleOrderAfter get(String id) {
		return super.get(id);
	}
	
	public List<ASimpleOrderAfter> findList(ASimpleOrderAfter aSimpleOrderAfter) {
		return super.findList(aSimpleOrderAfter);
	}
	
	public Page<ASimpleOrderAfter> findPage(Page<ASimpleOrderAfter> page, ASimpleOrderAfter aSimpleOrderAfter) {
		return super.findPage(page, aSimpleOrderAfter);
	}
	
	@Transactional(readOnly = false)
	public void save(ASimpleOrderAfter aSimpleOrderAfter) {
		super.save(aSimpleOrderAfter);
	}
	
	@Transactional(readOnly = false)
	public void delete(ASimpleOrderAfter aSimpleOrderAfter) {
		super.delete(aSimpleOrderAfter);
	}
	
}