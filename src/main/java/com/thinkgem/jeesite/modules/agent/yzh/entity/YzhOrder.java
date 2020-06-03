/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.yzh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 云中鹤订单管理Entity
 * @author 云中鹤订单管理
 * @version 2020-06-02
 */
public class YzhOrder extends DataEntity<YzhOrder> {
	
	private static final long serialVersionUID = 1L;
	private String receivername;		// 收货人姓名
	private String productid;		// 售后产品
	private String thirdorder;		// 第三方订单号
	private double orderProductPrice;		// 产品价格
	private String orderKey;		// 系统订单号
	private double orderShipmentFee;		// 快递费用
	private double orderTotalPrice;		// 订单总金额
	private String isaccount;		// 是否对账
	private String pidNums;		// 商品ID_数量
	private String province;		// 省
	private String city;		// 市
	private String county;		// 区/县
	private String town;		// 乡
	private String address;		// 收货人详细地址
	private String mobile;		// 收货人手机
	private String remark;		// 订单备注信息
	private int amount;		// 售后数量
	private String reason;		// 售后退换货原因
	private String userremark;		// 售后用户备注
	private String serviceOrder;		// 售后id
	private String status;		// 售后状态
	private String type;		// 售后类型, niffer:换货, returned: 退货
	private String shipmentName;
	private String	shipmentOrder;
	private String returnedAddress;
	private String receiverName2;
	private String receiverMobile;
	private String newOrder;
	private String receiverStatus;

	public String getReturnedAddress() {
		return returnedAddress;
	}

	public void setReturnedAddress(String returnedAddress) {
		this.returnedAddress = returnedAddress;
	}

	public String getReceiverName2() {
		return receiverName2;
	}

	public void setReceiverName2(String receiverName) {
		this.receiverName2 = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(String newOrder) {
		this.newOrder = newOrder;
	}

	public String getReceiverStatus() {
		return receiverStatus;
	}

	public void setReceiverStatus(String receiverStatus) {
		this.receiverStatus = receiverStatus;
	}

	public String getShipmentName() {
		return shipmentName;
	}

	public void setShipmentName(String shipmentName) {
		this.shipmentName = shipmentName;
	}

	public String getShipmentOrder() {
		return shipmentOrder;
	}

	public void setShipmentOrder(String shipmentOrder) {
		this.shipmentOrder = shipmentOrder;
	}

	public YzhOrder() {
		super();
	}

	public YzhOrder(String id){
		super(id);
	}

	@Length(min=0, max=255, message="收货人姓名长度必须介于 0 和 255 之间")
	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	
	@Length(min=0, max=11, message="售后产品长度必须介于 0 和 11 之间")
	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}
	
	@Length(min=0, max=255, message="第三方订单号长度必须介于 0 和 255 之间")
	public String getThirdorder() {
		return thirdorder;
	}

	public void setThirdorder(String thirdorder) {
		this.thirdorder = thirdorder;
	}
	
	public double getOrderProductPrice() {
		return orderProductPrice;
	}

	public void setOrderProductPrice(double orderProductPrice) {
		this.orderProductPrice = orderProductPrice;
	}
	
	@Length(min=0, max=200, message="系统订单号长度必须介于 0 和 200 之间")
	public String getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}
	
	public double getOrderShipmentFee() {
		return orderShipmentFee;
	}

	public void setOrderShipmentFee(double orderShipmentFee) {
		this.orderShipmentFee = orderShipmentFee;
	}
	
	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	
	@Length(min=0, max=1, message="是否对账长度必须介于 0 和 1 之间")
	public String getIsaccount() {
		return isaccount;
	}

	public void setIsaccount(String isaccount) {
		this.isaccount = isaccount;
	}
	
	@Length(min=0, max=255, message="商品ID_数量长度必须介于 0 和 255 之间")
	public String getPidNums() {
		return pidNums;
	}

	public void setPidNums(String pidNums) {
		this.pidNums = pidNums;
	}
	
	@Length(min=0, max=255, message="省长度必须介于 0 和 255 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=255, message="市长度必须介于 0 和 255 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=255, message="区/县长度必须介于 0 和 255 之间")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	@Length(min=0, max=255, message="乡长度必须介于 0 和 255 之间")
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
	
	@Length(min=0, max=255, message="收货人详细地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=255, message="收货人手机长度必须介于 0 和 255 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=255, message="订单备注信息长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=255, message="售后退换货原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=255, message="售后用户备注长度必须介于 0 和 255 之间")
	public String getUserremark() {
		return userremark;
	}

	public void setUserremark(String userremark) {
		this.userremark = userremark;
	}
	
	@Length(min=0, max=255, message="售后id长度必须介于 0 和 255 之间")
	public String getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(String serviceOrder) {
		this.serviceOrder = serviceOrder;
	}
	
	@Length(min=0, max=255, message="售后状态长度必须介于 0 和 255 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="售后类型, niffer:换货, returned: 退货长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}