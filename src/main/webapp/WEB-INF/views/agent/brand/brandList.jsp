<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>品牌管理</title>
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
		<li class="active"><a href="${ctx}/brand/brand/">货源仓库列表</a></li>
		<shiro:hasPermission name="brand:brand:edit"><li><a href="${ctx}/brand/brand/form">货源仓库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="brand" action="${ctx}/brand/brand/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>货源仓库名称：</label>
				<form:input path="warehousename" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>货源仓库名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="brand:brand:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="brand">
			<tr>
				<td><a href="${ctx}/brand/brand/form?id=${brand.id}">
					${brand.warehousename}
				</a></td>
				<td>
					<fmt:formatDate value="${brand.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${brand.remarks}
				</td>
				<shiro:hasPermission name="brand:brand:edit"><td>
    				<a href="${ctx}/brand/brand/form?id=${brand.id}">修改</a>
					<a href="${ctx}/stock/stock/list?warehousename=${brand.warehousename}">查看库存</a>

					<a href="${ctx}/brand/brand/delete?id=${brand.id}" onclick="return confirmx('确认要删除该货源仓库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>