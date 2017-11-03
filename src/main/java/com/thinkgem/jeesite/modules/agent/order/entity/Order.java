/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotNull;

/**
 * 代理订单Entity
 * @author luotianwen
 * @version 2017-11-03
 */
public class Order extends DataEntity<Order> {
	
	private static final long serialVersionUID = 1L;
	private String agentid;		// 代理
	private String agentName;
	private int money;		// 金额
	private int num;		// 数量
	private String state;		// 状态
	private double discount;		// 折扣
	private int discountmoney;		// 折扣后价
	private String delivernumber;		// 快递单号
	private String courier;		// 快递公司
	private String articleno;		// 货号
	private String size;		// 尺码
	private String sex;		// 性别
	private String paystate;		// 支付状态
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
    private  String onumber;

	public String getOnumber() {
		return onumber;
	}

	public void setOnumber(String onumber) {
		this.onumber = onumber;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	@NotNull
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	@Length(min=1, max=4, message="状态长度必须介于 1 和 4 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public int getDiscountmoney() {
		return discountmoney;
	}

	public void setDiscountmoney(int discountmoney) {
		this.discountmoney = discountmoney;
	}
	
	@Length(min=0, max=32, message="快递单号长度必须介于 0 和 32 之间")
	public String getDelivernumber() {
		return delivernumber;
	}

	public void setDelivernumber(String delivernumber) {
		this.delivernumber = delivernumber;
	}
	@NotNull
	@Length(min=0, max=100, message="快递公司长度必须介于 0 和 100 之间")
	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}
	@NotNull
	@Length(min=0, max=50, message="货号长度必须介于 0 和 50 之间")
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	@NotNull
	@Length(min=0, max=10, message="尺码长度必须介于 0 和 10 之间")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	@NotNull
	@Length(min=0, max=10, message="性别长度必须介于 0 和 10 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=1, message="支付状态长度必须介于 0 和 1 之间")
	public String getPaystate() {
		return paystate;
	}

	public void setPaystate(String paystate) {
		this.paystate = paystate;
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
		
}