<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

 <div class="row mt-3">
 <c:forEach var="payment" items="${payments}">

 	<div class="card mx-auto" style="width: 18rem;">
  		<div class="card-body">
    		<h5 class="card-title">${payment.name}</h5>
    		<p class="card-text">${payment.description}</p>
    		<a href="#" class="btn btn-primary">Оплатить</a>
  		</div>
	</div>

	</c:forEach>
	</div>
   

	<c:choose>
		<c:when test="${role == null}">
			<div class="text-end">
				<button type="button" class="btn btn-link link-dark" data-bs-toggle="modal" data-bs-target="#modalNotification">Все платежи></button>
			</div>
			<%@include file="modalNotificationRegLog.jsp" %>
		</c:when>
		<c:otherwise>
			<form action="Controller" method="post">
				<div class="text-end">
					<button class="btn btn-link link-dark" type="submit" name="command" value="payments">Все платежи></button>
				</div>
			</form>
		</c:otherwise>
	</c:choose>
	
</body>
</html>