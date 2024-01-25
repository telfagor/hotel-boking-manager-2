<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <style>
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>

<h2>Add apartment</h2>
<form action="${pageContext.request.contextPath}/addApartment" method="post" enctype="multipart/form-data">
    <label for="number_of_rooms"><fmt:message key="page.apartment.rooms"/>:</label>
    <input type="number" name="number_of_rooms" id="number_of_rooms">
    <br><br>
    <label for="number_of_seats"><fmt:message key="page.apartment.seats"/>:</label>
    <input type="number" name="number_of_seats" id="number_of_seats">
    <br><br>
    <label for="price_per_hour"><fmt:message key="page.apartment.hour.price"/>:</label>
    <input type="number" name="price_per_hour" id="price_per_hour">
    <br><br>
    <label for="photo"><fmt:message key="page.add.apartment.photo"/>:</label>
    <input type="file" name="photo" id="photo">
    <br><br>
    <label for="type"><fmt:message key="page.apartment.type"/>:</label>
    <select name="type" id="type">
        <c:forEach var="type" items="${requestScope.types}">
            <option value="${type}">${type}</option>
        </c:forEach>
    </select>
    <br><br>
    <button type="submit"><fmt:message key="page.registration.submit.button"/></button>
</form>

<c:forEach var="error" items="${requestScope.errors}">
    <p class="error-message">${error.message}</p>
</c:forEach>

<%@ include file="toApartmentButton.jsp" %>
</body>
</html>
