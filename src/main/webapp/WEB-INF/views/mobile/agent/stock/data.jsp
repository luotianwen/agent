<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>




<div class="ui-grid-d">
   <%-- <div class="col-1">articleno</div>--%>
    <div class="ui-block-a">品牌</div>
    <div class="ui-block-b">尺码/性别</div>
    <div class="ui-block-c">库存</div>
    <div class="ui-block-d">市场</div>
    <div class="ui-block-e">折扣</div>

</div>
<c:forEach items="${page.list}" var="stock">
    <div class="ui-grid-d ">
       <%-- <div class="col-1">${stock.articleno}</div>--%>
        <div class="ui-block-a">${stock.brandname}</div>
        <div class="ui-block-b">${stock.size}/${stock.sex}</div>
        <div class="ui-block-c">${stock.innernum}</div>
        <div class="ui-block-d">${stock.marketprice}</div>
        <div class="ui-block-e"><fmt:formatNumber type="number" value="${stock.discount}" pattern="0.00" maxFractionDigits="2"/></div>

    </div>
</c:forEach>
