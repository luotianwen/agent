package com.thinkgem.jeesite.modules.task.utils;

import com.thinkgem.jeesite.modules.task.entity.TaskScheduleJob;

public class ScheduleJobUtils {
     public static com.thinkgem.jeesite.common.quartz.data.ScheduleJob entityToData(TaskScheduleJob scheduleJobEntity){
    	 
    	 com.thinkgem.jeesite.common.quartz.data.ScheduleJob scheduleJob=new com.thinkgem.jeesite.common.quartz.data.ScheduleJob();
    	 scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
    	 scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
    	 scheduleJob.setDescription(scheduleJobEntity.getDescription());
    	 scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
    	 scheduleJob.setJobName(scheduleJobEntity.getJobName());
    	 scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
    	 scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
    	 scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
    	 scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
    	 return scheduleJob;
     }
}
