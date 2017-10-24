/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.dao.AgentDao;

/**
 * 代理设置Service
 * @author luotianwen
 * @version 2017-10-24
 */
@Service
@Transactional(readOnly = true)
public class AgentService extends CrudService<AgentDao, Agent> {

	public Agent get(String id) {
		return super.get(id);
	}
	
	public List<Agent> findList(Agent agent) {
		return super.findList(agent);
	}
	
	public Page<Agent> findPage(Page<Agent> page, Agent agent) {
		return super.findPage(page, agent);
	}
	
	@Transactional(readOnly = false)
	public void save(Agent agent) {
		super.save(agent);
	}
	
	@Transactional(readOnly = false)
	public void delete(Agent agent) {
		super.delete(agent);
	}
	
}