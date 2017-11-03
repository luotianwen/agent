<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="orderquery_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">订单查询</h1>
    </header>


    <article class="active" data-scroll="true" id="orderquery_article">
        <form id="orderqueryForm"   >
            <div class="input-group">
                <div class="input-row">
                    <label for="onumbers">订单号</label>
                    <input type="text" name="onumbers" id="onumbers"  value="${order.onumber}" placeholder="请填写订单号">
                </div>
            </div>
            <button id="btn" class="submit block" data-icon="key">查询</button>
        </form>
        <div style="padding: 2px;" id="orderdata">



        </div>
    </article>
    <script type="text/javascript">
        $('body').delegate('#orderquery_section','pageinit',function(){
            $("#orderqueryForm").submit(function(){
                J.showMask();
                var orderForm = $("#orderqueryForm");
                $.post("${ctx}/order/data", {onumber:$("#onumbers").val()}, function(data){
                    J.hideMask();
                    $('#orderdata').html(data);
                });
                return false;
            });
        });
        $('body').delegate('#orderquery_section','pageshow',function(){
            $('#orderquery_article').addClass('active');
        });
    </script>
</section>
