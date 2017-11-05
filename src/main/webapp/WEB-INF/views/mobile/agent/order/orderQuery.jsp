<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<%@ include file="/WEB-INF/views/include/mhead.jsp"%><!DOCTYPE >
<body>

<div data-role="page">
    <div data-role="header">
        <a href="#index_section?index"  class="ui-btn ui-shadow ui-corner-all ui-btn-icon-left ui-icon-back" data-rel="back" >返回</a>
        <h1  >订单查询</h1>
    </div>
    <div data-role="main" class="ui-content">
        <form id="orderqueryForm"   method="post">
            <label for="onumbers">订单号</label>
            <input type="text" name="onumbers" id="onumbers"  value="${order.onumber}" placeholder="请填写订单号">
            <button type="submit"  class="ui-icon-user">查询</button>

        </form>

        <div data-role="popup" id="popupBasic">
            <p id="content">ss</p>
        </div>
    </div>


    <div style="padding: 1px;" id="orderdata">
    </div>

</div>
<script type="text/javascript">
    var sessionid = '${not empty fns:getPrincipal() ? fns:getPrincipal().sessionid : ""}';
    if(sessionid==''){
        window.location=ctx+"/login";
    }
    $(function () {
        $("#orderqueryForm").submit(function(){
            $.mobile.loading('show');
            $.post("${ctx}/order/data", {onumber:$("#onumbers").val()}, function(data){
                $.mobile.loading( "hide" );
                $('#orderdata').html(data);
            });
            return false;
        });

    });
</script>

</body>
</html>