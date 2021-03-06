<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>订单售后管理</title>
    <meta name="decorator" content="default"/>
    <style>
        .table-striped tbody tr:nth-child(odd) td {
            background-color: #8DBE5A;
        }

    </style>
    <script type="text/javascript">
        function promptxcourier(title, href, closed) {
            layer.open({
                type: 1,
                shadeClose: true,
                content: '<div class=\'form-search\' style=\'padding:20px;text-align:center;\'>快递公司：<input type=\'text\' id=\'backcourier2\' name=\'backcourier\'/> </br>快递单号：<input type=\'text\' id=\'backnumber2\' name=\'backnumber2\'/></br>快递费用：<input type=\'text\' id=\'backmoney2\' name=\'backmoney\'/></div>',
                btn: ['确定', '取消']
                , yes: function (index, f) {
                    var backcourier=$("#backcourier2").val();
                    var backnumber=$("#backnumber2").val();
                    var backmoney=$("#backmoney2").val();
                    if (backcourier == '') {
                        top.$.jBox.tip("请输入快递公司。", 'error');
                        return false;
                    }
                    if (backnumber == '') {
                        top.$.jBox.tip("请输入快递单号。", 'error');
                        return false;
                    }
                    if (backmoney == '') {
                        top.$.jBox.tip("请输入快递费用。", 'error');
                        return false;
                    }
                    resetTip(); //loading();
                    location = href + "&backcourier=" + encodeURIComponent(backcourier) + "&backnumber=" + encodeURIComponent(backnumber) + "&backmoney=" + encodeURIComponent(backmoney);

                }
                , btn2: function (index, layero) {
                    //按钮【按钮二】的回调
                    layer.close(index);
                    return false;
                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
            return false;
        }
        // 提示输入对话框
        function promptxcourier2(title, href, closed) {
            top.$.jBox("<div class='form-search' style='padding:20px;text-align:center;'>快递公司：<input type='text' id='backcourier' name='backcourier'/> </br>快递单号：<input type='text' id='backnumber' name='backnumber'/>" +
                "</br>快递费用：<input type='text' id='backmoney' name='backmoney'/></div>", {
                title: title, submit: function (v, h, f) {
                    if (f.backcourier == '') {
                        top.$.jBox.tip("请输入快递公司。", 'error');
                        return false;
                    }
                    if (f.backnumber == '') {
                        top.$.jBox.tip("请输入快递单号。", 'error');
                        return false;
                    }
                    if (f.backmoney == '') {
                        top.$.jBox.tip("请输入快递费用。", 'error');
                        return false;
                    }
                    if (typeof href == 'function') {
                        href();
                    } else {
                        resetTip(); //loading();
                        console.log(href + "&backcourier=" + encodeURIComponent(f.backcourier) + "&backnumber=" + encodeURIComponent(f.backnumber) + "&backmoney=" + encodeURIComponent(f.backmoney))
                        location = href + "&backcourier=" + encodeURIComponent(f.backcourier) + "&backnumber=" + encodeURIComponent(f.backnumber) + "&backmoney=" + encodeURIComponent(f.backmoney);
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
            var clipboard = new ClipboardJS('.copy');

            clipboard.on('success', function (e) {
                //console.info('Action:', e.action);
                //console.info('Text:', e.text);
                //console.info('Trigger:', e.trigger);
                top.$.jBox.tip("复制" + e.text + "成功");
                e.clearSelection();
            });

            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        var oldAction = $("#searchForm").attr("action");
                        $("#searchForm").attr("action", "${ctx}/simpleorder/simpleOrderAfter/export");
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
    <li class="active"><a href="${ctx}/simpleorder/simpleOrderAfter/">订单售后列表</a></li>
    <shiro:hasPermission name="simpleorder:simpleOrderAfter:edit">
        <li><a href="${ctx}/simpleorder/simpleOrderAfter/form">订单售后添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="simpleOrderAfter" action="${ctx}/simpleorder/simpleOrderAfter/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
        <li><label>订单号：</label>
            <form:input path="orderId" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
        <li><label>收件人：</label>
            <form:input path="consignee" htmlEscape="false" maxlength="100" class="input-medium"/>
        </li>
        <li><label>手机：</label>
            <form:input path="phone" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
        <li><label>货号：</label>
            <form:input path="articleno" htmlEscape="false" maxlength="200" class="input-medium"/>
        </li>
        <li><label>售后方式：</label>
            <form:select path="state" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('a_after_order_state')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>三方售后：</label>
            <form:select path="afterstate" class="input-medium">
                <form:option value="" label="全部"/>
                <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
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

        <th>收件人</th>

        <th>售后原因</th>
        <th>售后方式</th>
        <th>退货地址</th>
        <th>换货快递</th>
        <th>退货快递</th>
        <th>三方售后</th>
        <th>创建时间</th>
        <th class="sort-column updateDate">更新时间</th>
        <shiro:hasPermission name="simpleorder:simpleOrderAf ter:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="simpleOrderAfter">
        <tr>
            <td>
                    ${simpleOrderAfter.no}
            </td>
            <td><a href="${ctx}/simpleorder/simpleOrder/list?orderId=${simpleOrderAfter.orderId}" target="_blank">
                    ${simpleOrderAfter.orderId}
            </a></td>
            <td>
                    ${simpleOrderAfter.articleno}
            </td>


            <td class="copy" data-clipboard-text="${simpleOrderAfter.consignee}，${simpleOrderAfter.phone}，，${simpleOrderAfter.address}，"
                title="点击复制">
                    <%-- 张三 ，13888888888 ，0518-88888888，江苏省 连云港市 新浦区 朝阳中路77号二楼 ，222000--%>

                    ${simpleOrderAfter.consignee}，${simpleOrderAfter.phone}，，${simpleOrderAfter.address}，

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
                    <a target="_blank" href="http://www.kuaidi100.com/chaxun?nu=${simpleOrderAfter.backnumber}">查看</a>
                </c:if>
            </td>
            <td>
                    ${simpleOrderAfter.courier}
                    ${simpleOrderAfter.delivernumber}
                <c:if test="${not empty simpleOrderAfter.delivernumber}">
                    <a target="_blank"
                       href="http://www.kuaidi100.com/chaxun?nu=${simpleOrderAfter.delivernumber}">查看</a>
                </c:if>

            </td>
            <td>
                    ${fns:getDictLabel(simpleOrderAfter.afterstate, 'yes_no', '')}
            </td>
            <td>
                <fmt:formatDate value="${simpleOrderAfter.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${simpleOrderAfter.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <shiro:hasPermission name="simpleorder:simpleOrderAfter:edit">
                <td>
                    <a href="${ctx}/simpleorder/simpleOrderAfter/form?id=${simpleOrderAfter.id}">修改</a>
                    <c:if test="${empty simpleOrderAfter.backaddress}">
                        <a href="${ctx}/simpleorder/simpleOrderAfter/backaddress?id=${simpleOrderAfter.id}&orderId=${simpleOrderAfter.orderId}&backaddress="
                           onclick="return promptx('填写退货地址','退货地址信息', this.href)">退货地址</a>
                    </c:if>
                    <c:if test="${simpleOrderAfter.state==1 &&  empty simpleOrderAfter.backcourier &&not empty simpleOrderAfter.backaddress}">
                        <a href="${ctx}/simpleorder/simpleOrderAfter/backcourier?id=${simpleOrderAfter.id}&orderId=${simpleOrderAfter.orderId}"
                           onclick="return promptxcourier('填写快递信息',   this.href)">快递信息</a>
                    </c:if>
                    <a href="${ctx}/simpleorder/simpleOrderAfter/delete?id=${simpleOrderAfter.id}"
                       onclick="return confirmx('确认要删除该订单售后吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </
    tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>