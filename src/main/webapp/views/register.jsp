<%--
  Created by IntelliJ IDEA.
  User: algart19
  Date: 20/3/23
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <style>
        <%@include file="/resources/style.css"%>
    </style>
    <title>Bootstrap Example</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="first">
    <form class="myform" action="" method="post">
        <div class="mb-3">
            <label for="exampleInputName" class="form-label">Full name</label>
            <input type="text" name="fname" required class="form-control" id="exampleInputName" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label><br>
            <input type="email" name="email" required class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" name="password" required class="form-control" id="exampleInputPassword1">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword2" class="form-label">Password</label>
            <input type="password" name="password2" required class="form-control" id="exampleInputPassword2">
        </div>
        <a href="${pageContext.request.contextPath}/login">Click here to login</a><br><br>
        <input type="submit" value="Register" class="btn btn-primary ">
    </form>
</div>

</body>
</html>
