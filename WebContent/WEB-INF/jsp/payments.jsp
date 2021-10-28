<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
</head>
<body class="mb-5 pb-5">
	<div class="container">
		<c:forEach var="payment" items="${entities}">
			<div class="card mb-5 mt-5 pt-5">
 				 <div class="row g-0">
    				<div class="col-md-3">
      					<img src="images/utilityBills.jpg" class="img-fluid rounded-start" alt="" />
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
       						<h5 class="display-5 text-center">${payment.name}</h5>
        					<p class="card-text text-center">Получатель: ${payment.reciever}</p>
        					<p class="card-text text-center">${payment.description}</p>
        					<div class="d-grid gap-2 col-2 mx-auto">
    							<button class="btn btn-primary" type="submit">Оплатить</button>
  							</div>
      					</div>
    				</div>
  				</div>
			</div>
		</c:forEach>
	</div>
	
	<c:set var="commandName" value="payments" scope="session"></c:set>
	<c:import url="pagination.jsp"></c:import>
	<c:remove var="commandName"/>
</body>
</html>