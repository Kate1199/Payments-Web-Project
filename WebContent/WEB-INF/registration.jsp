<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registration</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	<link rel="stylesheet" href="css/main.css">
</head>
<body>
<form action="Controller?command=registration" method="post" class="row g-3 needs-validation">
	<div class="p-5 mt-5 col-md-5 mx-auto">
		
			<h5 class="display-5 text-center">Регистрация</h5>

			<div class="row mb-2">
    			<label for="inputLogin" class="form-label">Логин*</label>
    			<input name="login" type="text" class="form-control" id="inputLogin" required pattern="[A-Za-z0-9_-]+">
 			</div>
 
 			<div class="row mb-2">
    			<label for="inputEmail" class="form-label">Email*</label>
    			<div class="input-group">
      			<span class="input-group-text" id="inputGroupPrepend2">@</span>
     	 		<input name="email" type="email" class="form-control" id="inputEmail"  
     	 			aria-describedby="inputGroupPrepend2" placeholder="name@example.com" 
     	 			required pattern="[a-zA-z_\.\d]+@([a-zA-Z]+\.){1,2}[a-zA-Z]+">
    		</div>
 		</div>
 
 		<div class="row">
 			<label for="inputPassword" class="form-label">Пароль*</label>
 			<div class="input-group">
  				<input name="password" type="password" class="form-control" id="inputPassword"
     				aria-describedby="basic-addon2" required pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^@#$%]).{6,20})">
  				<div class="input-group-append">
   					<a href="#" class="btn btn-outline-dark" onclick="return show_hide_password(this);"><img src="images/eye.svg" alt="" /></a>
 
  				</div>
    			<span id="passwordHelpInline" class="form-text">
  					Ваш пароль должен состоять из 8-20 символов, содержать буквы разного регистра, цифры и не должен содержать пробелов, специальных символов или эмодзи.
				</span>
			</div>
		</div>
 
  		<div class="mt-2 mb-2 d-grid gap-2 mx-auto">
   			 <div class="form-check">
      			<input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
      			<label class="form-check-label" for="invalidCheck2">
        			Согласие на обработку данных
      			</label>
      			<span class="row form-text text-danger">${message}</span>
    		</div>
  		</div>
  		<div class="row">
  		<div class="d-grid gap-2 col-4 mx-auto mt-3">
    		<button class="btn btn-primary" type="submit">Отправить</button>
  		</div>
  	</div>
  	</div>
  	
  
  	
   	
</form>

<script type="text/javascript">
function show_hide_password(target){
	var input = document.getElementById('inputPassword');
	if (input.getAttribute('type') == 'password') {
		target.classList.add('view');
		input.setAttribute('type', 'text');
	} else {
		target.classList.remove('view');
		input.setAttribute('type', 'password');
	}
	return false;
}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>