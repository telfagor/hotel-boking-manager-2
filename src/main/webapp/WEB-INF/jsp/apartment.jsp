<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Apartments</title>
    <style>
        .apartment-description {
            list-style-type: none;
            margin-bottom: 20px;
        }

        .btn {
            display: inline-block;
            margin: 40px;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="yourOrdersButton.jsp" %>

<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <%@ include file="addApartmentButton.jsp" %>
    <%@ include file="allUsersButton.jsp" %>
</c:if>

<h2><fmt:message key="page.apartment.title"/></h2>
<ul>
    <c:forEach var="apartment" items="${requestScope.apartments}">
        <c:if test="${apartment.status != 'OCCUPIED'}">
            <li>
                <ul class="apartment-description">
                    <li><img width="300" src="${pageContext.request.contextPath}/images/${apartment.photo}"
                             alt="apartment"></li>
                    <li><fmt:message key="page.apartment.id"/>: ${apartment.id}</li>
                    <li><fmt:message key="page.apartment.rooms"/>: ${apartment.numberOfRooms}</li>
                    <li><fmt:message key="page.apartment.seats"/>: ${apartment.numberOfSeats}</li>
                    <li><fmt:message key="page.apartment.hour.price"/>: ${apartment.pricePerHour}</li>
                    <li><fmt:message key="page.apartment.status"/>: ${apartment.status}</li>
                    <li><fmt:message key="page.apartment.type"/>: ${apartment.type}</li>
                </ul>
            </li>
        </c:if>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}/login">
    <button class="btn" type="button"><fmt:message key="page.apartment.back.button"/></button>
</a>

<c:choose>
    <c:when test="${sessionScope.user.userDetail ne null}">
        <a href="${pageContext.request.contextPath}/order">
            <button class="btn" type="button"><fmt:message key="page.apartment.order.create"/></button>
        </a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/userDetail">
            <button class="btn" type="button"><fmt:message key="page.apartment.order.create"/></button>
        </a>
    </c:otherwise>
</c:choose>
</body>
</html>
