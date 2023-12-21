<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your orders</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/userOrders">
    <c:choose>
        <c:when test="${sessionScope.user.role eq 'ADMIN'}">
            <button type="button">Orders</button>
        </c:when>
        <c:otherwise>
            <button type="button">Your Orders</button>
        </c:otherwise>
    </c:choose>
</a>

</body>
</html>
