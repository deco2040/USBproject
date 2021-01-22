<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>개구리JPT</title>
<style>
</style>

<%
	String cookie = "";
	Cookie[] cookies = request.getCookies(); //쿠키생성
	if (cookies != null && cookies.length > 0)
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("useremail")) { // 내가 원하는 쿠키명 찾아서 값 저장
				cookie = cookies[i].getValue();
		}
	}
%>

<script>
window.onload = function () {
	var row = ${row};
	
	 if(row==1){
	 	alert("로그인에 실패했습니다");
	 	}
	 row = 0;
	 
}

	function login1() {
		login.submit();
	}
	
	</script>

</head>
<center>
	<body cellpadding="0" cellspacing="0" marginleft="0" margintop="0"
		width="100%" height="100%" align="center">

		<div class="card align-middle"
			style="width: 20rem; border-radius: 20px;">
			<div class="card-title" style="margin-top: 30px;">
				<h2 class="card-title text-center" style="color: #113366;">개구리JPT</h2>
			</div>



			<div class="card-body"text-align:center;>
				<h5 class="form-signin-heading">로그인 정보를 입력하세요</h5>
				<form name="login" method="post" action="user?cmd=logincheck">
					<label for="inputEmail" class="sr-only">Email</label> <input
						type="email" name="email" class="form-control" placeholder="Email"
						value="<%=cookie%>" required autofocus><BR> <label
						for="inputPassword" class="sr-only">Password</label> <input
						type="password" name="passwd" class="form-control"
						placeholder="Password" required><br>

					<div class="checkbox">			
						<label> <input type="checkbox" name="remember" <%=cookie!=""?"checked" : ""%>>
							이메일 기억하기
						</label>
					</div>

					<button class="btn btn-lg btn-primary btn-block"
						onclick="login1();">로 그 인</button>
				</form>
				<br>

				<form name="register" method="post" action="user?cmd=register">
					<button class="btn btn-lg btn-primary btn-block">회원가입</button>
					<br>
				</form>

				<form name="search" method="post" action="user?cmd=pwsearch">
					<button class="btn btn-lg btn-primary btn-block">비밀번호 찾기</button>
					<br>
				</form>
</center>
</div>
</div>


<div class="modal"></div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->



<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</body>
</html>