package com.thinkgem.jeesite.modules.task.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.task.service.TaskScheduleJobService;

public class ScheduleJobInitListener implements ApplicationListener<ContextRefreshedEvent> {

	protected TaskScheduleJobService scheduleJobService = SpringContextHolder.getApplicationContext()
			.getBean(TaskScheduleJobService.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			scheduleJobService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}