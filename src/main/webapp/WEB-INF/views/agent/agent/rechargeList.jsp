<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>充值管理</title>
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
		<li class="active"><a href="${ctx}/agent/recharge/">充值列表</a></li>
		<shiro:hasPermission name="agent:recharge:edit"><li><a href="${ctx}/agent/recharge/form">充值添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recharge" action="${ctx}/agent/recharge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>金额</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="agent:recharge:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recharge">
			<tr>
				<td>
						${recharge.agentName}
				 </td>
				<td>
						${recharge.money}
				</td>
				<td>
					<fmt:formatDate value="${recharge.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				 </td>
				<td>
					${recharge.remarks}
				</td>
				<%--<shiro:hasPermission name="agent:recharge:edit"><td>
    				<a href="${ctx}/agent/recharge/form?id=${recharge.id}">修改</a>
					<a href="${ctx}/agent/recharge/delete?id=${recharge.id}" onclick="return confirmx('确认要删除该充值吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>