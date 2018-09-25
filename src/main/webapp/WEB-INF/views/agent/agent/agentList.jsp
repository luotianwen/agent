<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理管理</title>
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
        function getIds() {
            var ids = [];
            $("input[name='ids']:checked").each(function () {
                ids.push({id:$(this).val(),name:$(this).attr("label")});
            });
            return ids;
        }
	</script>
</head>
<body>
<c:if test="${empty agent.checked}">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/agent/agent/">代理列表</a></li>
		<shiro:hasPermission name="agent:agent:edit"><li><a href="${ctx}/agent/agent/form">代理添加</a></li></shiro:hasPermission>
	</ul>
</c:if>
	<form:form id="searchForm" modelAttribute="agent" action="${ctx}/agent/agent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="email" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>微信：</label>
				<form:input path="weixin" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系手机：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${agent.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${agent.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>登录名：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:radiobuttons path="state" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:if test="${not empty agent.checked}">
					<th>选择</th>
				</c:if>
				<th>名称</th>
				<th>余额</th>
				<th>性别</th>
				<th>联系电话</th>
				<th>邮箱</th>
				<th>微信</th>
				<%--<th>联系手机</th>--%>



				<th>登录名</th>
				<th>状态</th>
				<th>折扣</th>
				<th>折扣名称</th>
				<th>支付宝</th>
				<th>创建时间</th>
			<%--<th>联系地址</th>--%>
				<c:if test="${  empty agent.checked}">
				<shiro:hasPermission name="agent:agent:edit"><th>操作</th></shiro:hasPermission>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="agent2">
			<tr>
				<c:if test="${not empty agent.checked}">
					<td>
						<input type="checkbox" name="ids" value="${agent2.id}" label="${agent2.name}">
					</td>
				</c:if>
				<td><a href="${ctx}/agent/agent/form?id=${agent2.id}">
					${agent2.name}
				</a></td>
				<td>
						${agent2.money}
				</td>
				<td>
					${fns:getDictLabel(agent2.sex, 'sex', '')}
				</td>
				<td>
					${agent2.phone}
				</td>
				<td>
					${agent2.email}
				</td>
				<td>
					${agent2.weixin}
				</td>


			<%--	<td>
					${agent.mobile}
				</td>--%>

				<td>
					${agent2.loginName}
				</td>
				<td>
					${fns:getDictLabel(agent2.state, 'yes_no', '')}
				</td>
				<td>
					${agent2.discount}
				</td>
				<td>
					${agent2.discountName}
				</td>
				<td>
					${agent2.apay}
				</td>
				<td>
					<fmt:formatDate value="${agent2.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>
					${agent.address}
				</td>--%>
				<c:if test="${ empty agent.checked}">
				<shiro:hasPermission name="agent:agent:edit"><td>
    				<a href="${ctx}/agent/agent/form?id=${agent2.id}">审核</a>
					<a href="${ctx}/agent/recharge/form?agentid=${agent2.id}&agentName=${agent.name}">充值</a>
					<a href="${ctx}/agent/agent/delete?id=${agent2.id}" onclick="return confirmx('确认要删除该代理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>