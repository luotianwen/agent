<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section id="ordersave_section" class="active">
    <header>
        <nav class="left">
            <a href="#index_section?index" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">订单</h1>
    </header>
    <article data-scroll="true" id="agent_article">
        <div class="indented">
            <form id="orderForm"   method="post">
                <input id="otoken" name="otoken" type="hidden" value="${token}"/>

                <div>&nbsp;</div>
                <div class="input-group">
                    <div class="input-row">
                        <label for="name">货号</label>
                        <input type="text" name="name" id="name" placeholder="请填写名称">
                    </div>
                    <div class="input-row">
                        <label for="num">数量</label>
                        <input type="text" name="num" id="num" placeholder="请填写数量">
                    </div>
                    <div class="input-row">
                        <label for="size">尺码</label>
                        <input type="text" name="size" id="size" placeholder="请填写尺码">
                    </div>
                    <div class="input-row">
                        <label for="sex">性别</label>
                        <input type="text" name="sex" id="sex" placeholder="请填写性别">
                    </div>
                    <div class="input-row">
                        <label for="phone">联系电话</label>
                        <input type="phone" name="phone" id="phone" placeholder="请填写联系电话">
                    </div>
                    <div class="input-row">
                        <label for="address">联系地址</label>
                        <input type="text" name="address" id="address" placeholder="请填写联系地址">
                    </div>
                    <div class="input-row">
                        <label for="courier">快递</label>
                        <input type="text" name="courier" id="courier" placeholder="请填写快递">
                    </div>
                    <div class="input-row">
                        <label for="remarks">备注</label>
                        <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
                    </div>

                </div>
                <div>&nbsp;</div>
                <button   class="submit block" data-icon="key">确认</button>

            </form>
        </div>
    </article>
</section>
<script type="text/javascript">

    $('body').delegate('#ordersave_section','pageinit',function(){
        $("#orderForm").submit(function(){
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
                var loginForm = $("#orderForm");
                $.post(ctx+"/order/order/qfsave", loginForm.serializeArray(), function(data){
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
    $('body').delegate('#ordersave_section','pageshow',function(){

        $('#agent_article').addClass('active');

    });
</script>
