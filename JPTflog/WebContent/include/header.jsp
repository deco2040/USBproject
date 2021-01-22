<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JPTflog</title>
<link rel="stylesheet" href="/css/login3.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="/js/login3.js"></script>
</head>
<style>
body {
	background-color: #040404;
}
* {
    font-weight: 400;
    box-sizing: border-box;
    word-wrap: break-word;
    word-break: keep-all;
    outline: none;
    -webkit-overflow-scrolling: touch;
}

</style>
<body>

	<%
	String email = (String)session.getAttribute("email");
	%>

	<div id='cssmenu'>
		<ul>
			<li><a href='user?cmd=home'><span>개구리JPT</span></a></li>
			<li class='active has-sub'><a href='/test/hak.jsp'><span>학습하기</span></a>
				<ul>
					<li class='has-sub'><a href='/question?cmd=allpart'><span>All Parts</span></a></li>
					<li class='has-sub'><a href='/question?cmd=part5'><span>Part 5</span></a></li>
					<li class='has-sub'><a href='/question?cmd=part6'><span>Part 6</span></a></li>
					<li class='has-sub'><a href='/question?cmd=part7'><span>Part 7</span></a></li>
					<li class='has-sub'><a href='/question?cmd=part8'><span>Part 8</span></a></li>
				</ul></li>
			<li><a href='#'><span>마이노트</span></a>
				<ul>
					<li class='has-sub'><a href='/test/naega1.jsp'><span>내가 푼 문제</span></a></li>
					<li class='has-sub'><a href='/test/naega2.jsp'><span>저장한 문제</span></a></li>
				</ul>
			</li>
			<li class='active has-sub'><a href='#'><span>특별학습</span></a>
				<ul>
					<li class='has-sub'><a href='/question?cmd=myweek'><span>취약한 문제</span></a></li>
					<li class='has-sub'><a href='/question?cmd=incorrecthigh'><span>오답높은문제</span></a></li>
				</ul>
			</li>
			<li style="float: right"><a href='/service?cmd=center'><span>고객센터</span></li>
			<li class='active has-sub' style="float: right"><a href='/login3.jsp'><%=email.split("@")[0] %>님 안녕하세요</a>
				<ul>
					<li class='has-sub'><a href='/user?cmd=mypage'><span>마이페이지</span></a></li>
					<li class='has-sub'><a href='/home/logout.jsp'><span>로그아웃</span></a></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>