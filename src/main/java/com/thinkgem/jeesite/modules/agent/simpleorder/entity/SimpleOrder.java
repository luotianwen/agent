/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;
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
	@ExcelField(title="货号", align=2, sort=1)

	private String articleno;		// 货号
	@ExcelField(title="数量", align=2, sort=2)

	private String num;		// 数量
	@ExcelField(title="金额", align=2, sort=4)

	private Double money;		// 金额
	private Double price;		// 市场价
	@ExcelField(title="状态", align=2, sort=3, dictType="a_simple_order_state")

	private String state;		// 状态
	@ExcelField(title="快递公司", align=2, sort=7)
	private String courier;		// 快递公司
	@ExcelField(title="快递单号", align=2, sort=8)

	private String delivernumber;		// 快递单号
	@ExcelField(title="快递信息", align=2, sort=9)

	private String deliverinfo;		// 快递信息
	@ExcelField(title="快递费", align=2, sort=5)
	private Double delivermoney;		// 快递费
	@ExcelField(title="总价", align=2, sort=6)
	private Double totalmoney;		// 总价
	@ExcelField(title="是否对账", align=2, sort=10, dictType="yes_no")
	private String isaccount;		// 是否对账

	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	@ExcelField(title="创建时间", align=2, sort=11)
	protected Date createDate;	// 创建日期
	@ExcelField(title="发货时间", align=2, sort=12)
	protected Date updateDate;	// 更新日期
	@ExcelField(title="备注", align=2, sort=13)
	protected String remarks;	// 备注

	private String isdelivernumber;

	public String getIsdelivernumber() {
		return isdelivernumber;
	}

	public void setIsdelivernumber(String isdelivernumber) {
		this.isdelivernumber = isdelivernumber;
	}

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