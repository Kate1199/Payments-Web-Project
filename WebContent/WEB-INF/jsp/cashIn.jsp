<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Пополнение счета</title>
</head>
<body class="mt-5 pt-5">
	
<p>Введите сумму в белорусских рублях</p>
	<input type="text" name="sum"><br>
	
	<p>Выберите счёт с которого вы хотите перечислить денежные средства:</p>
	<c:forEach var="account" items="${accounts}">
		<button type="submit">Номер счета: ${account.numberIban}<br>
			Баланс: ${account.balance}
		</button>
	</c:forEach>
</body>
</html>