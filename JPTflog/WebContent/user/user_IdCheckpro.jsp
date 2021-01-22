<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jptflog.model.user.UserDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복 체크</title>

<script> 
function apply(email){  
		opener.regform.useremail.value = email;
		window.close(); //창닫기 
		}

</script>

</head>
<body>
	<div style="text-align: center"></div>
	<h3>* 이메일 중복 확인 결과 *</h3>
	<% //1) 사용가능한 아이디일 경우, 아이디 입력 폼에 넣기 위함 
	String email=request.getParameter("email"); 
	
	UserDAO user = UserDAO.getInstance();
	int cnt= user.idCheck(email); 
	
	out.println("입력 Email : <strong>" + email + "</stong>"); 
	if(cnt==1){ out.println("<p>사용 가능한 아이디입니다.</p>"); 
	out.println("<a href='javascript:apply(\"" + email + "\")'>[적용]</a>");

 	}
	else{ 
		out.println("<p style='color: red'>해당 아이디는 사용하실 수 없습니다.</p>"); 
	}
	
	%>
	<hr>
	<a href="javascript:history.back()">[다시시도]</a> &nbsp; &nbsp;
	<a href="javascript:window.close()">[창닫기]</a>
</body>
</html>

