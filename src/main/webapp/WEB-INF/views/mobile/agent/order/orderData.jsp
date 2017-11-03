<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="grid demo-grid">
    <div class="col-1">订单号</div>
    <div class="col-1">货号</div>
    <div class="col-1">快递单号</div>
    <div class="col-1">状态</div>
    <div class="col-1">金额</div>
</div>
<c:forEach items="${orders}" var="order">
    <div class="grid ">
        <div class="col-1">${order.onumber}</div>
        <div class="col-1">${order.articleno}</div>
        <div class="col-1">${order.delivernumber}</div>
        <div class="col-1">${fns:getDictLabel(order.state, 'a_order_state', '')}</div>
        <div class="col-1">${order.discountmoney}</div>
    </div>
</c:forEach>

