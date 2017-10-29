/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.order.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 代理订单Entity
 * @author luotianwen
 * @version 2017-10-29
 */
public class OrderDetail extends DataEntity<OrderDetail> {
	
	private static final long serialVersionUID = 1L;
	private Order order;		// 订单id 父类
	private String stockid;		// 库存id
	private String price;		// 单价
	private String num;		// 数量
	private String money;		// 金额
	
	public OrderDetail() {
		super();
	}

	public OrderDetail(String id){
		super(id);
	}

	public OrderDetail(Order order){
		this.order = order;
	}

	@Length(min=1, max=32, message="订单id长度必须介于 1 和 32 之间")
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Length(min=1, max=32, message="库存id长度必须介于 1 和 32 之间")
	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=1, max=11, message="数量长度必须介于 1 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}