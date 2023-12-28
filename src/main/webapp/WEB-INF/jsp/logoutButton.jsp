<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/logout" method="post" enctype="application/x-www-form-urlencoded">
    <button type="submit">Logout</button>
</form>

<fmt:setLocale value="${sessionScope.lang ne null ? sessionScope.lang : (param.lang ne null ? param.lang : 'en-US')}"/>
<fmt:setBundle basename="translations"/>
</body>
</html>

