<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>下单管理管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        var oldAction = $("#searchForm").attr("action");
                        $("#searchForm").attr("action", "${ctx}/msimpleorder/export");
                        $("#searchForm").submit();
                        $("#searchForm").attr("action", oldAction);
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
        });


        function page(n, s) {
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
<form:form id="searchForm" modelAttribute="simpleOrder" action="${ctx}/msimpleorder/" method="post"
           class="breadcrumb form-search">
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
                <form:options items="${fns:getDictList('a_simple_order_state')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>售后状态：</label>
            <form:select path="afterstate" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('a_simple_order_afterstate')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>是否有单号：</label>
            <form:select path="isdelivernumber" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>收件人：</label>
            <form:input path="consignee" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
        <li><label>快递单号：</label>
            <form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
            <%--<li><label>快递信息：</label>
                <form:input path="deliverinfo" htmlEscape="false" maxlength="32" class="input-medium"/>
            </li>--%>
        <li><label>是否对账：</label>
            <form:select path="isaccount" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>剩余金额:</label>
            <input value="${agent.money}" disabled maxlength="20" class="input-medium"/>
        </li>
        <li><label>创建时间：</label>
            <input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${simpleOrder.beginCreateDate}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
            <input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${simpleOrder.endCreateDate}" pattern="yyyy-MM-dd"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
        <th>订单号</th>
        <th>货号</th>
        <th>颜色|尺码</th>
        <th>数量</th>
        <th>状态</th>
        <th>售后状态</th>
        <th>类别</th>
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
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="simpleOrder">
        <tr>
            <td>
                    ${simpleOrder.orderId}
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
                    ${fns:getDictLabel(simpleOrder.afterstate, 'a_simple_order_afterstate', '')}
            </td>
            <td>
                    ${fns:getDictLabel(simpleOrder.type, 'a_simple_order_type', '')}
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
                <c:if test="${not empty simpleOrder.delivernumber}">
                    <a target="_blank"
                       href="https://www.baidu.com/s?ie=UTF-8&wd=${simpleOrder.courier} ${simpleOrder.delivernumber}">查看</a>
                </c:if>
            </td>
            <td>

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
            <td>
                <c:if test="${simpleOrder.state==1}">
                    <a href="${ctx}/msimpleorder/form?id=${simpleOrder.id}">修改</a>
                    <%--<a href="${ctx}/msimpleorder/delete?id=${simpleOrder.id}"
                       onclick="return confirmx('确认要删除该下单管理吗？', this.href)">删除</a>--%>
                </c:if>
                <c:if test="${simpleOrder.state==3}">
                    <a href="${ctx}/msimpleorder/after?id=${simpleOrder.id}"
                       onclick="return confirmx('确认要售后吗？', this.href)">售后</a>
                </c:if>
                <c:if test="${simpleOrder.afterstate==4||simpleOrder.afterstate==5||simpleOrder.afterstate==7}">
                    <a href="${ctx}/msimpleorder/listSimpleOrderAfter?orderId=${simpleOrder.orderId}">售后信息</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>