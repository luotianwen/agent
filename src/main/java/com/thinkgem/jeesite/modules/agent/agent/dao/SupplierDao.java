/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.agent.entity.Supplier;

/**
 * 供应商管理DAO接口
 * @author martins
 * @version 2019-11-14
 */
@MyBatisDao
public interface SupplierDao extends CrudDao<Supplier> {
	
}