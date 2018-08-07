/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductStockLog;

/**
 * 动力越博库存日志DAO接口
 * @author 罗天文
 * @version 2018-08-07
 */
@MyBatisDao
public interface DlybProductStockLogDao extends CrudDao<DlybProductStockLog> {
	
}