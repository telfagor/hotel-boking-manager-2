<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Personal account</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Your details!</h2>

<c:choose>
    <c:when test="${not empty requestScope.userDetail}">
        <form action="${pageContext.request.contextPath}/personalAccount" method="post" enctype="multipart/form-data">
            <label for="contact-number">Contact number</label>
            <input type="tel" name="contact-number" id="contact-number" value="${requestScope.userDetail.contactNumber}">
            <br>
            <label for="photo">Photo:</label>
            <img src="${pageContext.request.contextPath}/images/${requestScope.userDetail.photo}" width="300px" alt="You does not have a image">
            <br>
            <input type="file" name="photo" id="photo">
            <br>
            <label for="birthdate">Birthdate:</label>
            <input type="date" name="birthdate" id="birthdate" value="${requestScope.userDetail.birthdate}">
            <br>
            <label for="money">Money:</label>
            <input type="number" name="money" id="money" value="${requestScope.userDetail.money}">
            <br>
            <button type="submit">Edit</button>
        </form>
    </c:when>
    <c:otherwise>
        <p>You did not indicate the details!</p>
    </c:otherwise>
</c:choose>

<%@ include file="toApartmentButton.jsp" %>

<c:if test="${not empty requestScope.errors}">
    <c:forEach var="error" items="${requestScope.errors}">
        <p class="ui-state-error-text">${error.message}</p>
    </c:forEach>
</c:if>
</body>
</html>
