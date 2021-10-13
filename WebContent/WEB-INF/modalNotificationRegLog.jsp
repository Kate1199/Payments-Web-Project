<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<div class="modal fade" id="modalNotification" tabindex="-1" aria-labelledby="modalNotificationLabel" aria-hidden="true">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="modalLabel">Невозможно оплатить</h5>
        				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
      			</div>
      			<div class="modal-body">
        			<p>Для того, чтобы перейти к платежам войдите или зарегистрируйтесь</p> 
      			</div>
      			<div class="modal-footer">
        			<%@include file="headerButtons.jsp" %>
      			</div>
    		</div>
  		</div>
	</div>
</body>
</html>