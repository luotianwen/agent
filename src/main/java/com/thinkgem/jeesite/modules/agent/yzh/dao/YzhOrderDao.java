/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.yzh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.agent.yzh.entity.YzhOrder;

/**
 * 云中鹤订单管理DAO接口
 * @author 云中鹤订单管理
 * @version 2020-06-02
 */
@MyBatisDao
public interface YzhOrderDao extends CrudDao<YzhOrder> {

    void updatekt(YzhOrder yzhOrder);

    void updateafter(YzhOrder yzhOrder);

    void returnedAddress(YzhOrder yzhOrder);
}