/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 仓库管理Entity
 * @author 罗天文
 * @version 2018-10-14
 */
public class ThreeStock extends DataEntity<ThreeStock> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 联系人
	private String phone;		// 电话
	private String weixin;		// 微信
	
	public ThreeStock() {
		super();
	}

	public ThreeStock(String id){
		super(id);
	}

	@Length(min=0, max=100, message="联系人长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="电话长度必须介于 0 和 10 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="微信长度必须介于 0 和 100 之间")
	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	
}