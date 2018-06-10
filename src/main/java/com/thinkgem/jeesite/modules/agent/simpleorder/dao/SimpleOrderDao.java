/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;

/**
 * 下单管理DAO接口
 * @author 罗天文
 * @version 2018-06-10
 */
@MyBatisDao
public interface SimpleOrderDao extends CrudDao<SimpleOrder> {
	
}