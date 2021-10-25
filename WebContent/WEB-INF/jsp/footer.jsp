<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body class="pb-5">
<section class="">
  <!-- Footer -->
  <footer class="footer fixed-bottom text-center text-white bg-dark mt-5" >
  
    <!-- Grid container -->
    <div class="container p-4 pb-0">
    
      <!-- Section: CTA -->
      <section class="">
        <p class="d-flex justify-content-center align-items-center">
        <c:choose>
        	<c:when test="${role == null}">
				<button type="button" class="btn btn-outline-light btn-rounded" data-bs-toggle="modal" data-bs-target="#modalNotification">Платёж</button>
				<%@include file="modalNotificationRegLog.jsp" %>
			</c:when>
			<c:otherwise>
				<form action="Controller" method="post">
          			<button name="command" value="payments" type="submit" class="btn btn-outline-light btn-rounded">
            			Платёж
          			</button>
          		</form>
			</c:otherwise>
        </c:choose>
        
        
        </p>
      </section>
      <!-- Section: CTA -->
      
    </div>
    <!-- Grid container -->

    <!-- Copyright -->
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
      © 2021 Copyright:
      <a class="text-white" href="/home">PaymentsSystem</a>
    </div>
    <!-- Copyright -->
    
  </footer>
  <!-- Footer -->
</section>
</body>
</html>