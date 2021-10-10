 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
						http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>Cистема платежей</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
		name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
		<link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@ include file="WEB-INF/header.jsp" %>

<c:choose>
	<c:when test="${requestScope.redirect == 'registration'}">
		<%@ include file="WEB-INF/registration.jsp" %>
	</c:when>
	<c:when test="${requestScope.redirect == 'login'}">
		<%@include file="WEB-INF/login.jsp" %>
	</c:when>
	<c:when test="${requestScope.redirect == 'payments'}">
		<%@include file="WEB-INF/payments.jsp" %>
	</c:when>
	<c:otherwise>
		<%@include file="WEB-INF/home.jsp" %>
	</c:otherwise>
</c:choose>


<%@ include file="WEB-INF/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>
