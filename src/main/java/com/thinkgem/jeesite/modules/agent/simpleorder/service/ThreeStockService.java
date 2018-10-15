/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.ThreeStock;
import com.thinkgem.jeesite.modules.agent.simpleorder.dao.ThreeStockDao;

/**
 * 仓库管理Service
 * @author 罗天文
 * @version 2018-10-14
 */
@Service
@Transactional(readOnly = true)
public class ThreeStockService extends CrudService<ThreeStockDao, ThreeStock> {

	public ThreeStock get(String id) {
		return super.get(id);
	}
	
	public List<ThreeStock> findList(ThreeStock threeStock) {
		return super.findList(threeStock);
	}
	
	public Page<ThreeStock> findPage(Page<ThreeStock> page, ThreeStock threeStock) {
		return super.findPage(page, threeStock);
	}
	
	@Transactional(readOnly = false)
	public void save(ThreeStock threeStock) {
		super.save(threeStock);
	}
	
	@Transactional(readOnly = false)
	public void delete(ThreeStock threeStock) {
		super.delete(threeStock);
	}
	
}