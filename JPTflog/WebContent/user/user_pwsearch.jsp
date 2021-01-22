<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<script>
function send(){
	if(document.pwsearch.useremail.value==""){ 
		alert("이메일을 입력해 주세요."); 
		document.pwsearch.useremail.focus();
		return; 
	}

	
	pwsearch.submit();
}
</script>


</head>
<body>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="container">
    	<div class="row">
    		<div class="col-md-3">
    		    <!--<img src="https://scocre.com/assets/img/forgot.png" class="img-fluid" alt="">-->
    		</div>
    		<div class="col-md-9" style="padding-top:100px">
    		    <h2 class="font-weight-light">비밀번호를 잊으셨나요?</h2>
    		    이메일 주소를 입력해서 비밀번호를 재설정 할 수 있습니다.
    		   <form name="pwsearch" method="get" action="password">		
    		        <input class="form-control form-control-lg" type="email" name="useremail" id="useremail" placeholder="이메일 주소를 입력해주세요">
    		   </form> 
    		        <div class="text-right my-3">
    		            <button class="btn btn-lg btn-primary" onclick="send();">비밀번호 재설정</button>
    		    		
    		    		
    		        </div>
    		     <button class="btn btn-lg btn-primary" onclick="location.href='/user?cmd=login'">뒤로가기</button>
    		           
    		    
    		</div>
    	</div>
    </div>

</body>
</html>