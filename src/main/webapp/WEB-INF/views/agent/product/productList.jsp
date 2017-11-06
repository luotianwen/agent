<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理管理</title>
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
		<li class="active"><a href="${ctx}/product/product/">商品管理列表</a></li>
		<shiro:hasPermission name="product:product:edit"><li><a href="${ctx}/product/product/form">商品管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/product/product/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>商品描述信息：</label>
				<form:input path="descr" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li><label>商品类别：</label>
				<form:input path="division" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>商品上市季节：</label>
				<form:input path="quarter" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:input path="brandname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:input path="sex" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>商品颜色：</label>
				<form:input path="colour" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>上市时间：</label>
				<input name="beginListingdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${product.beginListingdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endListingdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${product.endListingdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>库存：</label>
				<form:input path="total" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>商品id：</label>
				<form:input path="pid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>销售价：</label>
				<form:input path="price" htmlEscape="false" class="input-medium"/>
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
				<th>商品类别</th>
				<th>商品上市季节</th>
				<th>品牌</th>
				<th>性别</th>
				<th>商品颜色</th>
				<th>市场价</th>
				<th>上市时间</th>
				<th>库存</th>
				<th>更新时间</th>
				<th>备注信息</th>

				<shiro:hasPermission name="product:product:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td><a href="${ctx}/product/product/form?id=${product.id}">
					${product.articleno}
				</a></td>
				<td>
					${product.division}
				</td>
				<td>
					${product.quarter}
				</td>
				<td>
					${product.brandname}
				</td>
				<td>
					${product.sex}
				</td>
				<td>
					${product.colour}
				</td>
				<td>
					${product.marketprice}
				</td>
				<td>
					<fmt:formatDate value="${product.listingdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${product.total}
				</td>
				<td>
					<fmt:formatDate value="${product.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${product.remarks}
				</td>

				<shiro:hasPermission name="product:product:edit"><td>
    				<a href="${ctx}/product/product/form?id=${product.id}">修改</a>
					<a href="${ctx}/stock/stock/list?articleno=${product.articleno}">查看库存</a>
					<a href="${ctx}/product/product/delete?id=${product.id}" onclick="return confirmx('确认要删除该商品管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>