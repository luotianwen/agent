<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/task/taskScheduleJob/">定时任务列表</a></li>
		<shiro:hasPermission name="task:taskScheduleJob:edit"><li><a href="${ctx}/task/taskScheduleJob/form">定时任务添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="taskScheduleJob" action="${ctx}/task/taskScheduleJob/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>方法名：</label>
				<form:input path="methodName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>是否有状态：</label>
				<form:select path="isConcurrent" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>任务状态：</label>
				<form:select path="jobStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('job_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>任务分组：</label>
				<form:input path="jobGroup" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>cron表达式</th>
				<th>方法名</th>
				<th>是否有状态</th>
				<th>调用方法(全限定类名)</th>
				<th>任务状态</th>
				<th>任务分组</th>
				<th>更新时间</th>
				<th>Spring bean</th>
				<th>任务名</th>
				<shiro:hasPermission name="task:taskScheduleJob:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="taskScheduleJob">
			<tr>
				<td>
					${taskScheduleJob.cronExpression}
				</td>
				<td>
					${taskScheduleJob.methodName}
				</td>
				<td>
					${fns:getDictLabel(taskScheduleJob.isConcurrent, 'yes_no', '')}
				</td>
				<td>
					${taskScheduleJob.beanClass}
				</td>
				<td>
					${fns:getDictLabel(taskScheduleJob.jobStatus, 'job_status', '')}
				</td>
				<td>
					${taskScheduleJob.jobGroup}
				</td>
				<td>
					<fmt:formatDate value="${taskScheduleJob.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${taskScheduleJob.springBean}
				</td>
				<td>
					${taskScheduleJob.jobName}
				</td>
				<shiro:hasPermission name="task:taskScheduleJob:edit"><td>
					<c:if test="${taskScheduleJob.jobStatus==0}">
					<a href="${ctx}/task/taskScheduleJob/changeJobStatus?id=${taskScheduleJob.id}&cmd=start" onclick="return confirmx('你确定要启动该计划任务么?', this.href)">启动</a>
					</c:if>
					<c:if test="${taskScheduleJob.jobStatus==1}">
					<a href="${ctx}/task/taskScheduleJob/changeJobStatus?id=${taskScheduleJob.id}&cmd=stop" onclick="return confirmx('你确定要停止该计划任务么?', this.href)">停止</a>

					</c:if>

					<a href="${ctx}/task/schedulejob/form?id=${taskScheduleJob.id}"  onclick="return confirmx('你确定要更新该计划任务么?', this.href)">更新</a>
					<a href="${ctx}/task/taskScheduleJob/delete?id=${taskScheduleJob.id}" onclick="return confirmx('确认要删除该定时任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>