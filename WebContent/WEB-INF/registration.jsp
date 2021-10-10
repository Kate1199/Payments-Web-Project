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

<div class="p-5 mt-5">
<form action="Controller?command=registration" method="post" class="row g-3">

<h5 class="display-5 text-center">Регистрация</h5>

<div class="row justify-content-md-center">
  <div class="col-md-4">
    <label for="inputLogin" class="form-label">Логин*</label>
    <input name="login" type="text" class="form-control" id="inputLogin" required>
  </div>
 </div>
 <div class="row justify-content-md-center">
  <div class="col-md-4">
    <label for="inputEmail" class="form-label">Email*</label>
    <div class="input-group">
      <span class="input-group-text" id="inputGroupPrepend2">@</span>
      <input name="email" type="email" class="form-control" id="inputEmail"  aria-describedby="inputGroupPrepend2" required>
    </div>
  </div>
 </div>
 <div class="row justify-content-md-center">
  <div class="col-md-4">
    <label for="inputPassword" class="form-label">Пароль*</label>
    <input name="password" type="password" class="form-control" id="inputPassword" required>
  </div>
 </div>
 <div class="row justify-content-md-center">
  <div class="col-md-4">
    <label for="inputPasswordRepeat" class="form-label">Повторите пароль*</label>
    <input name="repeatPassword" type="password" class="form-control" id="inputPasswordRepeat" required>
  </div>
 </div>
 <div class="row">
  <div class="mt-2 mb-2 d-grid gap-2 col-md-4 mx-auto">
    <div class="form-check">
      <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
      <label class="form-check-label" for="invalidCheck2">
        Согласие на обработку данных
      </label>
    </div>
  </div>
  </div>
   <div class="row">
  <div class="d-grid gap-2 col-2 mx-auto">
    <button class="btn btn-primary" type="submit">Отправить</button>
  </div>
  </div>
</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>