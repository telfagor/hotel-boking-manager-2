<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>All Users</title>
</head>
<body>

<%@ include file="header.jsp" %>

<a href="${pageContext.request.contextPath}/allUsers?pageSize=1&pageNumber=${(empty param.pageNumber) ? 1 : param.pageNumber + 1}">
    <button type="button"><fmt:message key="page.button.next"/></button>
</a>

<ul>
    <c:choose>
        <c:when test="${not empty requestScope.users}">
            <c:forEach var="user" items="${requestScope.users}">
                <li>
                    <a href="${pageContext.request.contextPath}/userOrders?userId=${user.id}">
                            ${user}
                    </a>
                </li>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="page.all.users.page"/></p>
        </c:otherwise>
    </c:choose>

</ul>

<%@ include file="toApartmentButton.jsp" %>
</body>
</html>
