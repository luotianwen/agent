/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductStockLog;
import com.thinkgem.jeesite.modules.agent.dlyb.dao.DlybProductStockLogDao;

/**
 * 动力越博库存日志Service
 * @author 罗天文
 * @version 2018-08-07
 */
@Service
@Transactional(readOnly = true)
public class DlybProductStockLogService extends CrudService<DlybProductStockLogDao, DlybProductStockLog> {

	public DlybProductStockLog get(String id) {
		return super.get(id);
	}
	
	public List<DlybProductStockLog> findList(DlybProductStockLog dlybProductStockLog) {
		return super.findList(dlybProductStockLog);
	}
	
	public Page<DlybProductStockLog> findPage(Page<DlybProductStockLog> page, DlybProductStockLog dlybProductStockLog) {
		return super.findPage(page, dlybProductStockLog);
	}
	
	@Transactional(readOnly = false)
	public void save(DlybProductStockLog dlybProductStockLog) {
		super.save(dlybProductStockLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(DlybProductStockLog dlybProductStockLog) {
		super.delete(dlybProductStockLog);
	}
	
}