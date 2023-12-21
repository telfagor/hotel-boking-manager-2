<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your Orders</title>
</head>
<body>
<%@ include file="header.jsp" %>
<a href="${pageContext.request.contextPath}/download">
    <button type="button">Download</button>
</a>

<h2>Your orders</h2>
<ul>
    <c:choose>
        <c:when test="${sessionScope.user.role eq 'ADMIN'}">
            <c:forEach var="order" items="${requestScope.orders}">
                <li>
                    <a href="${pageContext.request.contextPath}/setOrderStatus?orderId=${order.orderId}">${order}</a>
                </li>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:forEach var="order" items="${requestScope.orders}">
                <li>${order}</li>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</ul>

<br>
<%@ include file="toApartmentButton.jsp" %>
</body>
</html>
