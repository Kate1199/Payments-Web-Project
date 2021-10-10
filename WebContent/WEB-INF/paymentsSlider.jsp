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

<sql:setDataSource var = "payments" driver = "com.mysql.cj.jdbc.Driver"
         url = "jdbc:mysql://localhost/payments_db"
         user = "root"  password = "654321"/>

<div id="carouselIndicators" class="carousel slide" data-bs-interval="false">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
    <div class="row mb-5 mt-3">
      <sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Коммунальные платежи';
         </sql:query>
		<c:forEach var="row" items="${result.rows}">
			<div class="card col-md-3 col-sm-6 me-5" style="width: 18rem;">
  				<img src="images/utilityBills.jpg" class="card-img-top" alt="">
  				<div class="card-body">
    				<h5 class="card-title">${row.payment_name}</h5>
    				<p class="card-text">${row.description}</p>
    				<a href="/utilityBills" class="btn btn-primary">Оплатить</a>
  				</div>
			</div>
		</c:forEach>
		
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Мобильная связь';
         </sql:query>
		<c:forEach var="row" items="${result.rows}">
			<div class="card col-md-3 col-sm-6 me-5" style="width: 18rem;">
  				<img src="images/mobileNetworks.jpg" class="card-img-top" alt="">
  				<div class="card-body">
    				<h5 class="card-title">${row.payment_name}</h5>
    				<p class="card-text">${row.description}</p>
    				<a href="/mobileNetworks" class="btn btn-primary">Оплатить</a>
  				</div>
			</div>
		</c:forEach>
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Интернет';
         </sql:query>
		<c:forEach var="row" items="${result.rows}">
			<div class="card col-md-3 col-sm-6 me-5" style="width: 18rem;">
  				<img src="https://www.vitbichi.by/photo/08-2020/191809_O.jpg" class="card-img-top" alt="">
  				<div class="card-body">
    				<h5 class="card-title">${row.payment_name}</h5>
    				<p class="card-text">${row.description}</p>
    				<a href="/internet" class="btn btn-primary">Оплатить</a>
  				</div>
			</div>
		</c:forEach>
		
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Переводы с карты';
         </sql:query>
		<c:forEach var="row" items="${result.rows}">
			<div class="card col-md-3 col-sm-6" style="width: 18rem;">
  				<img src="https://www.polsov.com/upload/014/u1405/2/6/58dc55ca.jpg" class="card-img-top" alt="">
  				<div class="card-body">
    				<h5 class="card-title">${row.payment_name}</h5>
    				<p class="card-text">${row.description}</p>
    				<a href="/fines" class="btn btn-primary">Оплатить</a>
  				</div>
			</div>
		</c:forEach>		
		</div>
    </div>
    <div class="carousel-item">
    <div class="row mb-5">
    
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Штрафы';
         </sql:query>
		<c:forEach var="row" items="${result.rows}">
			<div class="card col-md-3 col-sm-6" style="width: 18rem;">
  				<img src="https://www.prikhist.com/wp-content/uploads/2020/03/shtraf2.png" class="card-img-top" alt="">
  				<div class="card-body">
    				<h5 class="card-title">${row.payment_name}</h5>
    				<p class="card-text">${row.description}</p>
    				<a href="/fines" class="btn btn-primary">Оплатить</a>
  				</div>
			</div>
		</c:forEach>
	</div>
    </div>
    <div class="carousel-item">
      <img src="..." class="d-block w-100" alt="...">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselIndicators"  data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Предыдущий</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselIndicators"  data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Следующий</span>
  </button>
</div>
	<form action="Controller" method="post">
		<div class="text-end">
			<button class="btn btn-link link-dark" type="submit" name="command" value="payments">Все платежи></button>
		</div>
	</form>
</body>
</html>