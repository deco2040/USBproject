<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pwreset</title>
</head>
<body>
	<link
		href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!------ Include the above in your HEAD tag ---------->

	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<!--<img src="https://scocre.com/assets/img/forgot.png" class="img-fluid" alt="">-->
			</div>
			<div class="col-md-9" style="padding-top: 100px">
				<h2 class="font-weight-light">비밀번호 재설정</h2>
						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label" for="pwd"></label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="password" class="form-control" name="passwd" id="pwd"  placeholder="변경할 비밀번호를 입력해주세요.(최대 oo자)" required/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="confirm" class="cols-sm-2 control-label" for="repwd"></label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="password" class="form-control" name="repasswd" id="repwd"  placeholder="변경할 비밀번호를 다시 입력해주세요" required/><br>
								</div>
							</div>
						</div>
						<div class="form-group ">
							<a href="" target="_blank" type="button" id="button" class="btn btn-primary btn-lg btn-block">비밀번호 변경</a>
						</div>
			</div>
		</div>
	</div>

</body>
</html>