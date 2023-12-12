<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your Orders</title>
</head>
<body>
<%@ include file="header.jsp" %>
    <h2>Your orders</h2>
    <ul>
        <c:forEach var="item" items="${requestScope.orders}">
            <li>${item}</li>
        </c:forEach>
    </ul>

<br>
<a href="${pageContext.request.contextPath}/apartment">To Apartments</a>

<%--<form action="${pageContext.request.contextPath}/download">
    <button type="button">Download</button>
</form>--%>

<a href="${pageContext.request.contextPath}/download">
    <button type="button">Download</button>
</a>
</body>
</html>
