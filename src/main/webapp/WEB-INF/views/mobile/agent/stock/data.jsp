<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
       &lt;%&ndash; <th>货号</th>&ndash;%&gt;
        <th>品牌</th>
        <th>尺码</th>
        <th>性别</th>
        <th>库存</th>
        <th>市场价</th>
        <th>折扣</th>
        <th>价格</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="stock">
        <tr>
           &lt;%&ndash; <td>
                    ${stock.articleno}
            </td>&ndash;%&gt;

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
</table>--%>



<div class="grid demo-grid">
   <%-- <div class="col-1">articleno</div>--%>
    <div class="col-1">品牌</div>
    <div class="col-1">尺码</div>
    <div class="col-1">性别</div>
    <div class="col-1">库存</div>
    <div class="col-1">市场</div>
    <div class="col-1">折扣</div>
</div>
<c:forEach items="${page.list}" var="stock">
    <div class="grid ">
       <%-- <div class="col-1">${stock.articleno}</div>--%>
        <div class="col-1">${stock.brandname}</div>
        <div class="col-1">${stock.size}</div>
        <div class="col-1">${stock.sex}</div>
        <div class="col-1">${stock.innernum}</div>
        <div class="col-1">${stock.marketprice}</div>
        <div class="col-1">${stock.discount}</div>

    </div>
</c:forEach>
