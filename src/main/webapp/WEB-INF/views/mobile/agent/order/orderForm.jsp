<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section id="ordersave_section" class="active">
    <header>
        <nav class="left">
            <a href="#index_section?index" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">订单</h1>
    </header>
    <article data-scroll="true" id="ordersave_article">
        <div class="indented">
            <form id="orderForm"   method="post">
                <input id="otoken" name="otoken" type="hidden" value="${otoken}"/>

                <div>&nbsp;</div>
                <div class="input-group">
                    <div class="input-row">
                        <label for="articleno">货号</label>
                        <input type="text" name="articleno" id="articleno" placeholder="请填写货号">
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
                        <input type="text" name="phone" id="phone" placeholder="请填写联系电话">
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
                        <input type="text" name="remarks" id="remarks" placeholder="请填写备注">
                    </div>
                </div>
                <div>&nbsp;</div>
                <button   class="submit block" data-icon="key">确认下单</button>

            </form>
        </div>
    </article>
</section>
<script type="text/javascript">

    $('body').delegate('#ordersave_section','pageinit',function(){
        $("#orderForm").submit(function(){
            if ($('#articleno').val() == ''){
                J.showToast('请填写货号', 'info');
            }
            else if ($('#num').val() == ''){
                J.showToast('请填写数量', 'info');
            }
            else if ($('#size').val() == ''){
                J.showToast('请填写尺码', 'info');
            }
            else if ($('#sex').val() == ''){
                J.showToast('请填写性别', 'info');
            }
            else if ($('#courier').val() == ''){
                J.showToast('请填写快递', 'info');
            }else if ($('#phone').val() == ''){
                J.showToast('请填写联系电话', 'info');
            }
            else if ($('#address').val() == ''){
                J.showToast('请填写联系地址', 'info');
            }
            else{
                J.showMask();
                var loginForm = $("#orderForm");
                $.post(ctx+"/order/qfsave", loginForm.serializeArray(), function(data){
                    J.hideMask();
                    if(data.status==0){
                        $("#orderForm").reset();
                        J.showToast('下单成功！联系商务客服进行支付和发货', 'success',0);
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

        $('#ordersave_article').addClass('active');

    });
</script>
