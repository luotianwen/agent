<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section id="stock_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">库存查询</h1>
    </header>


    <article class="active" data-scroll="true" id="stock_article">
        <form id="stockForm"   >
            <div class="input-group">
                <div class="input-row">
                    <label for="articleno">账号</label>
                    <input type="text" name="articleno" id="articleno"  value="${stock.articleno}" placeholder="请填写账号">
                </div>
            </div>
            <button id="btn" class="submit block" data-icon="key">query</button>
        </form>
        <div style="padding: 2px;" id="data">



        </div>
    </article>
    <script type="text/javascript">
        $('body').delegate('#stock_section','pageinit',function(){
            $("#stockForm").submit(function(){
                if ($('#articleno').val() == ''){
                    J.showToast('请填写账号', 'info');
                    return false;
                }
                else{
                    var stockForm = $("#stockForm");
                    $.post("${ctx}/stock/stock/data", stockForm.serializeArray(), function(data){
                        $('#data').html(data);
                    });
                }
                return false;
            });
        });
        $('body').delegate('#stock_section','pageshow',function(){
            $('#stock_article').addClass('active');
        });
    </script>
</section>
