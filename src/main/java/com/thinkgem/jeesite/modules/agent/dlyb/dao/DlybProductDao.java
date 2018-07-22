/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProduct;

import java.util.List;

/**
 * 动力越博商品DAO接口
 * @author 罗天文
 * @version 2018-07-22
 */
@MyBatisDao
public interface DlybProductDao extends CrudDao<DlybProduct> {
  public void deleteALL();

  List<DlybProduct> getAll();

    DlybProduct getByNo(DlybProduct d);
}