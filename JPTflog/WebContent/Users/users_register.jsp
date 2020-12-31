<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
<head> 


    <script src="https://kit.fontawesome.com/c0da795b31.js" crossorigin="anonymous"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">


		<!-- Website CSS style -->
		<link href="css/bootstrap.min.css" rel="stylesheet">

		<!-- Website Font style -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<link rel="stylesheet" href="style.css">
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

		<title>Register</title>
	</head>
	<body>
		<div class="container">
			<div class="row main">
				<div class="main-login main-center">
					<form class="" id="regform" method="post" action="#">
					<h1 class="text-center">회원가입</h1>
						
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">이메일</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="useremail" id="email"  placeholder="ex)angryfrog@gmail.com"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label" for="pwd">비밀번호</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="password" class="form-control" name="passwd" id="pwd"  placeholder="최대 oo자까지 입력가능" required/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="confirm" class="cols-sm-2 control-label" for="repwd">비밀번호 확인</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="password" class="form-control" name="repasswd" id="repwd"  placeholder="비밀번호를 다시 입력해주세요" required/><br>
								</div>
							</div>
						</div>
						<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div> 
						<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
						
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">전화번호</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fas fa-phone-alt" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="phone" id="phone"  placeholder="전화번호를 입력해주세요"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">주소</label><br>
							<body>
								<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
								<input type="text" size=15 id="sample4_postcode" placeholder="우편번호">
								<input type="text" size=30 id="sample4_roadAddress" placeholder="도로명주소">
								<input type="text" size=30 id="sample4_jibunAddress" placeholder="지번주소">
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" size=30 id="sample4_detailAddress" placeholder="상세주소">
								<input type="text" size=15 id="sample4_extraAddress" placeholder="참고항목">
								
								<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
								<script>
								    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
								    function sample4_execDaumPostcode() {
								        new daum.Postcode({
								            oncomplete: function(data) {
								                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
								
								                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
								                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
								                var roadAddr = data.roadAddress; // 도로명 주소 변수
								                var extraRoadAddr = ''; // 참고 항목 변수
								
								                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
								                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
								                    extraRoadAddr += data.bname;
								                }
								                // 건물명이 있고, 공동주택일 경우 추가한다.
								                if(data.buildingName !== '' && data.apartment === 'Y'){
								                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
								                }
								                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								                if(extraRoadAddr !== ''){
								                    extraRoadAddr = ' (' + extraRoadAddr + ')';
								                }
								
								                // 우편번호와 주소 정보를 해당 필드에 넣는다.
								                document.getElementById('sample4_postcode').value = data.zonecode;
								                document.getElementById("sample4_roadAddress").value = roadAddr;
								                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
								                
								                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
								                if(roadAddr !== ''){
								                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
								                } else {
								                    document.getElementById("sample4_extraAddress").value = '';
								                }
								
								                var guideTextBox = document.getElementById("guide");
								                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
								                if(data.autoRoadAddress) {
								                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
								                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
								                    guideTextBox.style.display = 'block';
								
								                } else if(data.autoJibunAddress) {
								                    var expJibunAddr = data.autoJibunAddress;
								                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
								                    guideTextBox.style.display = 'block';
								                } else {
								                    guideTextBox.innerHTML = '';
								                    guideTextBox.style.display = 'none';
								                }
								            }
								        }).open();
								    }
								</script>
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">성별</label>
							<div class="cols-sm-10">
								<div class="input-group">
						<label class="box-radio-input"><input type="radio" name="cp_item" value="남자"><span>남자</span></label>
						<label class="box-radio-input"><input type="radio" name="cp_item" value="여자"><span>여자</span></label>
						</div>
						</div>
						</div>
						<div class="form-group ">
							<label  type="submit" id="button" class="btn btn-primary btn-lg btn-block login-button">회원가입</label>
						</div>
						<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
						<script type="text/javascript">
						$(function(){
							$("#alert-success").hide();
							$("#alert-danger").hide();
							$("input").keyup(function(){
								var pwd=$("#pwd").val();
								var repwd=$("#repwd").val();
								if(pwd != "" || repwd != ""){
									if(pwd == repwd){
										$("#alert-success").show();
										$("#alert-danger").hide();
										$("#submit").removeAttr("disabled");
										}else{
											$("#alert-success").hide();
											$("#alert-danger").show();
											$("#submit").attr("disabled", "disabled");
											}
									}
								});
							});							
						</script>
					</form>
				</div>
			</div>
		</div>

		 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	</body>
</html>