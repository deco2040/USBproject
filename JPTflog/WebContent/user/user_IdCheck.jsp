<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheckForm.jsp</title>
</head>
<body>
	<div style="text-align: center">
		<h3>* 이메일 중복확인 *</h3>
		<form method="post" action="user_IdCheckpro.jsp"
			onsubmit="return blankCheck(this)">
			이메일 : <input type="email" name="email" maxlength="40" autofocus>
			<input type="submit" value="중복확인">
		</form>
	</div>
</body>
</html>

