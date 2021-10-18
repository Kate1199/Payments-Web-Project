<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Client Form</title>
</head>
<body class="mt-5 pt-5">
	<form action="Controller?command=clientForm" method="post" class="row g-3 needs-validation">
		
			<h5 class="display-5 mb-3 text-center">Личные данные</h5>
			
			<div class="row col-9 justify-content-center mx-auto">
			<div class="col-3 mb-2">
    			<label for="inpuLastName" class="form-label">Фамилия</label>
    			<input name="lastName" type="text" class="form-control" id="inputLastName" required pattern="^[A-ZА-Я]{1}[a-zа-я]*[\s-]*[a-zа-я]+">
 			</div>
 			
 			<div class="col-3 mb-2">
    			<label for="inpuFirstName" class="form-label">Имя</label>
    			<input name="firstName" type="text" class="form-control" id="inputFirstName" required pattern="^[A-ZА-Я]{1}[a-zа-я]*[\s-]*[a-zа-я]+">
 			</div>
 			
 			<div class="col-3 mb-2">
    			<label for="inputPatronymic" class="form-label">Отчество (если есть)</label>
    			<input name="patronymic" type="text" class="form-control" id="inputPatronymic" pattern="[А-ЯA-Z]{1}[a-zа-я]+">
 			</div>
 			
 			<div class="col-3 mb-2">
    			<label for="inputIdentifiactionNumber" class="form-label">Идентификационный номер</label>
    			<input name="identifiactionNumber" type="text" class="form-control" id="inputIdentifiactionNumber" required pattern="[A-Za-z0-9]{5,20}">
 			</div>
 			
 			<div class="col-3 mb-2">
    			<label for="inputPhoneNumber" class="form-label">Номер телефона</label>
    			<input name="phoneNumber" type="text" class="form-control" id="inputPhoneNumber" placeholder="+375 29" required pattern="\u002B{1}[0-9]{7,}">
 			</div>
 			
 			<div class="col-5 mb-2">
    			<label for="inputRegistrationAddress" class="form-label">Адрес регистрации</label>
    			<input name="registrationAddress" type="text" class="form-control" id="inputRegistrationAddress" placeholder="г. Минск, ул. ..." required pattern="[A-Za-zА-Яа-я0-9\.\,\\]{10,}">
 			</div>
 			
 			<div class="col-5 mb-2">
    			<label for="inputRealAddress" class="form-label">Адрес проживания</label>
    			<input name="realAddress" type="text" class="form-control" id="inputRealAddress" placeholder="г. Минск, ул. ..." required pattern="[A-Za-zА-Яа-я0-9\.\,\\]{10,}">
 			</div>
 			
  		</div>
 			
 			<div class="row">
  				<div class="mt-3 d-grid gap-2 col-3 mx-auto">
    				<button class="btn btn-primary" type="submit">Отправить</button>
  				</div>
  			</div>

	</form>
</body>
</html>