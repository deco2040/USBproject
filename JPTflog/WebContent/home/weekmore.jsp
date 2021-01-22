<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/login3.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="/js/login3.js"></script>
<title>Home</title>
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
div {
	display: block;
	text-align: center;
	
}

.header {
	display: flex;
	-webkit-box-pack: justify;
	justify-content: space-between;
	-webkit-box-align: center;
	align-items: center;
	margin: 0px 0px 24px;
	border-bottom: 2px solid rgb(191, 191, 191);
}
.clear {clear:both;}
.clear:after {content:""; display:block; clear:both;}

</style>
<body>

	<div id=#jb-sidebar>
		<div class=card>
			<div class=header>
				<span class=title>Part 별 분석 더보기&nbsp;</span>
			</div>
		</div>
			<div class=card-wrapper>	
	
				<img src="data:image/jpg;base64, <%=request.getAttribute("part")%>" /> 
				<br>
				<img src="data:image/jpg;base64, <%=request.getAttribute("totpart")%>" />
				
			</div>
			
			<br>
	
	</div>
	
		<div id=#jb-sidebar>
		<div class=card>
			<div class=header>
				<span class=title>Part 별 분석 더보기2&nbsp;</span>
			</div>
		</div>
			<div class=card-wrapper>	
	
				<img src="data:image/jpg;base64, <%=request.getAttribute("part2")%>" /> 
				<br>
				<img src="data:image/jpg;base64, <%=request.getAttribute("totpart2")%>" />
				
			</div>
			
			<br>
	
	</div>
	
	<br><br>
	<div class=card-wrapper><a href='javascript:history.back()'>뒤로가기</a></div>
	<br><br>

	
</body>
</html>
<%@ include file="/include/footer.jsp"%>