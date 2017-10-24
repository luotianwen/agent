/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.task.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.MyBeanUtils;
import com.thinkgem.jeesite.common.utils.ObjectUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.task.entity.TaskScheduleJob;
import com.thinkgem.jeesite.modules.task.service.TaskScheduleJobService;

/**
 * 定时任务Controller
 * @author lee
 * @version 2017-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/task/taskScheduleJob")
public class TaskScheduleJobController extends BaseController {

	@Autowired
	private TaskScheduleJobService taskScheduleJobService;
	
	@ModelAttribute
	public TaskScheduleJob get(@RequestParam(required=false) String id) {
		TaskScheduleJob entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = taskScheduleJobService.get(id);
		}
		if (entity == null){
			entity = new TaskScheduleJob();
		}
		return entity;
	}
	
	@RequiresPermissions("task:taskScheduleJob:view")
	@RequestMapping(value = {"list", ""})
	public String list(TaskScheduleJob taskScheduleJob, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TaskScheduleJob> page = taskScheduleJobService.findPage(new Page<TaskScheduleJob>(request, response), taskScheduleJob); 
		model.addAttribute("page", page);
		return "modules/task/taskScheduleJobList";
	}

	@RequiresPermissions("task:taskScheduleJob:view")
	@RequestMapping(value = "form")
	public String form(TaskScheduleJob taskScheduleJob, Model model) {
		model.addAttribute("taskScheduleJob", taskScheduleJob);
		return "modules/task/taskScheduleJobForm";
	}

	@RequiresPermissions("task:taskScheduleJob:edit")
	@RequestMapping(value = "save")
	public String save(TaskScheduleJob taskScheduleJob, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, taskScheduleJob)){
			return form(taskScheduleJob, model);
		}
		taskScheduleJobService.save(taskScheduleJob);
		addMessage(redirectAttributes, "保存定时任务成功");
		return "redirect:"+Global.getAdminPath()+"/task/taskScheduleJob/?repage";
	}
	
	@RequiresPermissions("task:taskScheduleJob:edit")
	@RequestMapping(value = "delete")
	public String delete(TaskScheduleJob taskScheduleJob, RedirectAttributes redirectAttributes) {
		taskScheduleJobService.delete(taskScheduleJob);
		addMessage(redirectAttributes, "删除定时任务成功");
		return "redirect:"+Global.getAdminPath()+"/task/taskScheduleJob/?repage";
	}

	@RequestMapping(value = "/changeJobStatus")
	public String changeJobStatus(TaskScheduleJob scheduleJob, HttpServletRequest request,
			HttpServletResponse response,RedirectAttributes redirectAttributes) {
		String cmd = request.getParameter("cmd");
		String label = "停止";
		if (cmd.equals("start")) {
			label = "启动";
		} else {
			label = "停止";
		}
		addMessage(redirectAttributes,"任务" + label + "成功");
		try {
			taskScheduleJobService.changeStatus(scheduleJob.getId(), cmd);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes,"任务" + label + "失败" + e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/task/taskScheduleJob/?repage";
	}

	@RequestMapping(value = "/updateCron")
	public String updateCron(TaskScheduleJob scheduleJob, HttpServletRequest request,
			HttpServletResponse response,RedirectAttributes redirectAttributes,Model model) {
		addMessage(redirectAttributes,"任务更新成功");
		try {
			taskScheduleJobService.updateCron(scheduleJob.getId());
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes,"任务更新失败" + e.getMessage());
		}
		Page<TaskScheduleJob> page = taskScheduleJobService.findPage(new Page<TaskScheduleJob>(request, response), scheduleJob); 
		model.addAttribute("page", page);
		return "redirect:"+Global.getAdminPath()+"/task/taskScheduleJob/?repage";
	}
	
	@RequestMapping(value = "/saveScheduleJob")
	public String saveScheduleJob(TaskScheduleJob scheduleJob, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes,Model model) {
		addMessage(redirectAttributes,"保存成功");
		if (!CronExpression.isValidExpression(scheduleJob.getCronExpression())) {
			addMessage(redirectAttributes,"cron表达式格式不对");
	    	return "redirect:"+Global.getAdminPath()+"/task/taskScheduleJob/?repage";
		}
		try {
			 if (ObjectUtils.isNullOrEmpty(scheduleJob.getId())) {
				 taskScheduleJobService.save(scheduleJob);
			} else {
				// FORM NULL不更新
				TaskScheduleJob oldEntity = taskScheduleJobService.get(scheduleJob.getId());
				MyBeanUtils.copyBeanNotNull2Bean(scheduleJob, oldEntity);
				taskScheduleJobService.save(oldEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes,"保存失败"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/task/taskScheduleJob/?repage";
	}
}