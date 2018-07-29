<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title>下单管理管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.table-striped tbody tr:nth-child(odd) td {
			background-color: #8DBE5A;
		}

	</style>
	<script type="text/javascript">

		$(document).ready(function() {
            var clipboard = new ClipboardJS('.copy');

            clipboard.on('success', function(e) {
                console.info('Action:', e.action);
                console.info('Text:', e.text);
                console.info('Trigger:', e.trigger);
                top.$.jBox.alert("复制"+e.text+"成功");
                e.clearSelection();
            });

            clipboard.on('error', function(e) {
                console.error('Action:', e.action);
                console.error('Trigger:', e.trigger);
                top.$.jBox.alert("复制"+e.text+"失败");
            });


            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        var oldAction=$("#searchForm").attr("action");
                        $("#searchForm").attr("action","${ctx}/simpleorder/simpleOrder/export");
                        $("#searchForm").submit();
                        $("#searchForm").attr("action",oldAction);
                    }
                },{buttonsFocus:1});
                top.$('.jbox-body .jbox-icon').css('top','55px');
            });
            $(":checkbox[name='orderIds']").click(function(){
                $("#checkId").attr('checked', $(":checkbox[name='orderIds']").length==$(":checkbox[name='orderIds']:checked").length);
            });
            $(":checkbox[name='checkId']").click(function(){
                checkAll(this, 'orderId');
            });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }


        function checkdel(){
            var num = $("input[type='checkbox']:checked").length;
            if(num == 0){
                top.$.jBox.alert("请选择你要对账的数据");
            }else{
                confirmx('确定要对账已选中的数据吗？',repairDel);
            }

        }

        function repairDel(){
            var ids=[];
            $("input[name='orderIds']:checked").each(function(){
                ids.push($(this).val());
            });
            var delIds=ids.join(",");
            var oldAction=$("#searchForm").attr("action");
            $("#searchForm").attr("action","${ctx}/simpleorder/simpleOrder/account?ids="+delIds);
            $("#searchForm").submit();
            $("#searchForm").attr("action",oldAction);
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
			<li><label>订单号：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('a_simple_order_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否有单号：</label>
				<form:select path="isdelivernumber" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>快递单号：</label>
				<form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${simpleOrder.beginCreateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${simpleOrder.endCreateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>收件人：</label>
				<form:input path="consignee" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>

		<%--	<li><label>快递信息：</label>
				<form:input path="deliverinfo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>--%>
			<li><label>是否对账：</label>
				<form:select path="isaccount" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<a href="#" onclick="checkdel()" class="btn btn-primary">批量对账</a></li>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table  table-bordered table-striped  table-condensed table-hover">
		<thead>
			<tr>
				<th><input type=checkbox name="checkId" id="checkId"  ></th>
				<th>订单号</th>
				<th>客户名称</th>
				<th>货号</th>
				<th>颜色|尺码</th>
				<th>数量</th>
				<th>状态</th>
				<th>售价</th>
				<th>快递费用</th>
				<th>总计</th>
				<th>快递</th>
				<th>收件信息</th>
				<%--<th>快递信息</th>--%>
				<th>是否对账</th>
				<th>创建时间</th>
				<th>发货时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="simpleorder:simpleOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td> </td>
			<td>
			</td>
			<td>
			</td>
			<td>
			</td>
			<td>
			</td>
			<td>
			</td>
			<td>
			</td>
			<td>
				${simpleOrder2.money}
			</td>
			<td>
				${simpleOrder2.delivermoney}
			</td>
			<td>
				${simpleOrder2.totalmoney}
			</td>
			<td>

			</td>
			<td>

			</td>
			<td>

			</td>
			<td>

			</td>
			<td>

			</td>
			<td>

			</td>
			<td>
			</td>
		</tr>
		<c:forEach items="${page.list}" var="simpleOrder">
			<tr>
				<td><input type="checkbox" name="orderIds" value="${simpleOrder.id}"   /></td>
				<td class="copy" data-clipboard-text="${simpleOrder.orderId}" title="点击复制">${simpleOrder.orderId}</td>
				<td>
						${simpleOrder.agentName}
				</td>
				<td>
						${simpleOrder.articleno}

				</td>
				<td>

				        	<c:if test="${not empty simpleOrder.colour}">
							 ${simpleOrder.colour}<span style="color: red">|</span>
							</c:if>
							  ${simpleOrder.spec}
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
						${simpleOrder.delivernumber}
				</td>
				<td class="copy" data-clipboard-text="${simpleOrder.consignee}，${simpleOrder.phone}，${simpleOrder.address}，" title="点击复制">
				<c:if test="${empty simpleOrder.deliverinfo}">
					${simpleOrder.consignee}，${simpleOrder.phone}，${simpleOrder.address}，
				</c:if>
				<c:if test="${not empty simpleOrder.deliverinfo}">
					${simpleOrder.deliverinfo}
				</c:if>
				</td>
				<td>
						${fns:getDictLabel(simpleOrder.isaccount, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${simpleOrder.createDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					<fmt:formatDate value="${simpleOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${simpleOrder.remarks}
				</td>
				<shiro:hasPermission name="simpleorder:simpleOrder:edit"><td>
					<c:if test="${simpleOrder.state==1||simpleOrder.state==2}">
						<a href="${ctx}/simpleorder/simpleOrder/deliverform?id=${simpleOrder.id}">发货</a>
					</c:if>
    				<a href="${ctx}/simpleorder/simpleOrder/form?id=${simpleOrder.id}">修改</a>
					<c:if test="${(empty simpleOrder.isaccount||simpleOrder.isaccount==0) && simpleOrder.state==3}">
					<a href="${ctx}/simpleorder/simpleOrder/isaccount?id=${simpleOrder.id}" onclick="return confirmx('确认要对账吗？', this.href)">对账</a>
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