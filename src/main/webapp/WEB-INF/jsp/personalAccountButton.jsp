<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Detail Button</title>
    <style>
        a {
            display: inline-block;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
    <a href="${requestScope.request.contextPath}/personalAccount">
        <button type="submit">Your Details</button>
    </a>
<br>
</body>
</html>
