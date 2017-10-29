/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 代理订单Entity
 * @author luotianwen
 * @version 2017-10-29
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String agentid;		// 代理
	private String money;		// 金额
	private String num;		// 数量
	private String state;		// 状态
	private String discountid;		// 折扣id
	private String discountmoney;		// 折扣后价
	private String delivernumber;		// 快递单号
	private String courier;		// 快递公司
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private List<OrderDetail> orderDetailList = Lists.newArrayList();		// 子表列表
	
	public Order() {
		super();
	}

	public Order(String id){
		super(id);
	}

	@Length(min=0, max=32, message="代理长度必须介于 0 和 32 之间")
	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=1, max=4, message="状态长度必须介于 1 和 4 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=1, max=32, message="折扣id长度必须介于 1 和 32 之间")
	public String getDiscountid() {
		return discountid;
	}

	public void setDiscountid(String discountid) {
		this.discountid = discountid;
	}
	
	public String getDiscountmoney() {
		return discountmoney;
	}

	public void setDiscountmoney(String discountmoney) {
		this.discountmoney = discountmoney;
	}
	
	@Length(min=0, max=32, message="快递单号长度必须介于 0 和 32 之间")
	public String getDelivernumber() {
		return delivernumber;
	}

	public void setDelivernumber(String delivernumber) {
		this.delivernumber = delivernumber;
	}
	
	@Length(min=0, max=100, message="快递公司长度必须介于 0 和 100 之间")
	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
}