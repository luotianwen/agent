/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.agent.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.springframework.beans.factory.annotation.Required;

/**
 * 代理Entity
 * @author luotianwen
 * @version 2017-10-31
 */
public class Agent extends DataEntity<Agent> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String sex;		// 性别
	private String phone;		// 联系电话
	private String email;		// 邮箱
	private String weixin;		// 微信
	private String mobile;		// 联系手机
	private String loginName;		// 登录名
	private String password;		// 密码
	private String state;		// 状态
	private String discountid;		// 折扣id
	private String apay;		// 支付宝
	private String address;		// 联系地址
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间

	private double discount;		// 折扣
	private String discountName;		// 折扣名称
	private String userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public Agent() {
		super();
	}

	public Agent(String id){
		super(id);
	}
    @Required
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="性别长度必须介于 0 和 2 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Required
	@Length(min=0, max=12, message="联系电话长度必须介于 0 和 12 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Required
	@Length(min=0, max=200, message="邮箱长度必须介于 0 和 200 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Required
	@Length(min=0, max=100, message="微信长度必须介于 0 和 100 之间")
	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	@Required
	@Length(min=0, max=100, message="联系手机长度必须介于 0 和 100 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=32, message="折扣id长度必须介于 0 和 32 之间")
	public String getDiscountid() {
		return discountid;
	}

	public void setDiscountid(String discountid) {
		this.discountid = discountid;
	}
	@Required
	@Length(min=0, max=100, message="支付宝长度必须介于 0 和 100 之间")
	public String getApay() {
		return apay;
	}

	public void setApay(String apay) {
		this.apay = apay;
	}
	@Required
	@Length(min=0, max=200, message="联系地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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