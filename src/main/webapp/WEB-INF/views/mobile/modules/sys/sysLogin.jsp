<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
	<%@ include file="/WEB-INF/views/include/mhead.jsp"%><!DOCTYPE >
<body>
<div data-role="page">
	<div data-role="header">
		<h1>登录</h1>
	</div>
	<div data-role="main" class="ui-content">
		<form  id="loginForm"  method="post">
			<label for="username">账号</label>
			<input type="text" name="username" id="username" placeholder="请填写登录账号">
			<label for="password">密码</label>
			<input type="password" name="password" id="password" placeholder="请填写登录密码">
			<div  id="validateCodeDiv" style="display:none;">
			<label class="input-label mid" for="validateCode">验证码</label>
			<sys:validateCode name="validateCode" />
			</div>
			<input type="hidden" name="mobileLogin" value="true">

			<input type="submit"   value="登录">

			<a  id="btn2" data-role="button" data-transition="slide">
				<strong>申请代理</strong>
			</a>

		</form>
		<div data-role="popup" id="popupBasic">
			<p id="content">ss</p>
		</div>
	</div>


</div>
<script type="text/javascript">
    var sessionid = '${not empty fns:getPrincipal() ? fns:getPrincipal().sessionid : ""}';
    if (sessionid != ''){
        window.location=ctx+'?index';
    }
    $(function () {
        $("#btn2").on('click',function () {
            window.location= '/agent/formadd';
        });

        $("#loginForm").submit(function(){
            if ($('#username').val() == ''){
                $("#content").html("请填写账号");
                $("#popupBasic").popup('open');
            }else if ($('#password').val() == ''){

                $("#content").html("请填写密码");
                $("#popupBasic").popup('open');
            }else if ($('#validateCodeDiv').is(':visible') && $('#validateCode').val() == ''){
                $("#content").html("请填写验证码");
                $("#popupBasic").popup('open');
            }else{
                var loginForm = $("#loginForm");
                $.mobile.loading('show');
                $.post("${ctx}/login", loginForm.serializeArray(), function(data){
                    $.mobile.loading( "hide" );
                    if (data && data.sessionid){
                        sessionid = data.sessionid;
                        window.location=ctx+'?index';
                    }else{
                        $("#content").html(data.message);
                        $("#popupBasic").popup('open');
                        if (data.shiroLoginFailure == 'org.apache.shiro.authc.AuthenticationException'){
                            $('#validateCodeDiv').show();
                        }
                        $('#validateCodeDiv a').click();
                    }

                });
            }
            return false;
        });
    });
</script>

</body>
</html>