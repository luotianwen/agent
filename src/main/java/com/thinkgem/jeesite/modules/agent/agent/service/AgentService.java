/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.SmsDemo;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.dao.AgentDao;

/**
 * 代理Service
 * @author luotianwen
 * @version 2017-10-31
 */
@Service
@Transactional(readOnly = true)
public class AgentService extends CrudService<AgentDao, Agent> {
	@Autowired
	private SystemService systemService;
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

		if("1".equals(agent.getState())&& StringUtils.isEmpty(agent.getUserid())){
			saveUser(agent);
		}
		super.save(agent);
	}
	private void saveUser(Agent agent){
		String phone=agent.getPhone();
		User user=new User();
		user.setPassword(SystemService.entryptPassword(phone.substring(phone.length()-6,phone.length())));
		user.setLoginName(agent.getLoginName());
		user.setCompany(new Office("1"));
		user.setOffice(new Office("2"));
		user.setName(agent.getName());
		List<Role> roleList = Lists.newArrayList();
		Role r=new Role();
		r.setId("53a18b73a2934535b20de15a3e9e8e17");
		roleList.add(r);
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		agent.setUserid(user.getId());
		JSONObject j=new JSONObject();
		j.put("name",agent.getName());
		j.put("account",agent.getLoginName());
		try {
			SmsDemo.sendSms(phone,j.toString(),"SMS_109505038");
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
	@Transactional(readOnly = false)
	public void delete(Agent agent) {
		super.delete(agent);
	}

	public Agent getUserId(String id) {
		return  dao.getUserId(id);
	}
}