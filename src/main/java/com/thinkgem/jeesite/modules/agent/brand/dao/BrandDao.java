/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.brand.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.brand.entity.Brand;

/**
 * 品牌DAO接口
 * @author luotianwen
 * @version 2017-10-29
 */
@MyBatisDao
public interface BrandDao extends CrudDao<Brand> {

    Brand getByName(String warehousename);

    void updateState(Brand brand);
}