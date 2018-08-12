<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>动力越博库存日志管理</title>
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
		<li class="active"><a href="${ctx}/dlyb/dlybProductStockLog/">动力越博库存日志列表</a></li>
		<shiro:hasPermission name="dlyb:dlybProductStockLog:edit"><li><a href="${ctx}/dlyb/dlybProductStockLog/form">动力越博库存日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dlybProductStockLog" action="${ctx}/dlyb/dlybProductStockLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li><label>变化量>=:</label>
				<form:input path="changenum" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dlybProductStockLog.beginCreateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dlybProductStockLog.endCreateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品货号</th>
				<th>名称</th>
				<th>品牌</th>
				<th>规格</th>
				<th>数量</th>
				<th>是否增加</th>
				<th>之前库存</th>
				<th>库存改变量</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="dlyb:dlybProductStockLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dlybProductStockLog">
			<tr>
				<td><a href="${ctx}/dlyb/dlybProductStockLog/form?id=${dlybProductStockLog.id}">
					${dlybProductStockLog.articleno}
				</a></td>
				<td>
					${dlybProductStockLog.name}
				</td>
				<td>
					${dlybProductStockLog.brandname}
				</td>
				<td>
					${dlybProductStockLog.spec}
				</td>
				<td>
					${dlybProductStockLog.num}
				</td>
				<td>
					${fns:getDictLabel(dlybProductStockLog.state, 'yes_no', '')}
				</td>
				<td>
					${dlybProductStockLog.beforenum}
				</td>
				<td>
					${dlybProductStockLog.changenum}
				</td>
				<td>
					<fmt:formatDate value="${dlybProductStockLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dlybProductStockLog.remarks}
				</td>
				<shiro:hasPermission name="dlyb:dlybProductStockLog:edit"><td>
    				<a href="${ctx}/dlyb/dlybProductStockLog/form?id=${dlybProductStockLog.id}">修改</a>
					<a href="${ctx}/dlyb/dlybProductStockLog/delete?id=${dlybProductStockLog.id}" onclick="return confirmx('确认要删除该动力越博库存日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>