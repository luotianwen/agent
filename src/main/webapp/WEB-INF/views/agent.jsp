<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<head>
	<meta charset="utf-8">
	<title>代理申请</title>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link rel="stylesheet" href="${ctxStatic}/jingle/css/Jingle.css">
	<link rel="stylesheet" href="${ctxStatic}/jingle/css/app.css">
</head>
<body>
<div id="aside_container">
</div>
<div id="section_container">
	<section id="login_section" class="active">
		<header>
			<h1 class="title">代理申请</h1>
		</header>
		<article data-scroll="true" id="login_article">
			<div class="indented">
				<form id="loginForm" action="/agent/saveadd" method="post">
					<input id="token" name="token" type="hidden" value="${token}"/>
					<input id="sex" name="sex" type="hidden" value="1"/>
					<div>&nbsp;</div>
					<div class="input-group">
						<div class="input-row">
							<label for="name">名称</label>
							<input type="text" name="name" id="name" placeholder="请填写名称">
						</div>
						<div class="input-row">
							<label for="phone">联系电话</label>
							<input type="text" name="phone" id="phone" placeholder="请填写联系电话">
						</div>
						<div class="input-row">
							<label for="mobile">联系手机</label>
							<input type="text" name="mobile" id="mobile" placeholder="请填写联系手机">
						</div>
						<div class="input-row">
							<label for="address">联系地址</label>
							<input type="text" name="address" id="address" placeholder="请填写联系地址">
						</div>
						<div class="input-row">
							<label for="apay">支付宝</label>
							<input type="text" name="apay" id="apay" placeholder="请填写支付宝">
						</div>

						<div class="input-row">
							<label for="weixin">微信</label>
							<input type="text" name="weixin" id="weixin" placeholder="请填写微信">
						</div>
						<div class="input-row">
							<label for="email">邮箱</label>
							<input type="email" name="email" id="email" placeholder="请填写邮箱">
						</div>
					</div>

					<div>&nbsp;</div>

					<button id="btn" class="submit block" data-icon="key">申请</button>
				</form>
			</div>
		</article>
	</section>
</div>
<!--<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/cordova.js"></script>-->
<!-- lib -->
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/zepto.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/iscroll.js"></script>
<%-- <script type="text/javascript" src="${ctxStatic}/jingle/js/lib/template.min.js"></script> --%>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/Jingle.debug.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/zepto.touch2mouse.js"></script>
<%-- <script type="text/javascript" src="${ctxStatic}/jingle/js/lib/JChart.debug.js"></script> --%>
<!--- app --->
<script type="text/javascript">var ctx = '${ctx}';</script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/app/app.js"></script>
<script type="text/javascript">

    $('body').delegate('#login_section','pageinit',function(){
        $("#loginForm").submit(function(){
            if ($('#name').val() == ''){
                J.showToast('请填写名称', 'info');
            }else if ($('#phone').val() == ''){
                J.showToast('请填写联系电话', 'info');
            }
            else if ($('#mobile').val() == ''){
                J.showToast('请填写联系手机', 'info');
            }

            else if ($('#address').val() == ''){
                J.showToast('请填写联系地址', 'info');
            }
            else if ($('#apay').val() == ''){
                J.showToast('请填写支付宝', 'info');
            }
            else if ($('#weixin').val() == ''){
                J.showToast('请填写微信', 'info');
            }
            else if ($('#email').val() == ''){
                J.showToast('请填写邮箱', 'info');
            }
            else{
                var loginForm = $("#loginForm");
                $.post(loginForm.attr('action'), loginForm.serializeArray(), function(data){
                    if(data.status==0){
                        J.showToast('保存成功！', 'success');
					}
					else{
                       J.showToast(data.message, 'error');
                    }

                });
            }
            return false;
        });
    });
    $('body').delegate('#login_section','pageshow',function(){

            $('#login_article').addClass('active');

    });
</script>
</body>
</html>
