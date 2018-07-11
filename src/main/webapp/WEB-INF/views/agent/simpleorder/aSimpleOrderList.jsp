<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>下单管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/msimpleorder/export");
                        $("#searchForm").submit();
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
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
		<li class="active"><a href="${ctx}/msimpleorder/">下单管理列表</a></li>
		<li><a href="${ctx}/msimpleorder/form">下单管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="simpleOrder" action="${ctx}/msimpleorder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('a_simple_order_state')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>快递单号：</label>
				<form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>快递信息：</label>
				<form:input path="deliverinfo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>是否对账：</label>
				<form:select path="isaccount" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>剩余金额:</label>
				<input value="${agent.money}" disabled maxlength="20" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${order.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${order.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>


			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>

				<th>货号</th>
				<th>数量</th>
				<th>状态</th>
				<th>售价</th>
				<th>快递费用</th>
				<th>总计</th>
				<th>快递公司</th>
				<th>快递单号</th>
				<th>快递信息</th>
				<th>是否对账</th>
				<th>创建时间</th>
				<th>发货时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="simpleOrder">
			<tr>

				<td>
					${simpleOrder.articleno}
				</td>
				<td>
					${simpleOrder.num}
				</td>
				<td>
					${fns:getDictLabel(simpleOrder.state, 'a_simple_order_state', '')}
				</td>
				<td>
						${simpleOrder.money}
				</td>
				<td>
						${simpleOrder.delivermoney}
				</td>
				<td>
						${simpleOrder.totalmoney}
				</td>
				<td>
						${simpleOrder.courier}
				</td>
				<td>
						${simpleOrder.delivernumber}
				</td>
				<td>
						${simpleOrder.deliverinfo}
				</td>
				<td>
						${fns:getDictLabel(simpleOrder.isaccount, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${simpleOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${simpleOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${simpleOrder.remarks}
				</td>
				<td>
					<c:if test="${simpleOrder.state==1}">
    				<a href="${ctx}/msimpleorder/form?id=${simpleOrder.id}">修改</a>
					<a href="${ctx}/msimpleorder/delete?id=${simpleOrder.id}" onclick="return confirmx('确认要删除该下单管理吗？', this.href)">删除</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>