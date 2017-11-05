<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<%@ include file="/WEB-INF/views/include/mhead.jsp"%><!DOCTYPE >
<body>

<div data-role="page">
	<div data-role="header">
		<%--<button class="ui-btn ui-shadow ui-corner-all ui-btn-icon-left ui-icon-back">back</button> --%>
    <a href="#index_section?index"  class="ui-btn ui-shadow ui-corner-all ui-btn-icon-left ui-icon-back" data-rel="back" >返回</a>

		<h1  >代理申请</h1>
	</div>
	<div data-role="main" class="ui-content">
		<form id="agentForm"   method="post">
			<input id="token" name="token" type="hidden" value="${token}"/>
			<label for="name">名称</label>
			<input type="text" name="name" id="name" placeholder="请填写名称">
			<label  for="sex">性别</label>
			<select name="sex" id="sex" data-role="slider">
				        <option value="true">男</option>
				    <option value="false">女</option>
			</select>

			<label for="phone">联系电话</label>
			<input type="tel" name="phone" id="phone" placeholder="请填写联系电话">
			<label for="mobile">联系手机</label>
			<input type="tel" name="mobile" id="mobile" placeholder="请填写联系手机">
			<label for="address">联系地址</label>
			<input type="text" name="address" id="address" placeholder="请填写联系地址">
			<label for="apay">支付宝</label>
			<input type="text" name="apay" id="apay" placeholder="请填写支付宝">
			<label for="weixin">微信</label>
			<input type="text" name="weixin" id="weixin" placeholder="请填写微信">
			<label for="email">邮箱</label>
			<input type="email" name="email" id="email" placeholder="请填写邮箱">
			<button type="submit"  class="ui-icon-user">申请</button>
			<input id="agentbtn" type="button" class="ui-icon-query" value="查询结果">
		</form>
		<div data-role="popup" id="popupBasic">
			<p id="content">ss</p>
		</div>
	</div>


</div>
<script type="text/javascript">

    $(function () {
        $("#agentbtn").on('click',function(){
            var mobile=$('#mobile').val();
            var phone=$('#phone').val();

            if (phone== ''&&mobile == ''){
                showToast("请填写联系电话或者联系手机");
            }
            else{
                $.mobile.loading('show');
                $.post("/agent/query", {"phone":phone,"mobile":mobile}, function(data){
                    $.mobile.loading( "hide" );
                    $("#content").html(data.message);
                    $("#popupBasic").popup('open');
                });
            }
        });

        $("#agentForm").submit(function(){
            if ($('#name').val() == '') {
                showToast('请填写名称', 'info');
            } else if ($('#phone').val() == '') {
                showToast('请填写联系电话', 'info');
            }
            else if ($('#mobile').val() == '') {
                showToast('请填写联系手机', 'info');
            }

            else if ($('#address').val() == '') {
                showToast('请填写联系地址', 'info');
            }
            else if ($('#apay').val() == '') {
                showToast('请填写支付宝', 'info');
            }
            else if ($('#weixin').val() == '') {
                showToast('请填写微信', 'info');
            }
            else if ($('#email').val() == '') {
                showToast('请填写邮箱', 'info');
            }
            else {
                var loginForm = $("#agentForm");
                $.mobile.loading('show');
                $.post("/agent/saveadd", loginForm.serializeArray(), function (data) {
                    $.mobile.loading( "hide" );
                    if (data.status == 0) {
                        showToast('保存成功！', 'success');
                    }
                    else {
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