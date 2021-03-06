<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存管理</title>
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
		<li class="active"><a href="${ctx}/stock/stock/">库存列表</a></li>
		<shiro:hasPermission name="stock:stock:edit"><li><a href="${ctx}/stock/stock/form">库存添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="stock" action="${ctx}/stock/stock/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>商品类别：</label>
				<form:input path="division" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:input path="brandname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>货源名：</label>
				<form:input path="warehousename" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${stock.beginCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${stock.endCreateDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>货源名</th>
				<th>商品货号</th>
				<th>商品类别</th>
				<th>品牌</th>
				<th>尺码2</th>
				<th>尺码1</th>
				<th>库存数量</th>
				<th>性别</th>
				<th>上市季节</th>
				<th>市场价</th>
				<th>折扣</th>
				<th>更新时间</th>

				<shiro:hasPermission name="stock:stock:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="stock">
			<tr>
				<td><a href="${ctx}/stock/stock/form?id=${stock.id}">
					${stock.warehousename}
				</a></td>

				<td>
						${stock.articleno}
				</td>
				<td>
					${stock.division}
				</td>
				<td>
					${stock.brandname}
				</td>
				<td>
					${stock.uksize}
				</td>
				<td>
					${stock.size}
				</td>
				<td>
					${stock.innernum}
				</td>
				<td>
					${stock.sex}
				</td>
				<td>
						${stock.quarter}
				</td>
				<td>
						${stock.marketprice}
				</td>
				<td>
						${stock.discount}
				</td>
				<td>
					<fmt:formatDate value="${stock.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>


				<shiro:hasPermission name="stock:stock:edit"><td>
    				<a href="${ctx}/stock/stock/form?id=${stock.id}">修改</a>
					<a href="${ctx}/stock/stock/delete?id=${stock.id}" onclick="return confirmx('确认要删除该库存吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>