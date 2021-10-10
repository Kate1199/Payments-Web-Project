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
<%@ include file="header.jsp" %>

<div class="p-5 mt-5">
<form action="Controller?command=registration" method="post" class="row g-3">

<h5 class="display-5">Регистрация</h5>

  <div class="col-md-4">
    <label for="inputLogin" class="form-label">Логин*</label>
    <input name="login" type="text" class="form-control" id="inputLogin" required>
  </div>
  <div class="col-md-4">
    <label for="inputEmail" class="form-label">Email*</label>
    <div class="input-group">
      <span class="input-group-text" id="inputGroupPrepend2">@</span>
      <input name="email" type="email" class="form-control" id="inputEmail"  aria-describedby="inputGroupPrepend2" required>
    </div>
  </div>
  <div class="col-md-4">
    <label for="inputPhoneNumber" class="form-label">Номер телефона*</label>
    <input type="text" class="form-control" id="inputPhoneNumber" value="+375()" required>
  </div>
  <div class="col-md-4">
    <label for="inputFistName" class="form-label">Имя*</label>
    <input type="text" class="form-control" id="inputFistName" required>
  </div>
  <div class="col-md-4">
    <label for="inputSecondName" class="form-label">Фамилия</label>
    <input type="text" class="form-control" id="inputSecondName" required>
  </div>
  <div class="col-md-4">
    <label for="inputPatronymic" class="form-label">Отчество (если есть)</label>
    <input type="text" class="form-control" id="inputPatronymic">
  </div>
  <div class="col-md-4">
    <label for="inputIdentifiactionNumber" class="form-label">Идентификационный номер паспорта*</label>
    <input type="text" class="form-control" id="inputIdentifiactionNumber" required>
  </div>
  <div class="col-md-4">
    <label for="inputPassword" class="form-label">Пароль*</label>
    <input name="password" type="password" class="form-control" id="inputPassword" required>
  </div>
  <div class="col-md-4">
    <label for="inputPasswordRepeat" class="form-label">Повторите пароль*</label>
    <input name="repeatPassword" type="password" class="form-control" id="inputPasswordRepeat" required>
  </div>
  
  <div class="col-md-6">
    <label for="inputRegistrationAddress" class="form-label">Адрес регистрации*</label>
    <input type="text" class="form-control" id="inputRegistrationAddress" required>
  </div>
   <div class="col-md-6">
    <label for="inputRealAddress" class="form-label">Адрес проживания*</label>
    <input type="text" class="form-control" id="inputRealAddress" required>
  </div>
  <div class="col-12">
    <div class="form-check">
      <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
      <label class="form-check-label" for="invalidCheck2">
        Согласие на обработку данных
      </label>
    </div>
  </div>
  <div class="col-12">
    <button class="btn btn-primary" type="submit">Отправить</button>
  </div>
  
</form>
</div>

<%@ include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>