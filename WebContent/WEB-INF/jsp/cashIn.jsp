<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Пополнение счета</title>
</head>
<body class="mt-5 pt-5">

<div class="container">

	<form action="Controller?command=cashIn" method="post">
		<div class="row col-md-5 col-sm-12 mb-2 mx-auto">
			<label for="inputSum" class="form-label">Введите сумму в белорусских рублях</label>
			<input id="inputSum" type="text" name="sum" class="form-control" pattern="[0-9]+\.?[0-9]{0,2}">
		</div>
	
		<div class="row col-md-5 col-sm-12 mb-3 mx-auto">
			<label class="form-label">Выберите счёт с которого вы хотите перечислить денежные средства:</label>
			<c:forEach var="account" items="${accounts}">
				<input type="radio" class="btn-check" name="numberIBAN" value="${account.numberIBAN}" id="${account.numberIBAN}" autocomplete="off">
  				<label class="btn btn-outline-success" for="${account.numberIBAN}">
  					Номер счета: ${account.numberIBAN}<br>
					Баланс: ${account.balance * account.balanceMultiplier} ${account.currency}
				</label>
			</c:forEach>
			<span class="row form-text text-danger text-center">${message}</span>
		</div>
	
		<div class="row col-md-3 col-sm-8 mb-2 mx-auto">
			<button type="submit" class="btn btn-primary">Пополнить</button>
		</div>
	</form>
</div>	

</body>
</html>