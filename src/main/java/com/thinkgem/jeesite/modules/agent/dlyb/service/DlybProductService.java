/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProduct;
import com.thinkgem.jeesite.modules.agent.dlyb.dao.DlybProductDao;

/**
 * 动力越博商品Service
 * @author 罗天文
 * @version 2018-07-22
 */
@Service
@Transactional(readOnly = true)
public class DlybProductService extends CrudService<DlybProductDao, DlybProduct> {

	public DlybProduct get(String id) {
		return super.get(id);
	}
	
	public List<DlybProduct> findList(DlybProduct dlybProduct) {
		return super.findList(dlybProduct);
	}
	
	public Page<DlybProduct> findPage(Page<DlybProduct> page, DlybProduct dlybProduct) {
		return super.findPage(page, dlybProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(DlybProduct dlybProduct) {
		super.save(dlybProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(DlybProduct dlybProduct) {
		super.delete(dlybProduct);
	}
	@Transactional(readOnly = false)
    public   void deleteALL() {
		 dao.deleteALL();
    }

	public List<DlybProduct> getAll() {
		return dao.findAllList(new DlybProduct());
	}

	public DlybProduct getByNo(String articleno) {
		DlybProduct d=new DlybProduct();
		d.setArticleno(articleno);
		return dao.getByNo(d);
	}
}