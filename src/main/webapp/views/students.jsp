<%--
  Created by IntelliJ IDEA.
  User: algart19
  Date: 19/3/23
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@page import="service.model.Student" %>
<%@page import="web.StudentsController" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>

<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    Student std;
    if( request.getAttribute("student")!=null)
        std = (Student) request.getAttribute("student");
    else
        std = new Student("",0);

%>
<html>
<head>
    <title>Students</title>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@include file="/resources/style.css"%>
    </style>
</head>
<body>
    <div class="mynavbar">
        <h3></h3>
        <a href="${pageContext.request.contextPath}/logout">Logout <i class="fa fa-sign-out" aria-hidden="true"></i></a>
    </div>
    <div class="mycontent">
        <div class="mycon">
            <form method="get" action="" class="container text-center">
                <div class="row justify-content-md-center align-items-md-center">
                    <div class="col-2">
                        <label for="search" >Search</label>
                    </div>
                    <div class="col-4">
                        <input class="form-control" type="text" value="" id="search" name="search">
                    </div>
                    <div class="col-2">
                        <input class="btn btn-primary" type="submit" value="search">
                    </div>
                </div>
            </form>
            <table class="table">
                <thead class="table-dark">
                    <tr>
                        <th >ID</th>
                        <th>Username</th>
                        <th>Age</th>
                        <th  style="text-align:center">Action</th>
                    </tr>
                </thead>
                <tbody>
                <%for (Student student:students) {%>
                <tr>
                    <td><%=student.getId()%></td>
                    <td><%=student.getUsername()%></td>
                    <td><%=student.getAge()%></td>
                    <td>
                        <div style="width: 100%;display: flex;justify-content: space-evenly;">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/students?update=<%=student.getId()%>">Update</a>
                            <a class="btn btn-danger" onclick="return confirm('Are you sure')" href="${pageContext.request.contextPath}/students?del=<%=student.getId()%>">Delete</a>
                        </div>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        <div class="mycon mycon2">
            <form action="" method="post">
                <% if(request.getParameter("update")!=null){%>
                <div class="mb-3">
                    <label for="id" class="form-label">Id</label>
                    <input type="text" name="id" id="id"  class="form-control" disabled value="<%=std.getId()%>">
                </div>
                <%}%>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" required
                           class="form-control <%if(request.getAttribute("err1")!=null){%>
                            mycolor
                            <%}%>" pattern="[a-zA-Z0-9.\-_$@*!]{3,30}"
                           id="username" name="username"
                           onfocus="remove(this)" value="<%=std.getUsername()%>"
                           aria-errormessage="usernamerr" >
                    <%if(request.getAttribute("err1")!=null){%>
                    <div id="usernamerr" class="text-danger" >
                       Username already used.
                    </div>
                    <%}%>
                </div>
                <div class="mb-3">
                    <label for="age" class="form-label">Age</label>
                    <input required type="number" class="form-control" id="age"  name="age" value="<%=std.getAge()%>">

                </div>
                <button type="submit" class="btn btn-primary"><% if(request.getParameter("update")!=null){%> Update <% }else{%> Submit <%}%></button>
            </form>
        </div>
    </div>
</body>
<script>
    function remove(ele){
        ele.classList.remove('mycolor');
        if(ele.nextSibling!= null){
            ele.nextSibling.remove();
            ele.blur();
            ele.focus();
        }
    }
</script>
</html>
