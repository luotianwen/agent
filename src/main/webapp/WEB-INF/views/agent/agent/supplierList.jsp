<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理管理</title>
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
		<li class="active"><a href="${ctx}/agent/supplier/">供应商管理列表</a></li>
		<shiro:hasPermission name="agent:supplier:edit"><li><a href="${ctx}/agent/supplier/form">供应商管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="supplier" action="${ctx}/agent/supplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司名称：</label>
				<form:input path="company" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>登录名：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公司名称</th>
				<th>名称</th>
				<th>联系电话</th>
				<th>联系手机</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>登录名</th>
				<th>状态</th>
				<shiro:hasPermission name="agent:supplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="supplier">
			<tr>
				<td><a href="${ctx}/agent/supplier/form?id=${supplier.id}">
					${supplier.company}
				</a></td>
				<td>
					${supplier.name}
				</td>
				<td>
					${supplier.phone}
				</td>
				<td>
					${supplier.mobile}
				</td>
				<td>
					<fmt:formatDate value="${supplier.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${supplier.remarks}
				</td>
				<td>
					${supplier.loginName}
				</td>
				<td>
					${fns:getDictLabel(supplier.state, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="agent:supplier:edit"><td>
    				<a href="${ctx}/agent/supplier/form?id=${supplier.id}">修改</a>
					<a href="${ctx}/agent/supplier/delete?id=${supplier.id}" onclick="return confirmx('确认要删除该供应商管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>