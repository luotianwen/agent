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
                    <label for="articlenos">货号*</label>
                    <input type="text" name="articlenos" id="articlenos"  value="${stock.articleno}" placeholder="请填写货号">
                </div>
                <div class="input-row">
                    <label for="brandnames">品牌</label>
                    <input type="text" name="brandnames" id="brandnames"  value="${stock.brandname}" placeholder="请填写品牌">
                </div>
            </div>
            <button id="btn" class="submit block" data-icon="key">查询</button>
        </form>
        <div style="padding: 2px;" id="data">



        </div>
    </article>
    <script type="text/javascript">
        $('body').delegate('#stock_section','pageinit',function(){
            $("#stockForm").submit(function(){
                if ($('#articlenos').val() == ''){
                    J.showToast('请填写货号', 'info');
                    return false;
                }
                else{
                    J.showMask();
                    $.post("${ctx}/stock/stock/data", {articleno:$('#articlenos').val(),brandname:$('#brandnames').val()}, function(data){
                      J.hideMask();
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
