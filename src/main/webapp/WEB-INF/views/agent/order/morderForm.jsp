<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<%@ include file="/WEB-INF/views/include/mhead.jsp"%><!DOCTYPE >
<body>
<ul class="nav nav-tabs">
    <li ><a href="${ctx}/order/query">订单列表</a></li>
    <li class="active"><a href="${ctx}/order/qf">下单</a></li>
</ul>
<div data-role="page">

    <div data-role="main" class="ui-content">
        <form id="orderForm"   method="post">
            <input id="otoken" name="otoken" type="hidden" value="${otoken}"/>
            <label for="articleno">货号</label>
            <input type="text" name="articleno" id="articleno" placeholder="请填写货号">
            <label for="num">数量</label>
            <input type="text" name="num" id="num" placeholder="请填写数量">

            <label for="size">尺码</label>
            <input type="text" name="size" id="size" placeholder="请填写尺码">
            <label for="sex">性别</label>
            <input type="text" name="sex" id="sex" placeholder="请填写性别">
            <label for="phone">联系电话</label>
            <input type="text" name="phone" id="phone" placeholder="请填写联系电话">
            <label for="address">联系地址</label>
            <input type="text" name="address" id="address" placeholder="请填写联系地址">
            <label for="courier">快递</label>
            <input type="text" name="courier" id="courier" placeholder="请填写快递">
            <label for="remarks">备注</label>
            <input type="text" name="remarks" id="remarks" placeholder="请填写备注">

            <button type="submit"  class="ui-icon-user">确认下单</button>
        </form>
        <div data-role="popup" id="popupBasic">
            <p id="content">ss</p>
        </div>
    </div>


</div>
<script type="text/javascript">

    $(function () {
        $("#orderForm").submit(function(){
            if ($('#articleno').val() == ''){
                showToast('请填写货号', 'info');
            }
            else if ($('#num').val() == ''){
                showToast('请填写数量', 'info');
            }
            else if ($('#size').val() == ''){
                showToast('请填写尺码', 'info');
            }
            else if ($('#sex').val() == ''){
                showToast('请填写性别', 'info');
            }
            else if ($('#courier').val() == ''){
                showToast('请填写快递', 'info');
            }else if ($('#phone').val() == ''){
                showToast('请填写联系电话', 'info');
            }
            else if ($('#address').val() == ''){
                showToast('请填写联系地址', 'info');
            }
            else{
                $.mobile.loading('show');
                var loginForm = $("#orderForm");
                $.post(ctx+"/order/qfsave", loginForm.serializeArray(), function(data){
                    $.mobile.loading( "hide" );
                    if(data.status==0){
                        $("#orderForm")[0].reset();
                        showToast('下单成功！联系商务客服进行支付和发货', 'success',0);
                    }
                    else{
                        showToast(data.message, 'error');
                    }

                });
            }
            return false;
        });
    });
</script>

</body>
</html>