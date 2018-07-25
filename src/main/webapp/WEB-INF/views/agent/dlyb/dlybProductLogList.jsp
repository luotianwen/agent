<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>动力越博商品日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function(){
                top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                    if(v=="ok"){
                        $("#searchForm").attr("action","${ctx}/dlyb/dlybProductLog/export");
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
		<li class="active"><a href="${ctx}/dlyb/dlybProductLog/">动力越博商品日志列表</a></li>
		<shiro:hasPermission name="dlyb:dlybProductLog:edit"><li><a href="${ctx}/dlyb/dlybProductLog/form">动力越博商品日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dlybProductLog" action="${ctx}/dlyb/dlybProductLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品货号：</label>
				<form:input path="articleno" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:input path="brandname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>是否增加：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${dlybProductLog.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${dlybProductLog.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>商品货号</th>
				<th>商品名称</th>
				<th>品牌</th>
				<th>市场价</th>
				<th>折扣</th>
				<th>是否增加</th>
				<th>变化量</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="dlyb:dlybProductLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dlybProductLog">
			<tr>
				<td><a href="${ctx}/dlyb/dlybProductLog/form?id=${dlybProductLog.id}">
					${dlybProductLog.articleno}
				</a></td>
				<td>
					${dlybProductLog.name}
				</td>
				<td>
					${dlybProductLog.brandname}
				</td>
				<td>
					${dlybProductLog.marketprice}
				</td>
				<td>
					${dlybProductLog.discount}
				</td>
				<td>
					${fns:getDictLabel(dlybProductLog.state, 'yes_no', '')}
				</td>
				<td>
						${dlybProductLog.changediscount}
				</td>

				<td>
					<fmt:formatDate value="${dlybProductLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dlybProductLog.remarks}
				</td>
				<shiro:hasPermission name="dlyb:dlybProductLog:edit"><td>
    				<a href="${ctx}/dlyb/dlybProductLog/form?id=${dlybProductLog.id}">修改</a>
					<a href="${ctx}/dlyb/dlybProductLog/delete?id=${dlybProductLog.id}" onclick="return confirmx('确认要删除该动力越博商品日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>