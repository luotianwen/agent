<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<%@ include file="/WEB-INF/views/include/mhead.jsp"%><!DOCTYPE >
<body>

<div data-role="page">
    <div data-role="header">
        <a href="#index_section?index"  class="ui-btn ui-shadow ui-corner-all ui-btn-icon-left ui-icon-back" data-rel="back" >返回</a>
        <h1  >库存查询</h1>
    </div>
    <div data-role="main" class="ui-content">
        <form id="stockForm"   method="post">
            <label for="articlenos">货号*</label>
            <input type="text" name="articlenos" id="articlenos"  value="${stock.articleno}" placeholder="请填写货号">
            <label for="brandnames">品牌</label>
            <input type="text" name="brandnames" id="brandnames"  value="${stock.brandname}" placeholder="请填写品牌">
            <button type="submit"  class="ui-icon-user">查询</button>

        </form>

        <div data-role="popup" id="popupBasic">
            <p id="content">ss</p>
        </div>
    </div>


    <div style="padding: 1px;" id="data">
    </div>

</div>
<script type="text/javascript">
    var sessionid = '${not empty fns:getPrincipal() ? fns:getPrincipal().sessionid : ""}';
    if(sessionid==''){
        window.location=ctx+"/login";
    }
    $(function () {
        $("#stockForm").submit(function(){
            if ($('#articlenos').val() == ''){
                 showToast('请填写货号', 'info');
                return false;
            }
            else{
                $.mobile.loading('show');
                $.post("${ctx}/stock/stock/data", {articleno:$('#articlenos').val(),brandname:$('#brandnames').val()}, function(data){
                    $.mobile.loading( "hide" );
                    $('#data').html(data);
                });
            }
            return false;
        });
    });
</script>

</body>
</html>