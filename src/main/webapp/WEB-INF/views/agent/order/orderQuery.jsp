<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>代理订单管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">

        $(function () {
            $("#orderqueryForm").submit(function(){

                $.post("${ctx}/order/data", {onumber:$("#onumbers").val()}, function(data){

                    $('#orderdata').html(data);
                });
                return false;
            });

        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/order/query">订单列表</a></li>
    <li ><a href="${ctx}/order/qf">下单</a></li>
</ul>
<form:form id="orderqueryForm"    method="post" class="breadcrumb form-search">

    <ul class="ul-form">
        <li><label>订单号：</label>
            <input type="text" name="onumbers" id="onumbers"  value="${order.onumber}" placeholder="请填写订单号">
        </li>

        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<div style="padding: 1px;" id="orderdata">


<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>

        <th>订单号</th>
        <th>货号</th>
        <th>快递单号</th>
        <th>订单状态</th>
        <th>金额</th>


    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
</div>
</body>
</html>