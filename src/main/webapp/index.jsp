<%--
  Created by IntelliJ IDEA.
  User: algart19
  Date: 29/3/23
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <style>
        html, body {
            height: 100%;
            width: 100%;
            display: flex;
            align-items: center;
            justify-content: space-evenly;
        }
        .btn-custom {
            padding: 20px 30px;
            font-size: 50px;
        }
    </style>
</head>
<body>
        <a class="btn btn-primary btn-custom" href="${pageContext.request.contextPath}/login">Login</a>
        <a class="btn btn-primary btn-custom" href="${pageContext.request.contextPath}/register">Register</a>

</body>
</html>
