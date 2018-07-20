/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.service;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.agent.simpleorder.dao.SimpleOrderDao;
import sun.management.resources.agent;

/**
 * 下单管理Service
 * @author 罗天文
 * @version 2018-06-16
 */
@Service
@Transactional(readOnly = true)
public class SimpleOrderService extends CrudService<SimpleOrderDao, SimpleOrder> {
	@Autowired
	private AgentService agentService;
	public SimpleOrder get(String id) {
		return super.get(id);
	}
	
	public List<SimpleOrder> findList(SimpleOrder simpleOrder) {
		return super.findList(simpleOrder);
	}
	
	public Page<SimpleOrder> findPage(Page<SimpleOrder> page, SimpleOrder simpleOrder) {
		return super.findPage(page, simpleOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(SimpleOrder simpleOrder){

		super.save(simpleOrder);

	}
	
	@Transactional(readOnly = false)
	public void delete(SimpleOrder simpleOrder) {
		Agent agent=new Agent();
		agent.setId(simpleOrder.getAgentid());
		agent.setMoney(simpleOrder.getMoney());
		agentService.addMoney(agent);
		super.delete(simpleOrder);
	}
	@Transactional(readOnly = false)
	public void deliver(SimpleOrder simpleOrder) throws Exception {
		if(simpleOrder.getState().equals("3")){
			double moneys=simpleOrder.getTotalmoney()-simpleOrder.getMoney();
			Agent agent=new Agent();
			agent.setId(simpleOrder.getAgentid());
			agent.setMoney(moneys);
			Double money=agentService.get(agent).getMoney();
			if(money>=moneys) {
				agentService.reduceMoney(agent);
			}
			else{
				throw new Exception("金额不够");
			}
		}
		simpleOrder.preUpdate();
		 dao.deliver(simpleOrder);
	}
   @Transactional(readOnly = false)
	public void asave(SimpleOrder simpleOrder) throws Exception {
		double f=0d;
		if(StringUtils.isEmpty(simpleOrder.getId())){
			f=simpleOrder.getMoney();
		}else{
			SimpleOrder a=this.get(simpleOrder.getId());
			f=simpleOrder.getMoney()-a.getMoney();
		}
		Agent agent=new Agent();
		agent.setId(simpleOrder.getAgentid());
		agent.setMoney(f);
		Double money=agentService.get(agent).getMoney();
		if(money>=f) {
			agentService.reduceMoney(agent);
		}
		else{
			throw new Exception("金额不够");
		}
		super.save(simpleOrder);
	}
	@Transactional(readOnly = false)
	public void isaccount(SimpleOrder simpleOrder) {
		dao.isaccount(simpleOrder);
	}
    @Transactional(readOnly = false)
    public void account(String ids) {

            dao.account(ids.split(","));

    }
}