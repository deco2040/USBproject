<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script>
	function change() {
		
		if(document.pwchange.AuthenticationUser.value==""){ 
			alert("인증번호를 입력해 주세요."); 
			document.pwchange.AuthenticationUser.focus();
			return; 
		}
		if(document.pwchange.pwd.value==""){ 
			alert("새 비밀번호를 입력해 주세요."); 
			document.pwchange.pwd.focus();
			return; 
		}
		if(document.pwchange.repwd.value==""){ 
			alert("새 비밀번호 확인을 입력해 주세요."); 
			document.pwchange.repwd.focus();
			return; 
		}
		if(document.pwchange.repwd.value!=document.pwchange.pwd.value){ 
			alert("비밀번호가 일치하지 않습니다."); 
			document.pwchange.repwd.focus();
			return; 
		}
		
		
		pwchange.submit();
	}
</script>


<head>
<meta charset="UTF-8">
<title>pwsearch</title>
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
				<form name = "pwchange" id="pwchange" method="post" action="password">

					<input type="hidden" name="useremail" value="${email }">
					<div class="form-group">
						<label for="authentication" class="cols-sm-2 control-label">인증번호</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									name="AuthenticationUser" id="AuthenticationUser"
									placeholder="인증번호" />
							</div>
						</div>
					</div>


					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label" for="pwd">새비밀번호</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									type="password" class="form-control" name="passwd" id="pwd"
									size=13 placeholder="최대 13자까지 입력가능" required />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label" for="repwd">새비밀번호
							확인</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									type="password" class="form-control" name="repasswd" id="repwd"
									placeholder="비밀번호를 다시 입력해주세요" required /><br>
							</div>
						</div>
					</div>
					<div class="alert alert-success" id="alert-success">비밀번호가
						일치합니다.</div>
					<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지
						않습니다.</div>
			</div>
			<br>

			<div class="form-group ">
				<label id="button"
					class="btn btn-primary btn-lg btn-block login-button"
					onclick="change();">비밀번호 변경</label> <label id="button"
					class="btn btn-primary btn-lg btn-block login-button"
					onclick="location.href='/user?cmd=login'">취소하기</label>
			</div>


		</div>

		</form>
		<script type="text/javascript">
			$(function() {
				$("#alert-success").hide();
				$("#alert-danger").hide();
				$("input").keyup(function() {
					var pwd = $("#pwd").val();
					var repwd = $("#repwd").val();
					if (pwd != "" || repwd != "") {
						if (pwd == repwd) {
							$("#alert-success").show();
							$("#alert-danger").hide();
							$("#submit").removeAttr("disabled");
						} else {
							$("#alert-success").hide();
							$("#alert-danger").show();
							$("#submit").attr("disabled", "disabled");
						}
					}
				});
			});
		</script>



	</div>

</body>
</html>