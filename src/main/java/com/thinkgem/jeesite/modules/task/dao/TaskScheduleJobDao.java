/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.task.entity.TaskScheduleJob;

/**
 * 定时任务DAO接口
 * @author lee
 * @version 2017-08-11
 */
@MyBatisDao
public interface TaskScheduleJobDao extends CrudDao<TaskScheduleJob> {
	
}