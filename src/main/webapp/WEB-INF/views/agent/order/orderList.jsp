<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理订单管理</title>
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
		<li class="active"><a href="${ctx}/order/order/">代理订单列表</a></li>
		<shiro:hasPermission name="order:order:edit"><li><a href="${ctx}/order/order/form">代理订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/order/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>代理：</label>
				<form:input path="agentid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>金额：</label>
				<form:input path="money" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${order.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('a_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>折扣id：</label>
				<form:input path="discountid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>折扣后价：</label>
				<form:input path="discountmoney" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>快递单号：</label>
				<form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>快递公司：</label>
				<form:input path="courier" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>代理</th>
				<th>金额</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>状态</th>
				<th>折扣id</th>
				<th>折扣后价</th>
				<th>快递单号</th>
				<th>快递公司</th>
				<shiro:hasPermission name="order:order:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td><a href="${ctx}/order/order/form?id=${order.id}">
					${order.agentid}
				</a></td>
				<td>
					${order.money}
				</td>
				<td>
					<fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${order.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${order.remarks}
				</td>
				<td>
					${fns:getDictLabel(order.state, 'a_order_state', '')}
				</td>
				<td>
					${order.discountid}
				</td>
				<td>
					${order.discountmoney}
				</td>
				<td>
					${order.delivernumber}
				</td>
				<td>
					${order.courier}
				</td>
				<shiro:hasPermission name="order:order:edit"><td>
    				<a href="${ctx}/order/order/form?id=${order.id}">修改</a>
					<a href="${ctx}/order/order/delete?id=${order.id}" onclick="return confirmx('确认要删除该代理订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>