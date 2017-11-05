<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<%@ include file="/WEB-INF/views/include/mhead.jsp"%><!DOCTYPE >
<body>
<div data-role="page">

    <div data-role="header">
        <h1   >${fns:getConfig('productName')}</h1>
       <%-- <a href="#index_section?index" id="btnLogout" class="ui-btn ui-icon-forward ui-btn-right ui-btn-icon-right"   >退出</a>
       --%> <a  href="#" id="btnLogout" class="ui-btn-right ui-btn-icon-right ui-icon-forward">退出</a>
    <%-- <div style=" position: absolute;top:1em;right:0">
            <a  href="#" id="btnLogout">退出</a>
        </div>--%>
    </div>
    <div data-role="main" class="ui-content">
        <ul data-role="listview">
            <li><a href="#" id="stock" data-transition="slide">库存查询</a></li>
            <li><a href="#" id="orderquery" data-transition="slide">订单查询</a></li>
            <li><a href="#"  id="ordersave" data-transition="slide">下订单</a></li>

        </ul>
       <%-- <a href="#stock_section" data-role="button" id="stock" data-transition="slide">
            <strong>库存查询</strong>
        </a>
        <a href="#orderquery_section" data-role="button" id="orderquery" data-transition="slide">
            <strong>订单查询</strong>
        </a>
        <a href="#ordersave_section" data-role="button" id="ordersave" data-transition="slide">
            <strong>下订单</strong>
        </a>--%>
    </div>


</div>
<script type="text/javascript">
    var sessionid = '${not empty fns:getPrincipal() ? fns:getPrincipal().sessionid : ""}';
    if(sessionid==''){
        window.location=ctx+"/login";
    }
    $(function () {
        $("#stock").on('click',function () {
            window.location= ctx + '/stock/stock/query';
        });
        $("#orderquery").on('click',function () {
            window.location=  ctx + '/order/query';
        });
        $("#ordersave").on('click',function () {
            window.location= ctx + '/order/qf';
        });
        $("#btnLogout").on('click',function () {
            confirmJQM('确认退出?', function(){
                $.get("${ctx}/logout", function(){
                    sessionid = '';
                    window.location=ctx+"/login";
                });
            });
        });
    });

</script>

</body>
</html>