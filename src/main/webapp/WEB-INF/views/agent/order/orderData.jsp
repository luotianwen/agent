<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>

        <th>订单号</th>
        <th>货号</th>
        <th>快递单号</th>
        <th>订单状态</th>
        <th>金额</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.onumber} </td>
            <td>${order.articleno} </td>
            <td>${order.delivernumber} </td>
            <td>${fns:getDictLabel(order.state, 'a_order_state', '')} </td>
            <td>
                    ${order.discountmoney}   </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

