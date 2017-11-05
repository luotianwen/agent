<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<div class="ui-grid-d">
    <div class="ui-block-a">订单号</div>
    <div class="ui-block-b">货号</div>
    <div class="ui-block-c">快递单号</div>
    <div class="ui-block-d">状态</div>
    <div class="ui-block-e">金额</div>
</div>
<c:forEach items="${orders}" var="order">
    <div class="ui-grid-d">
        <div class="ui-block-a" style="word-break:break-all;word-wrap:break-word; ">${order.onumber}</div>
        <div class="ui-block-b" style="word-break:break-all;word-wrap:break-word; ">${order.articleno}</div>
        <div class="ui-block-c" style="word-break:break-all;word-wrap:break-word; ">${order.delivernumber}</div>
        <div class="ui-block-d">${fns:getDictLabel(order.state, 'a_order_state', '')}</div>
        <div class="ui-block-e">${order.discountmoney}</div>
    </div>
</c:forEach>

