/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.order.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.order.entity.Order;

/**
 * 代理订单DAO接口
 * @author luotianwen
 * @version 2017-11-03
 */
@MyBatisDao
public interface OrderDao extends CrudDao<Order> {
	
}