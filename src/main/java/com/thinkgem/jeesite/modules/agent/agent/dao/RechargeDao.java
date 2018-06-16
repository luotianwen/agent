/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.agent.entity.Recharge;

/**
 * 充值DAO接口
 * @author 罗天文
 * @version 2018-06-16
 */
@MyBatisDao
public interface RechargeDao extends CrudDao<Recharge> {
	
}