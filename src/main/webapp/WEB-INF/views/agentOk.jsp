<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>代理结果</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
 请等待管理员(1-3天)审核！审核通过会以短信通知为准！
</br>
<sys:message content="${message}" />
</body>
</html>