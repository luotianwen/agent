/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.agent.agent.entity.Supplier;
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
	private Supplier supplier;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	@ExcelField(title="货号", align=1, sort=10)

	private String articleno;		// 货号
	@ExcelField(title="数量", align=1, sort=40)

	private int num;		// 数量
	@ExcelField(title="金额", align=1, sort=44)
	private Double money;		// 金额
	private Double price;		// 市场价
	@ExcelField(title="状态", align=1, sort=43, dictType="a_simple_order_state")

	private String state;		// 状态
	@ExcelField(title="快递公司", align=1, sort=67 )
	private String courier;		// 快递公司
	@ExcelField(title="快递单号", align=1, sort=68)

	private String delivernumber;		// 快递单号
	@ExcelField(title="快递信息", align=1, sort=165)
	private String deliverinfo;		// 快递信息
	@ExcelField(title="快递费", align=1, sort=65)
	private Double delivermoney;		// 快递费
	@ExcelField(title="总价", align=1, sort=66)
	private Double totalmoney;		// 总价
	@ExcelField(title="是否对账", align=1, sort=90, dictType="yes_no")
	private String isaccount;		// 是否对账
	@ExcelField(title="收件人", align=1, sort=69)
	private String consignee;		// 收件人
	@ExcelField(title="手机", align=1, sort=79)
	private String phone;		// 手机
	@ExcelField(title="颜色", align=1, sort=20)
	private String colour;		// 颜色
	@ExcelField(title="规格尺码", align=1, sort=30)
	private String spec;		// 规格尺码
	@ExcelField(title="地址", align=1, sort=89)
	private String address;		// 地址
	private String three;		// 三方
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	@ExcelField(title="创建时间", align=1, sort=110)
	protected Date createDate;	// 创建日期
	@ExcelField(title="发货时间", align=1, sort=120)
	protected Date updateDate;	// 更新日期
	@ExcelField(title="备注", align=1, sort=130)
	protected String remarks;	// 备注
	@ExcelField(title="订单号", align=1, sort=0)
	private String orderId;
	//@ExcelField(title="序号", align=1, sort=0)
	private String no;
	@ExcelField(title="类别", align=1, sort=140, dictType="a_simple_order_type")
	protected String type;	// 类别


	public String getNo() {
		return no;
	}public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String warehouse;//仓库
	private String tmarticleno;//货号
	private String tmspec;//规格
	private String tmstate;//状态
	private String afterstate;

	public String getThree() {
		return three;
	}

	public void setThree(String three) {
		this.three = three;
	}

	public String getAfterstate() {
		return afterstate;
	}

	public void setAfterstate(String afterstate) {
		this.afterstate = afterstate;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getTmarticleno() {
		return tmarticleno;
	}

	public void setTmarticleno(String tmarticleno) {
		this.tmarticleno = tmarticleno;
	}

	public String getTmspec() {
		return tmspec;
	}

	public void setTmspec(String tmspec) {
		this.tmspec = tmspec;
	}

	public String getTmstate() {
		return tmstate;
	}

	public void setTmstate(String tmstate) {
		this.tmstate = tmstate;
	}

	private String tradeId;//交易号

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
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