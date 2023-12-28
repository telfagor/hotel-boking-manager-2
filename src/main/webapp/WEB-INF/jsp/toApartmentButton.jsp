<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/apartment">
        <button type="button"><fmt:message key="page.to.apartment.button"/></button>
    </a>
</div>

<fmt:setLocale value="${sessionScope.lang ne null ? sessionScope.lang : (param.lang ne null ? param.lang : 'en-US')}"/>
<fmt:setBundle basename="translations"/>
</body>
</html>
