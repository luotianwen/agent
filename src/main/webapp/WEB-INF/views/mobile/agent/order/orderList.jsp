<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="orderList_section">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">订单查询</h1>
    </header>
    <article class="active" data-scroll="true" id="orderList_article">
        <div style="padding: 10px 0 20px;">
            <ul class="list inset demo-list">

                <li data-icon="next" data-selected="selected">
                    <span class="icon phone"></span>
                    <a href="#orderquery_section" data-target="section">
                        <strong>订单查询</strong>
                    </a>

                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon book"></span>
                    <a href="#ordersave_section" data-target="section">
                        <strong>下单</strong>
                    </a>
                </li>

            </ul>
        </div>
    </article>
    <script type="text/javascript">
        $('body').delegate('#orderList_section','pageinit',function(){
        });
        $('body').delegate('#orderList_section','pageshow',function(){
            debugger;
            $('#orderList_article').addClass('active');
        });
    </script>
</section>