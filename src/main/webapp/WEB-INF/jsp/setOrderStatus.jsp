<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set Order Status</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/setOrderStatus" method="post" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="orderId" value="${param.orderId}">
    <input type="hidden" name="userId" value="${param.userId}">
    <button value="approved" name="status" type="submit"><fmt:message key="page.order.set.status.approved"/></button>
    <button value="rejected" name="status" type="submit"><fmt:message key="page.order.set.status.rejected"/></button>
</form>

<%@ include file="toApartmentButton.jsp" %>
</body>
</html>
