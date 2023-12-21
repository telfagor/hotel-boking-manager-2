<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Welcome to our hotel!</h1><%--<fmt:message key="page.header.title"/>--%>
<c:if test="${not empty sessionScope.user}">
    <form action="${pageContext.request.contextPath}/logout" method="post" enctype="application/x-www-form-urlencoded">
        <button type="submit">Logout</button>
    </form>
</c:if>

<div>
    <form action="${pageContext.request.contextPath}/locale" method="post" >
        <button type="submit" name="lang" value="ru_RU">RU</button>
        <button type="submit" name="lang" value="en_US">EN</button>
    </form>
</div>

<fmt:setLocale value="${sessionScope.lang ne null ? sessionScope.lang : (param.lang ne null ? param.lang : 'en-US')}"/>
<fmt:setBundle basename="translations"/>
</body>
</html>
