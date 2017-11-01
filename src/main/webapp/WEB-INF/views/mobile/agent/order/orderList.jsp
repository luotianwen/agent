<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="order_section">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">订单查询</h1>
    </header>
    <article class="active">
        <div style="line-height:50px;padding:10px;">
            order order
        </div>
    </article>
    <script type="text/javascript">
        $('body').delegate('#order_section','pageinit',function(){
        });
        $('body').delegate('#order_section','pageshow',function(){
            var hash = J.Util.parseHash(location.hash);
            console.log(hash.param);
        });
    </script>
</section>