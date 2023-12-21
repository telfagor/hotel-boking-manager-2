<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Login</title>
    <style>
        .error-message {
            color: red;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/login" method="post" enctype="application/x-www-form-urlencoded">
    <label for="email"><fmt:message key="page.login.email"/>:</label>
    <input type="email" name="email" id="email" value="${param.email}" required>
    <br>
    <label for="password"><fmt:message key="page.login.password"/>:</label>
    <input type="password" name="password" id="password" required>
    <br>
    <button type="submit"><fmt:message key="page.login.submit.button"/></button>
</form>
<div>
    <p><fmt:message key="page.login.registration.link"/>
        <a href="${pageContext.request.contextPath}/registration">
            <button type="button"><fmt:message key="page.login.registration.button"/></button></a>
    </p>
    <div>
        <span>
            <c:if test="${param.error != null}">
                <p class="error-message"><fmt:message key="page.login.error.message"/></p>
            </c:if>
        </span>
    </div>
</div>
</body>
</html>
