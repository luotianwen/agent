/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.discount.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.discount.entity.Discount;

/**
 * 折扣设置DAO接口
 * @author luotianwen
 * @version 2017-10-24
 */
@MyBatisDao
public interface DiscountDao extends CrudDao<Discount> {
	
}