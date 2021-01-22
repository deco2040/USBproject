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
<c:set var="none" value="none" />
 	<div id=#jb-content>
		<div class="card clear">
			<div class=header>
				<span class=title>예상점수&nbsp;</span>
			</div>
		</div>
			<div class="card-wrapper clear">
	
				<c:if test ="${expert != -99}">
				 	<font class="score">${expert} / 900</font>
				</c:if>
				<c:if test ="${expert == -99}">
				 	<h1 style="color: #FFFFFF;">간단한 시험을 통해 예상점수를 알아 볼 수 있습니다. </h1>
				</c:if>
				
			</div>
			<div class=card-wrapper>
				<c:if test ="${goal != -99}">
				 	목표 점수와 차이는 000점 입니다.
				</c:if>
				
			</div>
			
			<div class=card-wrapper>
				
				<c:if test ="${expert != -99}">
				 	<a href='/user?cmd=expectation'>예상점수 한번 더 알아보러가기</a>
				</c:if>
				<c:if test ="${expert == -99}">
				 	<a href='/user?cmd=expectation'>예상점수 알아보러가기</a>
				</c:if>
				
			</div>
		<div class=card>
			<div class=header>
				<span class=title>목표 점수&nbsp;</span>
			</div>
		</div>
			<div class=card-wrapper>
				<c:if test ="${goalgraph ne none}">
				 	<img src="data:image/jpg;base64, <%=request.getAttribute("goalgraph")%>" /><br>			 	
				</c:if>
				<c:if test ="${goalgraph eq none}">
				 	<h1 style="color: #FFFFFF;">아직 등록된 목표점수 및 점수가 없습니다. </h1>			 	
				</c:if>
					
			</div>
			
			<br>
			<div class=card-wrapper>			
				<c:if test ="${goal != -99}">
				 	<a href='#'>목표점수 변경하기 ></a>
				</c:if>
				<c:if test ="${goal == -99}">
				 	<a href='#'>목표점수 등록하기 ></a>
				</c:if>
				
			</div>
			
			
	</div>
	<div id=#jb-sidebar>
		<div class=card>
			<div class=header>
				<span class=title>취약점 분석&nbsp;</span>
			</div>
		</div>
			<div class=card-wrapper>
			
			<c:if test ="${rader ne none || pie ne none}">
				<img src="data:image/jpg;base64, <%=request.getAttribute("rader")%>" />
				<br>
				<img src="data:image/jpg;base64, <%=request.getAttribute("pie")%>" />
			</c:if>
			<c:if test ="${rader eq none || pie eq none}">
				 	<h1 style="color: #FFFFFF;">아직 등록된 약점 데이터가 없습니다. </h1>
			</c:if>
				
			</div>
	</div>
	<div id=#jb-sidebar>
		<div class=card>
			<div class=header>
				<span class=title>Part 별 분석&nbsp;</span>
			</div>
		</div>
			<div class=card-wrapper>
			
			<c:if test ="${part ne none || totpart ne none}">
				<img src="data:image/jpg;base64, <%=request.getAttribute("part")%>" /> 
				<br>
				<img src="data:image/jpg;base64, <%=request.getAttribute("totpart")%>" />		
			</c:if>
			<c:if test ="${part eq none || totpart eq none}">
				 	<h1 style="color: #FFFFFF;">아직 등록된 파트별 데이터가 없습니다. </h1>
			</c:if>
				
			</div>
			
			<br>
			<c:if test ="${part ne none || totpart ne none}">
				<div class=card-wrapper><a href='/week?cmd=weekmore'>MORE</a></div>
			</c:if>
	</div>
	<br><br>
</body>
</html>
<%@ include file="/include/footer.jsp"%>