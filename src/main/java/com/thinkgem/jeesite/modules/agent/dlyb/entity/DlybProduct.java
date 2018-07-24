/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 动力越博商品Entity
 * @author 罗天文
 * @version 2018-07-22
 */
public class DlybProduct extends DataEntity<DlybProduct> {
	
	private static final long serialVersionUID = 1L;
	private String articleno;		// 商品货号
	private String name;		// 名称
	private String brandname;		// 品牌
	private Double marketprice;		// 市场价
	private Double discount;		// 折扣
	
	public DlybProduct() {
		super();
	}

	public DlybProduct(String id){
		super(id);
	}

	@Length(min=0, max=100, message="商品货号长度必须介于 0 和 100 之间")
	@ExcelField(title="商品货号", align=1, sort=1 )
	public String getArticleno() {
		return articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	
	@Length(min=0, max=2000, message="名称长度必须介于 0 和 2000 之间")
	@ExcelField(title="商品名称", align=1, sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="品牌长度必须介于 0 和 100 之间")
	@ExcelField(title="商品品牌", align=1, sort=20)
	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	@ExcelField(title="市场价", align=1, sort=30,type=1)
	public Double getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(Double marketprice) {
		this.marketprice = marketprice;
	}
	@ExcelField(title="折扣", align=1, sort=40,type=1)
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
}