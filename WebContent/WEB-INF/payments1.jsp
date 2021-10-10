<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Payments</title>
</head>
<body class="mb-5 pb-5">

	<sql:setDataSource var = "payments" driver = "com.mysql.cj.jdbc.Driver"
         url = "jdbc:mysql://localhost/payments_db"
         user = "root"  password = "654321"/>


		<div class="container">
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Коммунальные платежи';
         </sql:query>
         
		<c:forEach var="row" items="${result.rows}">
			<div class="card mb-5 mt-5 pt-5">
 				 <div class="row g-0">
    				<div class="col-md-3">
      					<img src="images/utilityBills.jpg" class="img-fluid rounded-start" alt="">
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
       						<h5 class="display-5 text-center">${row.payment_name}</h5>
        					<p class="card-text text-center">Получатель: ${row.reciever}</p>
        					<p class="card-text text-center">${row.description}</p>
        					<div class="d-grid gap-2 col-2 mx-auto">
    							<button class="btn btn-primary" type="submit">Отправить</button>
  							</div>
      					</div>
    				</div>
  				</div>
			</div>
		</c:forEach>
		
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Мобильная связь';
         </sql:query>
         
		<c:forEach var="row" items="${result.rows}">
			<div class="card mb-5 mt-5 pt-5">
 				 <div class="row g-0">
    				<div class="col-md-3">
      					<img src="images/mobileNetworks.jpg" class="img-fluid rounded-start" alt="">
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
       						<h5 class="display-5 text-center">${row.payment_name}</h5>
        					<p class="card-text text-center">Получатель: ${row.reciever}</p>
        					<p class="card-text text-center">${row.description}</p>
        					<div class="d-grid gap-2 col-2 mx-auto">
    							<button class="btn btn-primary" type="submit">Отправить</button>
  							</div>
      					</div>
    				</div>
  				</div>
			</div>
		</c:forEach>
		
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Интернет';
         </sql:query>
         
		<c:forEach var="row" items="${result.rows}">
			<div class="card mb-5 mt-5 pt-5">
 				 <div class="row g-0">
    				<div class="col-md-3">
      					<img src="https://www.vitbichi.by/photo/08-2020/191809_O.jpg" class="img-fluid rounded-start" alt="">
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
       						<h5 class="display-5 text-center">${row.payment_name}</h5>
        					<p class="card-text text-center">Получатель: ${row.reciever}</p>
        					<p class="card-text text-center">${row.description}</p>
        					<div class="d-grid gap-2 col-2 mx-auto">
    							<button class="btn btn-primary" type="submit">Отправить</button>
  							</div>
      					</div>
    				</div>
  				</div>
			</div>
		</c:forEach>
		
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Переводы с карты';
         </sql:query>
         
		<c:forEach var="row" items="${result.rows}">
			<div class="card mb-5 mt-5 pt-5">
 				 <div class="row g-0">
    				<div class="col-md-3">
      					<img src="https://www.polsov.com/upload/014/u1405/2/6/58dc55ca.jpg" class="img-fluid rounded-start" alt="">
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
       						<h5 class="display-5 text-center">${row.payment_name}</h5>
        					<p class="card-text text-center">Получатель: ${row.reciever}</p>
        					<p class="card-text text-center">${row.description}</p>
        					<div class="d-grid gap-2 col-2 mx-auto">
    							<button class="btn btn-primary" type="submit">Отправить</button>
  							</div>
      					</div>
    				</div>
  				</div>
			</div>
		</c:forEach>
		
		<sql:query dataSource = "${payments}" var = "result">
            SELECT * from Payments WHERE payment_name = 'Штрафы';
         </sql:query>
         
		<c:forEach var="row" items="${result.rows}">
			<div class="card mb-5 mt-5 pt-5">
 				 <div class="row g-0">
    				<div class="col-md-3">
      					<img src="https://www.prikhist.com/wp-content/uploads/2020/03/shtraf2.png" class="img-fluid rounded-start" alt="">
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
       						<h5 class="display-5 text-center">${row.payment_name}</h5>
        					<p class="card-text text-center">Получатель: ${row.reciever}</p>
        					<p class="card-text text-center">${row.description}</p>
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