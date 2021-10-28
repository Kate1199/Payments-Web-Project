<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Администраторы</title>
</head>
<body>
	<table class="table">
  		<thead class="thead-inverse">
    		<tr>
      			<th>login</th>
      			<th>email</th>
      			<th></th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:forEach var="user" items="${entities}">
    				<tr>
      					<th scope="row">${user.login} ${client.firstName} ${client.patronymic}</th>
      					<td>${user.email}</td>
      					<td><button type="submit" class="btn btn-success">Одобрить заявку</button></td>
    				</tr>
    		</c:forEach>
  		</tbody>
  		<c:set var="commandName" value="potentialAdmin"></c:set>
  		<c:import url="pagination.jsp"></c:import>
  		<c:remove var="commandName"/>
	</table>
</body>
</html>