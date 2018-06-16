<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>下单管理管理</title>
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
		<li class="active"><a href="${ctx}/simpleorder/simpleOrder/">下单管理列表</a></li>
		<shiro:hasPermission name="simpleorder:simpleOrder:edit"><li><a href="${ctx}/simpleorder/simpleOrder/form">下单管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="simpleOrder" action="${ctx}/simpleorder/simpleOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('a_simple_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>快递单号：</label>
				<form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${simpleOrder.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${simpleOrder.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>是否对账：</label>
				<form:select path="isaccount" class="input-medium">
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
				<th>货号</th>
				<th>数量</th>
				<th>金额</th>
				<th>状态</th>
				<th>快递公司</th>
				<th>快递单号</th>
				<th>快递费</th>
				<th>创建时间</th>
				<th>总价</th>
				<th>是否对账</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="simpleorder:simpleOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="simpleOrder">
			<tr>
				<td><a href="${ctx}/simpleorder/simpleOrder/form?id=${simpleOrder.id}">
					${simpleOrder.articleno}
				</a></td>
				<td>
					${simpleOrder.num}
				</td>
				<td>
					${simpleOrder.money}
				</td>
				<td>
					${fns:getDictLabel(simpleOrder.state, 'a_simple_order_state', '')}
				</td>
				<td>
					${simpleOrder.courier}
				</td>
				<td>
					${simpleOrder.delivernumber}
				</td>
				<td>
					${simpleOrder.delivermoney}
				</td>
				<td>
					<fmt:formatDate value="${simpleOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${simpleOrder.totalmoney}
				</td>
				<td>
					${fns:getDictLabel(simpleOrder.isaccount, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${simpleOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${simpleOrder.remarks}
				</td>
				<shiro:hasPermission name="simpleorder:simpleOrder:edit"><td>
    				<a href="${ctx}/simpleorder/simpleOrder/form?id=${simpleOrder.id}">修改</a>
					<c:if test="${simpleorder.state==1}">
					   <a href="${ctx}/simpleorder/simpleOrder/deliverform?id=${simpleOrder.id}">发货</a>
					</c:if>

					<a href="${ctx}/simpleorder/simpleOrder/delete?id=${simpleOrder.id}" onclick="return confirmx('确认要删除该下单管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>