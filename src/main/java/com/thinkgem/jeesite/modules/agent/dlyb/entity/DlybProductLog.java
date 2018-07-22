/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 动力越博商品日志Entity
 * @author 罗天文
 * @version 2018-07-22
 */
public class DlybProductLog extends DataEntity<DlybProductLog> {
	
	private static final long serialVersionUID = 1L;

	private String articleno;		// 商品货号
	private String name;		// 商品名称
	private String brandname;		// 品牌
	private Double marketprice;		// 市场价
	private Double changediscount;		// 折扣变化
	private Double discount;		// 折扣
	private String state;		// 是否增加
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间

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

	public DlybProductLog() {
		super();
	}

	public DlybProductLog(String id){
		super(id);
	}

	@Length(min=0, max=100, message="商品货号长度必须介于 0 和 100 之间")
	@ExcelField(title="商品货号", align=2, sort=1)
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	
	@Length(min=0, max=2000, message="商品名称长度必须介于 0 和 2000 之间")
	@ExcelField(title="商品名称", align=2, sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="品牌长度必须介于 0 和 100 之间")
	@ExcelField(title="商品品牌", align=2, sort=20)
	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	@ExcelField(title="商品价格", align=2, sort=22)
	public Double getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(Double marketprice) {
		this.marketprice = marketprice;
	}
	@ExcelField(title="商品变动折扣", align=2, sort=40)
	public Double getChangediscount() {
		return changediscount;
	}

	public void setChangediscount(Double changediscount) {
		this.changediscount = changediscount;
	}
	@ExcelField(title="商品折扣", align=2, sort=30)
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	@Length(min=0, max=1, message="是否增加长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}