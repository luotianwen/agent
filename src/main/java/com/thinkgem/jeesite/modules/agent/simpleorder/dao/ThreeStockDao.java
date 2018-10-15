/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.ThreeStock;

/**
 * 仓库管理DAO接口
 * @author 罗天文
 * @version 2018-10-14
 */
@MyBatisDao
public interface ThreeStockDao extends CrudDao<ThreeStock> {
	
}