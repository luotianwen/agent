/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.brand.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 品牌Entity
 * @author luotianwen
 * @version 2017-10-25
 */
public class Brand extends DataEntity<Brand> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 品牌名称
	
	public Brand() {
		super();
	}

	public Brand(String id){
		super(id);
	}

	@Length(min=0, max=255, message="品牌名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}