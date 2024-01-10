<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Detail Button</title>
    <style>
        .personal-account {
            display: block;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <a class="personal-account" href="${requestScope.request.contextPath}/personalAccount">
        <button type="submit">Your Details</button>
    </a>
<br>
</body>
</html>
