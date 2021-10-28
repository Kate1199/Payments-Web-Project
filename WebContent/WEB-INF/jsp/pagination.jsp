<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
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
    					<form action="Controller?command=${commandName}" method="post">
      						<button name="pagination" value="previous" type="submit" class="page-link" aria-label="Предыдущая">
        						<span aria-hidden="true">&laquo;</span>
      						</button>
      					</form>
    				</li>
    			</c:otherwise>
  			</c:choose>
    		
    		<c:forEach var="pageNumber" items="${pageNumbers}">
    		<form action="Controller?command=${commandName}&pagination=goTo" method="post">
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
    					<form action="Controller?command=${commandName}" method="post">
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