<%--
  Created by IntelliJ IDEA.
  User: Madhukar
  Date: 2/19/2016
  Time: 6:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--if(session.getAttribute("isVerifiedMember")==null){--%>
<%--request.getRequestDispatcher("/login").forward(request,response);--%>
<%--}%>--%>
<html>
<head>
  <title>Expense Store</title>
  <link href="${pageContext.request.contextPath}/includes/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .navbar-brand img{
      height:45px;
    }
    .navbar-header .navbar-brand{
      color: #eaf0ff;
    }
    body{
      margin-top:60px;
    }
  </style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <%--<img alt="Logo" src="${pageContext.request.contextPath}/includes/images/logo.png" class="img-responsive">--%>
      </a>
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Ispend</a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="#">Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/expense">Expense</a></li>
        <li><a href="#report">Reports</a></li>
        <li><a href="logout?page=logout" class="button special">Log Out</a></li>

      </ul>
    </div><!--/.nav-collapse -->
  </div> <!-- container collapse -->
</div> <!-- navbar collapse -->

