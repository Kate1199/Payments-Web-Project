<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body class="mt-5 pt-5 mb-5 pb-5">
	<div class="container mb-5 pb-5">
		<div class="card mb-5">
  			<div class="card-header">
    			Личная информация
 		 	</div>
 		 <c:set var="user" value="${user}"></c:set>
 		 <c:set var="client" value="${requestScope.client}"></c:set>
  			<div class="card-body">
    			<dl class="row">
    			
    				<dt class="col-sm-3">Логин</dt>
  					<dd class="col-sm-9">${user.login}</dd>
  					
  					<dt class="col-sm-3">email</dt>
  					<dd class="col-sm-9">${user.email}</dd>
  					
  					<dt class="col-sm-3">ФИО</dt>
  					<dd class="col-sm-9">${client.firstName} ${client.lastName} ${client.patronymic}</dd>

 					<dt class="col-sm-3">Идентификационный номер</dt>
  					<dd class="col-sm-9">${client.identificationNumber}</dd>

  					<dt class="col-sm-3">Номер телефона</dt>
  					<dd class="col-sm-9">${client.phoneNumber}</dd>

  					<dt class="col-sm-3 text-truncate">Адрес регистрации</dt>
  					<dd class="col-sm-9">${client.registrationAddress}</dd>

  					<dt class="col-sm-3">Адрес проживания</dt>
  					<dd class="col-sm-8">${client.realAddress}</dd>
				</dl>
    			<a href="#" class="btn btn-success">Редактировать</a>
  			</div>
		</div>

		<h5 class="display-5 mb-3">Мои счета</h5>
		
		<div class="card mb-5">
			<c:forEach var="account" items="${accounts}">
				<div class="card-header">
  					Счет № ${account.numberIBAN}
 		 		</div>
 		 
  				<div class="card-body">
    				<dl class="row">
    			
  						<dt class="col-sm-3">Баланс</dt>
  						<dd class="col-sm-9">${account.balance * account.balanceMultiplier} ${account.currency}</dd>
  						
  							<dt class="col-sm-3">Привязанные карты</dt>
  							<dd class="col-sm-9">
  								<c:forEach var="card" items="${account.cards}">
  									№ ${card.startDigits} **** **** ${card.endDigits} <br> ${card.validityPeriod} <br>
  								</c:forEach>
  							</dd>
					</dl>
					
					<form action="Controller?command=sendAccount" method="post">
    					<button type="submit" class="btn btn-success" name="numberIBAN" value="${account.numberIBAN}">Пополнить счет</button>
    					<button type="button" class="btn btn-danger">Заблокировать счет</button>
    				</form>
					
  				</div>
  			</c:forEach>
		</div>
	</div>
</body>
</html>