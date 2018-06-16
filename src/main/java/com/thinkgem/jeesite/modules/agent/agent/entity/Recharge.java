/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 充值Entity
 * @author 罗天文
 * @version 2018-06-16
 */
public class Recharge extends DataEntity<Recharge> {
	
	private static final long serialVersionUID = 1L;
	private String agentid;		// 代理
	private String agentName;		// 代理

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	private Double money;		// 充值金额
	
	public Recharge() {
		super();
	}

	public Recharge(String id){
		super(id);
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
}