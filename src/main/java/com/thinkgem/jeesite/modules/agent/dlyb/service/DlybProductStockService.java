/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductStock;
import com.thinkgem.jeesite.modules.agent.dlyb.dao.DlybProductStockDao;

/**
 * 动力越博库存Service
 * @author 罗天文
 * @version 2018-08-07
 */
@Service
@Transactional(readOnly = true)
public class DlybProductStockService extends CrudService<DlybProductStockDao, DlybProductStock> {

	public DlybProductStock get(String id) {
		return super.get(id);
	}
	
	public List<DlybProductStock> findList(DlybProductStock dlybProductStock) {
		return super.findList(dlybProductStock);
	}
	
	public Page<DlybProductStock> findPage(Page<DlybProductStock> page, DlybProductStock dlybProductStock) {
		return super.findPage(page, dlybProductStock);
	}
	
	@Transactional(readOnly = false)
	public void save(DlybProductStock dlybProductStock) {
		super.save(dlybProductStock);
	}
	
	@Transactional(readOnly = false)
	public void delete(DlybProductStock dlybProductStock) {
		super.delete(dlybProductStock);
	}

    public DlybProductStock getByNo(DlybProductStock dlybProductStock) {
		return dao.getByNo(dlybProductStock);
    }
}