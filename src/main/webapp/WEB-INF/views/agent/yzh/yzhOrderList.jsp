<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>云中鹤订单管理管理</title>
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
		<li class="active"><a href="${ctx}/yzh/yzhOrder/">云中鹤订单管理列表</a></li>
	<%--	<shiro:hasPermission name="yzh:yzhOrder:edit"><li><a href="${ctx}/yzh/yzhOrder/form">云中鹤订单管理添加</a></li></shiro:hasPermission>
	--%></ul>
	<form:form id="searchForm" modelAttribute="yzhOrder" action="${ctx}/yzh/yzhOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>收货人姓名：</label>
				<form:input path="receivername" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>售后产品：</label>
				<form:input path="productid" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>订单号：</label>
				<form:input path="thirdorder" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>系统订单号：</label>
				<form:input path="orderKey" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>收货人手机：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单号</th>
				<th>收货人</th>
				<th>手机</th>
				<th>产品</th>
				<th>数量</th>
				<th>产品价格</th>
				<th>快递信息</th>
				<th>快递费</th>
				<th>系统订单号</th>
				<th>订单总金额</th>
				<th>售后类型</th>
				<th>售后信息</th>
				<th>换货单号</th>

				<shiro:hasPermission name="yzh:yzhOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="yzhOrder">
			<tr>
				<td>
					${yzhOrder.thirdorder}
				 </td>
				<td>
						${yzhOrder.receivername}
				</td>
				<td>
						${yzhOrder.mobile}
				</td>
				<td>
					${yzhOrder.productid}
				</td>
				<td>
						${yzhOrder.amount}
				</td>
				<td>
					${yzhOrder.orderProductPrice}
				</td>
				<td>
						${yzhOrder.shipmentName}-${yzhOrder.shipmentOrder}
				</td>
				<td>
						${yzhOrder.orderShipmentFee}
				</td>
				<td>
					${yzhOrder.orderKey}
				</td>
				<td>
					${yzhOrder.orderTotalPrice}
				</td>
				<td>
						${yzhOrder.serviceOrder} ${yzhOrder.type}
				</td>
				<td>
				 ${yzhOrder.receiverName2} ${yzhOrder.receiverMobile} 		${yzhOrder.returnedAddress}
				</td>

				<td>
						${yzhOrder.newOrder}  ${yzhOrder.receiverStatus}
				</td>

				<shiro:hasPermission name="yzh:yzhOrder:edit"><td>

					<c:if test="${ empty yzhOrder.shipmentOrder}">
					<a href="${ctx}/yzh/yzhOrder/kd?id=${yzhOrder.id}" onclick="return confirmx('确认要拉取快递吗？', this.href)">拉取快递</a>
					</c:if>
					<c:if test="${ empty yzhOrder.serviceOrder}">
						<a href="${ctx}/yzh/yzhOrder/after?id=${yzhOrder.id}"
						   onclick="return confirmx('确认要售后吗？', this.href)">售后</a>
					</c:if>
					<c:if test="${ not empty yzhOrder.serviceOrder}">
						<a href="${ctx}/yzh/yzhOrder/returnedAddress?id=${yzhOrder.id}"
						   onclick="return confirmx('确认要拉取地址吗？', this.href)">拉取售后信息</a>
					</c:if>
					<a href="${ctx}/yzh/yzhOrder/delete?id=${yzhOrder.id}" onclick="return confirmx('确认要删除该云中鹤订单管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>