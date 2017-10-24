/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 定时任务Entity
 * @author lee
 * @version 2017-08-11
 */
public class TaskScheduleJob extends DataEntity<TaskScheduleJob> {
	
	private static final long serialVersionUID = 1L;
	private String cronExpression;		// cron表达式
	private String methodName;		// 方法名
	private String isConcurrent;		// 是否有状态
	private String description;		// 任务描述
	private String beanClass;		// 调用方法(全限定类名)
	private String jobStatus;		// 任务状态
	private String jobGroup;		// 任务分组
	private String springBean;		// Spring bean
	private String jobName;		// 任务名
	
	public TaskScheduleJob() {
		super();
	}

	public TaskScheduleJob(String id){
		super(id);
	}

	@Length(min=1, max=255, message="cron表达式长度必须介于 1 和 255 之间")
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	@Length(min=1, max=255, message="方法名长度必须介于 1 和 255 之间")
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	@Length(min=0, max=255, message="是否有状态长度必须介于 0 和 255 之间")
	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	
	@Length(min=0, max=255, message="任务描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="调用方法(全限定类名)长度必须介于 0 和 255 之间")
	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	
	@Length(min=0, max=255, message="任务状态长度必须介于 0 和 255 之间")
	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	@Length(min=0, max=255, message="任务分组长度必须介于 0 和 255 之间")
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	@Length(min=0, max=255, message="Spring bean长度必须介于 0 和 255 之间")
	public String getSpringBean() {
		return springBean;
	}

	public void setSpringBean(String springBean) {
		this.springBean = springBean;
	}
	
	@Length(min=0, max=255, message="任务名长度必须介于 0 和 255 之间")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
}