/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.brand.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 品牌Entity
 * @author luotianwen
 * @version 2017-10-29
 */
public class Brand extends DataEntity<Brand> {
	
	private static final long serialVersionUID = 1L;
	private String warehousename;		// 品牌名称
	private String pickingnum;		// 起配件数
	private String pickingdate;		// 配货时间
	private String brands;		// 经营品牌
	private String pickingrate;		// 配货率
	private int state;		// 是否更新
	private String udate;		// 更新时间

	public Brand() {
		super();
	}

	public Brand(String id){
		super(id);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUdate() {
		return udate;
	}

	public void setUdate(String udate) {
		this.udate = udate;
	}

	@Length(min=0, max=255, message="品牌名称长度必须介于 0 和 255 之间")
	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	
	@Length(min=0, max=20, message="起配件数长度必须介于 0 和 20 之间")
	public String getPickingnum() {
		return pickingnum;
	}

	public void setPickingnum(String pickingnum) {
		this.pickingnum = pickingnum;
	}
	
	@Length(min=0, max=100, message="配货时间长度必须介于 0 和 100 之间")
	public String getPickingdate() {
		return pickingdate;
	}

	public void setPickingdate(String pickingdate) {
		this.pickingdate = pickingdate;
	}
	
	@Length(min=0, max=100, message="经营品牌长度必须介于 0 和 100 之间")
	public String getBrands() {
		return brands;
	}

	public void setBrands(String brands) {
		this.brands = brands;
	}
	
	@Length(min=0, max=100, message="配货率长度必须介于 0 和 100 之间")
	public String getPickingrate() {
		return pickingrate;
	}

	public void setPickingrate(String pickingrate) {
		this.pickingrate = pickingrate;
	}
	
}