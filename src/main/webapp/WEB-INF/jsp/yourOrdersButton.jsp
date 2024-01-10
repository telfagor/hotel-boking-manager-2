<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your orders</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/userOrders?userId=${sessionScope.user.id}">
    <button type="button">Your Orders</button>
</a>

</body>
</html>
