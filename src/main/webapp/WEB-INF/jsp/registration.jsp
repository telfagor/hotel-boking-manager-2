<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Registration</title>
    <style>
        .color-message {
            color: red;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<h1><fmt:message key="page.registration.title"/></h1>
<form action="${pageContext.request.contextPath}/registration" method="post" enctype="application/x-www-form-urlencoded">
    <label for="first_name"><fmt:message key="page.registration.first.name"/>:</label>
    <input type="text" name="first_name" id="first_name">
    <br>
    <label for="last_name"><fmt:message key="page.registration.last.name"/>:</label>
    <input type="text" name="last_name" id="last_name">
    <br>
    <label for="email"><fmt:message key="page.registration.email"/>:</label>
    <input type="email" name="email" id="email">
    <br>
    <label for="password"><fmt:message key="page.registration.password"/>:</label>
    <input type="password" name="password" id="password">
    <br>
    <label><fmt:message key="page.registration.gender"/>:
        <c:forEach var="gender" items="${requestScope.genders}">
            <input type="radio" name="gender" value="${gender}"> ${gender}
        </c:forEach>
    </label>
    <br>
    <button type="submit"><fmt:message key="page.registration.submit.button"/></button>
    <a href="${pageContext.request.contextPath}/login">
        <button type="button"><fmt:message key="page.registration.login.button"/></button>
    </a>
</form>

<c:if test="${not empty requestScope.errors}">
    <c:forEach var="error" items="${requestScope.errors}">
        <span class="color-message">${error.message}</span>
    </c:forEach>
</c:if>
</body>
</html>
