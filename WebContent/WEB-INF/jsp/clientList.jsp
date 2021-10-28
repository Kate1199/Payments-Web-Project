<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Клиенты</title>
</head>
<body class="mt-5 pt-5 mb-5 pb-5">
	<table class="table">
  		<thead class="thead-inverse">
    		<tr>
      			<th>ФИО</th>
      			<th>Идентификационный номер</th>
      			<th>Номер счёта</th>
      			<th></th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:forEach var="account" items="${entities}">
  			<c:set var="client" value="${account.client}"></c:set>
    				<tr>
      					<th scope="row">${client.lastName} ${client.firstName} ${client.patronymic}</th>
      					<td>${client.identificationNumber}</td>
     					 <td>${account.numberIBAN}</td>
      					<td><button type="submit" class="btn btn-danger">Заблокировать счёт</button></td>
    				</tr>
    		</c:forEach>
  		</tbody>
	</table>
	
	<c:set var="commandName" value="showClients" scope="session"></c:set>
	<c:import url="pagination.jsp"></c:import>
	<c:remove var="commandName"></c:remove>
</body>
</html>