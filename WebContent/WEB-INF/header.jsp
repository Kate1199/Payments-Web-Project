<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top ">
	<div class="container">
		<a class="navbar-brand" href="#">Система платежей</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false">
			<span class="navbar-toggler-icon"></span>
		</button>
	<div class="collapse navbar-collapse " id="navbarContent">
     <ul class="navbar-nav mr-auto mb-2">
       <li class="nav-item">
         <a class="nav-link " href="/about">О нас</a>
       </li>
       <li class="nav-item">
         <a class="nav-link" href="/payments">Платежи</a>
       </li>
       <li class="nav-item">
         <a class="nav-link " href="/cards">Банковские карты</a>
       </li>
       <li class="nav-item">
         <a class="nav-link " href="parners">Партнеры</a>
       </li>
       <li class="nav-item">
         <a class="nav-link " href="/contacts">Контакты</a>
       </li>
     </ul>

<div class="ms-5 ps-5">
     <c:choose>
     <c:when test="${sessionScope.role == 'USER'}">
     <div class="row">
     <div class="col-8 me-2">
     	<h5 class="text-light">Привет, ${sessionScope.login}</h5>
     </div>
     <div class="col-3">
     <form action="Controller" method="get">
     	<button type="submit" name="redirect" value="exit" class="btn btn-outline-primary">Выход</button>
     </form>
     </div>
     </div>
     </c:when>
     <c:otherwise>
     	<%@ include file="headerButtons.jsp" %>
     </c:otherwise>
     </c:choose>
     </div>
   </div>
   </div>

</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>