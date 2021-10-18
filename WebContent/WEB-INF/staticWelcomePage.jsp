<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cистема платежей</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		<link rel="stylesheet" href="css/main.css">
</head>

<body>
<%@ include file="header.jsp" %>

<c:choose>
	<c:when test="${requestScope.redirect == 'registration'}">
		<%@ include file="registration.jsp" %>
	</c:when>
	<c:when test="${requestScope.redirect == 'login'}">
		<%@include file="login.jsp" %>
	</c:when>
	<c:when test="${requestScope.redirect == 'payments'}">
		<%@include file="payments.jsp" %>
	</c:when>
	<c:when test="${requestScope.redirect == 'aboutUs'}">
		<%@include file="aboutUs.jsp" %>
	</c:when>
	<c:when test="${redirect == 'exchangeRates'}">
		<%@include file="exchangeRates.jsp" %>
	</c:when>
	<c:when test="${redirect == 'partners'}">
		<%@include file="partners.jsp" %>
	</c:when>
	<c:when test="${redirect == 'contacts'}">
		<%@include file="contacts.jsp" %>
	</c:when>
	<c:when test="${redirect == 'clientForm'}">
		<%@include file="ClientForm.jsp" %>
	</c:when>
	<c:otherwise>
		<%@include file="home.jsp" %>
	</c:otherwise>
</c:choose>


<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>