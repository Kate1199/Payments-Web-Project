<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<c:forEach var="payment" items="${payments}">
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
    							<button class="btn btn-primary" type="submit">Отправить</button>
  							</div>
      					</div>
    				</div>
  				</div>
			</div>
		</c:forEach>
	</div>
	
	<nav>
  		<ul class="pagination justify-content-center mb-5 pb-5">
    		<li class="page-item disabled">
      			<a class="page-link" href="#" aria-label="Предыдущая">
        			<span aria-hidden="true">&laquo;</span>
      			</a>
    		</li>
    		<li class="page-item"><a class="page-link" href="#">1</a></li>
    		<li class="page-item active" aria-current="page">
      			<a class="page-link" href="#">2</a>
    		</li>
    		<li class="page-item"><a class="page-link" href="#">3</a></li>
    		<li class="page-item">
      			<a class="page-link" href="#" aria-label="Следующая">
        			<span aria-hidden="true">&raquo;</span>
      			</a>
    		</li>
  		</ul>
	</nav>
</body>
</html>