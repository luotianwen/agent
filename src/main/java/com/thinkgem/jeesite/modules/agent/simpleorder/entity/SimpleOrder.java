/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 下单管理Entity
 * @author 罗天文
 * @version 2018-06-16
 */
public class SimpleOrder extends DataEntity<SimpleOrder> {
	
	private static final long serialVersionUID = 1L;
	private String agentid;		// 代理
	private String agentName;		// 代理

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	private String articleno;		// 货号
	private String num;		// 数量
	private Double money;		// 金额
	private Double price;		// 市场价
	private String state;		// 状态
	private String courier;		// 快递公司
	private String delivernumber;		// 快递单号
	private String deliverinfo;		// 快递信息
	private Double delivermoney;		// 快递费
	private Double totalmoney;		// 总价
	private String isaccount;		// 是否对账
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public SimpleOrder() {
		super();
	}

	public SimpleOrder(String id){
		super(id);
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}
	
	public String getDelivernumber() {
		return delivernumber;
	}

	public void setDelivernumber(String delivernumber) {
		this.delivernumber = delivernumber;
	}
	
	public String getDeliverinfo() {
		return deliverinfo;
	}

	public void setDeliverinfo(String deliverinfo) {
		this.deliverinfo = deliverinfo;
	}
	
	public Double getDelivermoney() {
		return delivermoney;
	}

	public void setDelivermoney(Double delivermoney) {
		this.delivermoney = delivermoney;
	}
	
	public Double getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}
	
	public String getIsaccount() {
		return isaccount;
	}

	public void setIsaccount(String isaccount) {
		this.isaccount = isaccount;
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