/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrderAfter;

/**
 * 订单售后DAO接口
 * @author 罗天文
 * @version 2018-08-03
 */
@MyBatisDao
public interface SimpleOrderAfterDao extends CrudDao<SimpleOrderAfter> {

    void backaddress(SimpleOrderAfter simpleOrderAfter);

    void backcourier(SimpleOrderAfter simpleOrderAfter);

    void courier(SimpleOrderAfter simpleOrderAfter);
}