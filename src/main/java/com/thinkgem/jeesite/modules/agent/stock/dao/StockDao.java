/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.stock.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.stock.entity.Stock;
import org.apache.ibatis.annotations.Param;

/**
 * 库存DAO接口
 * @author luotianwen
 * @version 2017-10-29
 */
@MyBatisDao
public interface StockDao extends CrudDao<Stock> {

    Stock getByName(@Param("warehousename") String warehousename, @Param("articleno") String articleno, @Param("size") String size);
}