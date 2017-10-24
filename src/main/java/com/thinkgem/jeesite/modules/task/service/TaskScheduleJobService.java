/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.service;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.quartz.QuartzManager;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.task.dao.TaskScheduleJobDao;
import com.thinkgem.jeesite.modules.task.entity.TaskScheduleJob;
import com.thinkgem.jeesite.modules.task.utils.ScheduleJobUtils;

/**
 * 定时任务Service
 * @author lee
 * @version 2017-08-11
 */
@Service
@Transactional(readOnly = true)
public class TaskScheduleJobService extends CrudService<TaskScheduleJobDao, TaskScheduleJob> {

	private QuartzManager quartzManager;
	
	public TaskScheduleJob get(String id) {
		return super.get(id);
	}
	
	public List<TaskScheduleJob> findList(TaskScheduleJob taskScheduleJob) {
		return super.findList(taskScheduleJob);
	}
	
	public Page<TaskScheduleJob> findPage(Page<TaskScheduleJob> page, TaskScheduleJob taskScheduleJob) {
		return super.findPage(page, taskScheduleJob);
	}
	
	@Transactional(readOnly = false)
	public void save(TaskScheduleJob taskScheduleJob) {
		super.save(taskScheduleJob);
	}
	
	@Transactional(readOnly = false)
	public void delete(TaskScheduleJob taskScheduleJob) {
		TaskScheduleJob scheduleJob = get(taskScheduleJob.getId());
		if (scheduleJob == null) {
			return;
		}
		try {
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		super.delete(taskScheduleJob);
	}
	@Transactional(readOnly = false)
	public void updateCron(String jobId) throws SchedulerException {
		TaskScheduleJob scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (com.thinkgem.jeesite.common.quartz.data.ScheduleJob.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
			QuartzManager quartzManager = new QuartzManager();
			quartzManager.updateJobCron(ScheduleJobUtils.entityToData(scheduleJob));
		}
		 super.save(scheduleJob);
	}

	@Transactional(readOnly = false)
	public void changeStatus(String jobId, String cmd) throws SchedulerException {
		TaskScheduleJob scheduleJob = get(jobId);
		if (scheduleJob == null) {
			return;
		}
		if ("stop".equals(cmd)) {
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			scheduleJob.setJobStatus(com.thinkgem.jeesite.common.quartz.data.ScheduleJob.STATUS_NOT_RUNNING);
		} else if ("start".equals(cmd)) {
			scheduleJob.setJobStatus(com.thinkgem.jeesite.common.quartz.data.ScheduleJob.STATUS_RUNNING);
			quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
		}
		 super.save(scheduleJob);
	}

	@Transactional(readOnly = false)
	public void deleteById(TaskScheduleJob id) {
		try {
			TaskScheduleJob scheduleJob = get(id);
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		super.delete(id);
	}

	@Transactional(readOnly = false)
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		TaskScheduleJob taskScheduleJob = new TaskScheduleJob();
		taskScheduleJob.setJobStatus("1");
		List<TaskScheduleJob> jobList = findList(taskScheduleJob);
		quartzManager = new QuartzManager();
		for (TaskScheduleJob scheduleJob : jobList) {
			quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
		}
	}
}