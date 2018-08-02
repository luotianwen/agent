/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单售后Entity
 * @author 罗天文
 * @version 2018-08-02
 */
public class ASimpleOrderAfter extends DataEntity<ASimpleOrderAfter> {
	
	private static final long serialVersionUID = 1L;
	private String orderid;		// 订单号
	private String articleno;		// 货号
	private String address;		// 地址
	private String consignee;		// 收件人
	private String phone;		// 手机
	private String state;		// 0退货1换货
	private String backaddress;		// 退货地址
	private String backcourier;		// 退货快递公司
	private String backnumber;		// 退货单号
	private String courier;		// 快递公司
	private String delivernumber;		// 快递单号
	private String backmoney;		// 金额
	
	public ASimpleOrderAfter() {
		super();
	}

	public ASimpleOrderAfter(String id){
		super(id);
	}

	@Length(min=0, max=32, message="订单号长度必须介于 0 和 32 之间")
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	@Length(min=0, max=200, message="货号长度必须介于 0 和 200 之间")
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="收件人长度必须介于 0 和 100 之间")
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	@Length(min=0, max=50, message="手机长度必须介于 0 和 50 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1, max=1, message="0退货1换货长度必须介于 1 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=200, message="退货地址长度必须介于 0 和 200 之间")
	public String getBackaddress() {
		return backaddress;
	}

	public void setBackaddress(String backaddress) {
		this.backaddress = backaddress;
	}
	
	@Length(min=0, max=100, message="退货快递公司长度必须介于 0 和 100 之间")
	public String getBackcourier() {
		return backcourier;
	}

	public void setBackcourier(String backcourier) {
		this.backcourier = backcourier;
	}
	
	@Length(min=0, max=32, message="退货单号长度必须介于 0 和 32 之间")
	public String getBacknumber() {
		return backnumber;
	}

	public void setBacknumber(String backnumber) {
		this.backnumber = backnumber;
	}
	
	@Length(min=0, max=100, message="快递公司长度必须介于 0 和 100 之间")
	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}
	
	@Length(min=0, max=32, message="快递单号长度必须介于 0 和 32 之间")
	public String getDelivernumber() {
		return delivernumber;
	}

	public void setDelivernumber(String delivernumber) {
		this.delivernumber = delivernumber;
	}
	
	public String getBackmoney() {
		return backmoney;
	}

	public void setBackmoney(String backmoney) {
		this.backmoney = backmoney;
	}
	
}