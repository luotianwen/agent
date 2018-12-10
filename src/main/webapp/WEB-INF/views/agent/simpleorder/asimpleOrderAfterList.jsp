<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>订单售后管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        // 提示输入对话框
        function promptxcourier(title, href, closed) {
            top.$.jBox("<div class='form-search' style='padding:20px;text-align:center;'>快递公司：<input type='text' id='courier' name='courier'/> </br>快递单号：<input type='text' id='delivernumber' name='delivernumber'/>" +
                " </div>", {
                title: title, submit: function (v, h, f) {
                    if (f.courier == '') {
                        top.$.jBox.tip("请输入快递公司。", 'error');
                        return false;
                    }
                    if (f.delivernumber == '') {
                        top.$.jBox.tip("请输入快递单号。", 'error');
                        return false;
                    }

                    if (typeof href == 'function') {
                        href();
                    } else {
                        resetTip(); //loading();
                        location = href + "&courier=" + encodeURIComponent(f.courier) + "&delivernumber=" + encodeURIComponent(f.delivernumber);
                    }
                }, closed: function () {
                    if (typeof closed == 'function') {
                        closed();
                    }
                }
            });
            return false;
        }

        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        var oldAction = $("#searchForm").attr("action");
                        $("#searchForm").attr("action", "${ctx}/msimpleorder/exportSimpleOrderAfter");
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
    <li class="active"><a href="#">订单售后列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="simpleOrderAfter" action="${ctx}/msimpleorder/listSimpleOrderAfter"
           method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>订单号：</label>
            <form:input path="orderId" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
        <li><label>收件人：</label>
            <form:input path="consignee" htmlEscape="false" maxlength="100" class="input-medium"/>
        </li>
        <li><label>售后方式：</label>
            <form:select path="state" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('a_after_order_state')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>退货单号：</label>
            <form:select path="backstate" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>换货单号：</label>
            <form:select path="changestate" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>换货单号：</label>
            <form:input path="backnumber" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
        <li><label>退货单号：</label>
            <form:input path="delivernumber" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
        <li><label>退款状态：</label>
            <form:select path="afterstate" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
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
        <th>序号</th>
        <th>订单号</th>
        <th>货号</th>
        <th>地址</th>
        <th>收件人</th>
        <th>手机</th>
        <th>售后原因</th>
        <th>售后方式</th>
        <th>退货地址</th>
        <th>换货快递</th>
        <th>退货快递</th>
        <th>退款状态</th>
        <th>创建时间</th>
        <th>更新时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="simpleOrderAfter">
        <tr>
            <td>
                    ${simpleOrderAfter.no}
            </td>
            <td> ${simpleOrderAfter.orderId}
            </td>
            <td>
                    ${simpleOrderAfter.articleno}
            </td>
            <td>
                    ${simpleOrderAfter.address}
            </td>
            <td>
                    ${simpleOrderAfter.consignee}
            </td>
            <td>
                    ${simpleOrderAfter.phone}
            </td>
            <td>
                    ${simpleOrderAfter.remarks}
            </td>
            <td>
                    ${fns:getDictLabel(simpleOrderAfter.state, 'a_after_order_state', '')}
            </td>
            <td>
                    ${simpleOrderAfter.backaddress}
            </td>
            <td>
                    ${simpleOrderAfter.backcourier},${simpleOrderAfter.backnumber} ,
                    ${simpleOrderAfter.backmoney}
                <c:if test="${not empty simpleOrderAfter.backnumber}">
                    <a target="_blank" href="https://www.baidu.com/s?ie=UTF-8&wd=${simpleOrderAfter.backnumber}">查看</a>
                </c:if>
            </td>
            <td>
                    ${simpleOrderAfter.courier}
                    ${simpleOrderAfter.delivernumber}
                <c:if test="${not empty simpleOrderAfter.delivernumber}">

                    <a target="_blank"
                       href="https://www.baidu.com/s?ie=UTF-8&wd=${simpleOrderAfter.delivernumber}">查看</a>
                </c:if>
            </td>
            <td>
                <c:if test="${simpleOrderAfter.state==2}" >
                    ${fns:getDictLabel(simpleOrderAfter.afterstate, 'yes_no', '')}
                 </c:if>
            </td>
            <td>
                <fmt:formatDate value="${simpleOrderAfter.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${simpleOrderAfter.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>

                <c:if test="${not empty simpleOrderAfter.backaddress && empty simpleOrderAfter.delivernumber }">
                    <a href="${ctx}/msimpleorder/courier?id=${simpleOrderAfter.id}&orderId=${simpleOrderAfter.orderId}"
                       onclick="return promptxcourier('填写快递信息',   this.href)">快递信息</a>
                </c:if>
                <c:if test="${  empty simpleOrderAfter.backaddress  }">
                    <a href="${ctx}/msimpleorder/deleteSimpleOrderAfter?id=${simpleOrderAfter.id}"
                       onclick="return confirmx('确认要删除该订单售后吗？', this.href)">删除</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>