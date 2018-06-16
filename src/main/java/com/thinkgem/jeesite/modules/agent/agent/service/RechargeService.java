/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.service;

import java.util.List;

import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.agent.entity.Recharge;
import com.thinkgem.jeesite.modules.agent.agent.dao.RechargeDao;

/**
 * 充值Service
 * @author 罗天文
 * @version 2018-06-16
 */
@Service
@Transactional(readOnly = true)
public class RechargeService extends CrudService<RechargeDao, Recharge> {
	@Autowired
	private AgentService agentService;
	public Recharge get(String id) {
		return super.get(id);
	}
	
	public List<Recharge> findList(Recharge recharge) {
		return super.findList(recharge);
	}
	
	public Page<Recharge> findPage(Page<Recharge> page, Recharge recharge) {
		return super.findPage(page, recharge);
	}
	
	@Transactional(readOnly = false)
	public void save(Recharge recharge) {
		Agent agent=new Agent();
		agent.setId(recharge.getAgentid());
		agent.setMoney(recharge.getMoney());
		agentService.addMoney(agent);
		super.save(recharge);
	}
	
	@Transactional(readOnly = false)
	public void delete(Recharge recharge) {
		super.delete(recharge);
	}
	
}