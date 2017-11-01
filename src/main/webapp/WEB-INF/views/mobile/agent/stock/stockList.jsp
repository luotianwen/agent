<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="stock_section">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">库存查询</h1>
    </header>
    <article class="active">
        <div style="line-height:50px;padding:10px;">
            stock stock
        </div>
    </article>
    <script type="text/javascript">
        $('body').delegate('#stock_section','pageinit',function(){
        });
        $('body').delegate('#stock_section','pageshow',function(){
            var hash = J.Util.parseHash(location.hash);
            console.log(hash.param);
        });
    </script>
</section>