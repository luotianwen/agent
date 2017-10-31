/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;

/**
 * 代理DAO接口
 * @author luotianwen
 * @version 2017-10-31
 */
@MyBatisDao
public interface AgentDao extends CrudDao<Agent> {
	
}