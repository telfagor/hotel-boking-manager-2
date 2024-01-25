<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Create order</title>

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

<%@include file="header.jsp" %>
<h2><fmt:message key="page.user.detail.title"/></h2>

<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <%@ include file="addApartmentButton.jsp" %>
</c:if>

<form action="${pageContext.request.contextPath}/userDetail" method="post" enctype="multipart/form-data">
    <label for="tel"><fmt:message key="page.user.detail.telephone"/>:</label>
    <input type="tel" name="telephone" id="tel" required>
    <br><br>
    <label for="photo"><fmt:message key="page.user.detail.photo"/></label>
    <input type="file" name="photo" id="photo">
    <br><br>
    <label for="birthdate"><fmt:message key="page.user.detail.birthdate"/>:</label>
    <input type="date" name="birthdate" id="birthdate" required>
    <br><br>
    <label for="amount"><fmt:message key="page.user.detail.amount"/>:</label>
    <input type="number" name="amount" min="0" value="0" id="amount">
    <br><br>
    <button type="submit"><fmt:message key="page.user.add.details"/></button>
</form>

<c:if test="${not empty requestScope.errors}">
    <c:forEach var="item" items="${requestScope.errors}">
        <div class="error">
            <p>${item.message}</p>
        </div>
    </c:forEach>
</c:if>
<%@ include file="toApartmentButton.jsp" %>
</body>
</html>
