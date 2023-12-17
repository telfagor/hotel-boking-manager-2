<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set Order Status</title>
</head>
<body>
<h1>Hello</h1>
<form action="${pageContext.request.contextPath}/setOrderStatus" method="post" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="orderId" value="${param.orderId}">
    <button value="approved" name="status" type="submit">APPROVED</button>
    <button value="rejected" name="status" type="submit">REJECTED</button>
</form>
</body>
</html>
