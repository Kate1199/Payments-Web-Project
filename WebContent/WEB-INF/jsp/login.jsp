<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
	<meta charset="UTF-8">
	<title>Login</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	<link rel="stylesheet" href="css/main.css">
</head>
<body>

<div class="p-5 mt-5">
<form action="Controller?command=login" method="post" class="row g-3">

<h5 class="display-5 text-center">Вход</h5>

<div class="row justify-content-md-center">
  <div class="col-md-4">
    <label for="inputLogin" class="form-label">Логин</label>
    <input name="login" type="text" class="form-control" id="inputLogin" value="${login}" required>
  </div>
  </div>
  <div class="row justify-content-md-center">
  <div class="col-md-4">
    <label for="inputPassword" class="form-label">Пароль</label>
    <input name="password" type="password" class="form-control" id="inputPassword" required>
    <span class="form-text text-danger">${message}</span>
  </div>
  </div>
  
  <div class="d-grid gap-2 col-2 mx-auto">
    <button class="btn btn-primary" type="submit">Войти</button>
  </div>
</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>