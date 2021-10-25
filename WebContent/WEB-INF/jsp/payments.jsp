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
    							<button class="btn btn-primary" type="submit">Оплатить</button>
  							</div>
      					</div>
    				</div>
  				</div>
			</div>
		</c:forEach>
	</div>
	
	<nav>
  		<ul class="pagination justify-content-center mb-5 pb-5">
  			<c:choose >
  				<c:when test="${currentPage <= 1}">
  					<li class="page-item disabled">
      					<a class="page-link" href="#" aria-label="Предыдущая">
        					<span aria-hidden="true">&laquo;</span>
      					</a>
    				</li>
    			</c:when>
    			<c:otherwise>
    				<li class="page-item">
    					<form action="Controller?command=payments" method="post">
      						<button name="pagination" value="previous" type="submit" class="page-link" aria-label="Предыдущая">
        						<span aria-hidden="true">&laquo;</span>
      						</button>
      					</form>
    				</li>
    			</c:otherwise>
  			</c:choose>
    		
    		<c:forEach var="pageNumber" items="${pageNumbers}">
    		<form action="Controller?command=payments&pagination=goTo" method="post">
    			<c:choose>
    				<c:when test="${pageNumber == currentPage}">
    					<li class="page-item active">
    						<button name="goTo" value="${pageNumber - 1}" type="button" class="page-link" >${pageNumber}</button>
    					</li>
    				</c:when>
    				<c:otherwise>
    						<li class="page-item">
    							<button name="goTo" value="${pageNumber - 1}" type="submit" class="page-link">${pageNumber}</button>
    						</li>
    				</c:otherwise>
    			</c:choose>
    							</form>
    		</c:forEach>
    	
    		<c:choose >
  				<c:when test="${totalPages == currentPage}">
  					<li class="page-item disabled">
      					<a class="page-link" href="#" aria-label="Следующая">
        					<span aria-hidden="true">&raquo;</span>
      					</a>
    				</li>
    			</c:when>
    			<c:otherwise>
    				<li class="page-item">
    					<form action="Controller?command=payments" method="post">
      						<button name="pagination" value="next" type="submit" class="page-link" aria-label="Следующая">
        						<span aria-hidden="true">&raquo;</span>
      						</button>
      					</form>
    				</li>
    			</c:otherwise>
  			</c:choose>
    		
  		</ul>
	</nav>	
</body>
</html>