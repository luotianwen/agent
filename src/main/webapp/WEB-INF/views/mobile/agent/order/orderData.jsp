<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>货号</th>
        <th>品牌</th>
        <th>尺码</th>
        <th>性别</th>
        <th>库存数量</th>
        <th>市场价</th>
        <th>折扣</th>
        <th>销售价</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="stock">
        <tr>
            <td>
                    ${stock.articleno}
            </td>

            <td>
                    ${stock.brandname}
            </td>

            <td>
                    ${stock.size}
            </td>
            <td>
                    ${stock.sex}
            </td>
            <td>
                    ${stock.innernum}
            </td>
            <td>
                    ${stock.marketprice}
            </td>
            <td>
                    ${stock.discount}
            </td>
            <td>
                    ${stock.price}
            </td>


        </tr>
    </c:forEach>
    </tbody>
</table>

