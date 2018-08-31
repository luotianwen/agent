/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单售后Entity
 * @author 罗天文
 * @version 2018-08-03
 */
public class SimpleOrderAfter extends DataEntity<SimpleOrderAfter> {
	
	private static final long serialVersionUID = 1L;
	@ExcelField(title="订单号", align=1, sort=1)
	private String orderId;		// 订单号
	@ExcelField(title="货号", align=1, sort=2)
	private String articleno;		// 货号
	@ExcelField(title="地址", align=1, sort=3)
	private String address;		// 地址
	@ExcelField(title="收件人", align=1, sort=4)
	private String consignee;		// 收件人
	@ExcelField(title="手机", align=1, sort=5)
	private String phone;		// 手机
	@ExcelField(title="售后方式", align=1, sort=6, dictType="a_after_order_state")
	private String state;		// 售后方式
	@ExcelField(title="退货地址", align=1, sort=7)
	private String backaddress;		// 退货地址
	@ExcelField(title="退货快递公司", align=1, sort=8)
	private String backcourier;		// 退货快递公司
	@ExcelField(title="退货单号", align=1, sort=10)
	private String backnumber;		// 退货单号
	@ExcelField(title="快递公司", align=1, sort=11)
	private String courier;		// 快递公司
	@ExcelField(title="快递单号", align=1, sort=12)
	private String delivernumber;		// 快递单号
	@ExcelField(title="金额", align=1, sort=9)
	private String backmoney;		// 金额
	@ExcelField(title="原因", align=1, sort=13)
	protected String remarks;	// 备注
	private String agent;
	@ExcelField(title="序号", align=1, sort=0)
	private String no;
	private String afterstate;
	private String backstate;
    private String changestate;

    public String getChangestate() {
        return changestate;
    }

    public void setChangestate(String changestate) {
        this.changestate = changestate;
    }

    public String getBackstate() {
		return backstate;
	}

	public void setBackstate(String backstate) {
		this.backstate = backstate;
	}

	public String getAfterstate() {
		return afterstate;
	}

	public void setAfterstate(String afterstate) {
		this.afterstate = afterstate;
	}

	public String getNo() {
		return no;
	}public void setNo(String no) {
		this.no = no;
	}
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public SimpleOrderAfter() {
		super();
	}

	public SimpleOrderAfter(String id){
		super(id);
	}

	@Length(min=0, max=32, message="订单号长度必须介于 0 和 32 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	
	@Length(min=1, max=1, message="售后方式长度必须介于 1 和 1 之间")
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