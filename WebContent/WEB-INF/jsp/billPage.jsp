<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Чек</title>
</head>
<body class="mt-5 pt-5 mb-5 pb-5">
 <div class="container mb-5 pb-5">
 
 	<c:set var="bill" value="${bill}" scope="request"></c:set>
 <div class="card mb-5 col-md-6 mx-auto">
  			<div class="card-header text-center">
    			Чек
 		 	</div>

  			<div class="card-body">
    			<dl class="row">
  					<dt class="col-sm-5">${payment.name}</dt>
  					<dd class="col-sm-7">${recieverAccount}</dd>
  					
 					<dt class="col-sm-5">Дата</dt>
					<dd class="col-sm-7">${dateTime}</dd>
					
  					<dt class="col-sm-5">Сумма</dt>
  					<dd class="col-sm-7">${bill.amount * 0.01} BYN</dd>
  					
					<c:if test="${bill.fee != 0}">
  						<dt class="col-sm-5 text-truncate">Комиссия</dt>
  						<dd class="col-sm-7">${bill.fee} BYN</dd>
  						
  						<dt class="col-sm-5 text-truncate">Всего</dt>
  						<dd class="col-sm-7">${bill.fee + bill.amount} BYN</dd>
  					</c:if>
  					
  						<dt class="col-sm-5 text-truncate">Получатель</dt>
  						<dd class="col-sm-7">${payment.reciever}</dd>
  						
  						<dt class="col-sm-5 text-truncate">Оплачено со счёта</dt>
  						<dd class="col-sm-7">№${senderAccount}</dd>
  					
  					<c:if test="${payment.paymentDetails != null}">
  						<dt class="col-sm-5 text-truncate">Детализация операций:</dt>
  						<dd class="col-sm-7">${payment.paymentDetails}</dd>
  					</c:if>
  					
  					
				</dl>
  			</div>
		</div>
 </div>

</body>
</html>