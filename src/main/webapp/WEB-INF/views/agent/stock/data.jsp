<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr><th>品牌</th>
        <th>尺码/性别</th>
        <th>库存</th>
        <th>市场</th>
        <th>折扣</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="stock">
        <tr>
            <td>${stock.brandname} </td>
            <td>${stock.size}/${stock.sex} </td>
            <td>${stock.innernum} </td>
            <td>${stock.marketprice} </td>
            <td>
                <fmt:formatNumber type="number" value="${stock.discount}" pattern="0.00" maxFractionDigits="2"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
