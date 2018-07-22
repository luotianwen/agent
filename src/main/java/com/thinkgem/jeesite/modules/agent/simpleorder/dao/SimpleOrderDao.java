/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 下单管理DAO接口
 * @author 罗天文
 * @version 2018-06-16
 */
@MyBatisDao
public interface SimpleOrderDao extends CrudDao<SimpleOrder> {

    void deliver(SimpleOrder simpleOrder);

    void isaccount(SimpleOrder simpleOrder);

    void account(@Param("ids")String[] ids);

    List<SimpleOrder> getOrderIdDeliver();

    void Tmdeliver(SimpleOrder simpleOrder);

    SimpleOrder sum(SimpleOrder simpleOrder);
}