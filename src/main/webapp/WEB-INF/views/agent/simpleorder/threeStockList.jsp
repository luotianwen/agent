<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库管理管理</title>
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
		<li class="active"><a href="${ctx}/simpleorder/threeStock/">仓库管理列表</a></li>
		<shiro:hasPermission name="simpleorder:threeStock:edit"><li><a href="${ctx}/simpleorder/threeStock/form">仓库管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="threeStock" action="${ctx}/simpleorder/threeStock/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>联系人：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>微信：</label>
				<form:input path="weixin" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>联系人</th>
				<th>电话</th>
				<th>微信</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="simpleorder:threeStock:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="threeStock">
			<tr>
				<td><a href="${ctx}/simpleorder/threeStock/form?id=${threeStock.id}">
					${threeStock.name}
				</a></td>
				<td>
					${threeStock.phone}
				</td>
				<td>
					${threeStock.weixin}
				</td>
				<td>
					<fmt:formatDate value="${threeStock.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${threeStock.remarks}
				</td>
				<shiro:hasPermission name="simpleorder:threeStock:edit"><td>
    				<a href="${ctx}/simpleorder/threeStock/form?id=${threeStock.id}">修改</a>
					<a href="${ctx}/simpleorder/threeStock/delete?id=${threeStock.id}" onclick="return confirmx('确认要删除该仓库管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>