<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

	<section id="agent_section" class="active">
		<header>
			<nav class="left">
				<a href="#index_section?index" data-icon="previous" data-target="back">返回</a>
			</nav>
			<h1 class="title">代理申请</h1>
		</header>
		<article data-scroll="true" id="agent_article">
			<div class="indented">
				<form id="agentForm"   method="post">
					<input id="token" name="token" type="hidden" value="${token}"/>

					<div>&nbsp;</div>
					<div class="input-group">
						<div class="input-row">
							<label for="name">名称</label>
							<input type="text" name="name" id="name" placeholder="请填写名称">
						</div>
						<div class="input-row">
							<label>性别</label>
							<div class="toggle active" name="sex" id="sex" data-on="男"  data-off="女" ></div>
						</div>
						<div class="input-row">
							<label for="phone">联系电话</label>
							<input type="phone" name="phone" id="phone" placeholder="请填写联系电话">
						</div>
						<div class="input-row">
							<label for="mobile">联系手机</label>
							<input type="phone" name="mobile" id="mobile" placeholder="请填写联系手机">
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
					<button   class="submit block" data-icon="key">申请</button>
					<div>&nbsp;</div>
					<input id="agentbtn" type="button" class="button block" data-icon="cogs" value="查询结果">
					<%--<button id="btn2" class="button block" data-icon="cogs">查询结果</button>--%>
				</form>
			</div>
		</article>
	</section>
<script type="text/javascript">

    $("#agentbtn").click(function(){
        var mobile=$('#mobile').val();
        var phone=$('#phone').val();

        if (phone== ''&&mobile == ''){
            J.showToast('请填写联系电话或者联系手机', 'info');
        }
        else{
            $.post("/agent/query", {"phone":phone,"mobile":mobile}, function(data){
                J.showToast(data.message, 'info');
            });
        }
    });
    $('body').delegate('#agent_section','pageinit',function(){
        $("#agentForm").submit(function(){
                if ($('#name').val() == '') {
                    J.showToast('请填写名称', 'info');
                } else if ($('#phone').val() == '') {
                    J.showToast('请填写联系电话', 'info');
                }
                else if ($('#mobile').val() == '') {
                    J.showToast('请填写联系手机', 'info');
                }

                else if ($('#address').val() == '') {
                    J.showToast('请填写联系地址', 'info');
                }
                else if ($('#apay').val() == '') {
                    J.showToast('请填写支付宝', 'info');
                }
                else if ($('#weixin').val() == '') {
                    J.showToast('请填写微信', 'info');
                }
                else if ($('#email').val() == '') {
                    J.showToast('请填写邮箱', 'info');
                }
                else {
                    var loginForm = $("#agentForm");
                    J.showMask();
                    $.post("/agent/saveadd", loginForm.serializeArray(), function (data) {
                        J.hideMask();
                        if (data.status == 0) {
                            J.showToast('保存成功！', 'success');
                        }
                        else {
                            J.showToast(data.message, 'error');
                        }

                    });
                }
            return false;
        });
    });
    $('body').delegate('#agent_section','pageshow',function(){

        $('#agent_article').addClass('active');

    });
</script>
