<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
	
	
<script>
	function correctsend() { 
    	correctform.submit();
	}
	function incorrectsend() { 
    	incorrectform.submit();
	}
</script>
	
	
</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

html, body {
	width: 100%;
	height: 100%;
}

body {
	background-color: #040404;
}

.jb-content {
	display: block;
	width: auto;
	padding: 20px;
	margin-bottom: 20px;
	text-align: center;
}

.title {
	margin-right: 10px;
	color: rgb(221, 221, 221);
	line-height: 48px;
	font-weight: 600;
	font-size: 16pt;
}

.wrapper {
	top: 1%;
	max-width: 700px;
	height: 200px;
	margin: 10px auto;
	padding: 10px;
	position: relative;
}
#bun {
	margin-right: 10px;
	color: rgb(221, 221, 221);
	line-height: 48px;
	font-weight: 600;
	font-size: 26pt;
}

.center {
	width: 400px;
	background: #333;
	border-radius: 5px;
	color: white;
	padding: 20px;
}

.wrapper.flex {
	width: 1000px;
	height: 500px;
	border: 2px solid white;
	font-size: 20px;
	font-weight: bold;
}

.wrapper .center {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: center;
}

.wrapper.flex .center {
	background: #333;
	border-radius: 5px;
	color: white;
	padding: 20px;
}
.clear {clear:both;}
.clear:after {content:""; display:block; clear:both;}
.header {
	display: flex;
	-webkit-box-pack: justify;
	justify-content: space-between;
	-webkit-box-align: center;
	align-items: center;
	margin: 0px 0px 24px;
	border-bottom: 2px solid rgb(191, 191, 191);
}

.button2 {
	width: 400px;
	background: #333;
	border-radius: 5px;
	color: white;
	padding: 20px;
}
a:link {text-decoration:none; color: rgb(221, 221, 221);}
a:visited {text-decoration:none; color: rgb(221, 221, 221);}
a:active {text-decoration:none; color:rgb(240, 240, 240);}
a:hover {text-decoration:none;}

</style>
<body>
<div class="wrapper">
    <div class="center">
      	시험이 종료 되었습니다.
      </div>
     
  </div>

	<div class="wrapper flex">
	<span class=title>part ${part } Score </span><br>
	<div class=header></div>
	<div>
	
		<c:if test="${fn:contains(week,1)}">
			<span id=bun>voca : </span><span id=bun style="float:right;"> ${scoreWeek.get(0)}/50 </span><br>	
		</c:if>		
		<c:if test="${fn:contains(week,2)}">
			<span id=bun>idiom : </span><span id=bun style="float:right;"> ${scoreWeek.get(1)}/50 </span><br>
		</c:if>
		
		<c:if test="${fn:contains(week,3)}">
			<span id=bun>proverb : </span><span id=bun style="float:right;"> ${scoreWeek.get(2)}/50 </span><br>
		</c:if>
		
		<c:if test="${fn:contains(week,4)}">
			<span id=bun>kanji : </span><span id=bun style="float:right;"> ${scoreWeek.get(3)}/50 </span><br>
		</c:if>
		
		<c:if test="${fn:contains(week,5)}">
			<span id=bun>grammer : </span><span id=bun style="float:right;"> ${scoreWeek.get(4)}/50 </span><br>
		</c:if>
		
		<c:if test="${fn:contains(week,6)}">
			<span id=bun>reading : </span><span id=bun style="float:right;"> ${scoreWeek.get(5)}/50 </span><br>
		</c:if>
		
		<c:if test="${fn:contains(week,7)}">
			<span id=bun>info : </span><span id=bun style="float:right;"> ${scoreWeek.get(6)}/ </span><br>
		</c:if>
		
		<c:if test="${fn:contains(week,8)}">
			<span id=bun>sentence : </span><span id=bun style="float:right;"> ${scoreWeek.get(7)}/50 </span><br>
		</c:if>
		
	</div>
	<div class=header></div>
	<span id=bun>Total : </span><span id=bun style="float:right;"> ${scoreWeek.get(0) + scoreWeek.get(1) + scoreWeek.get(2) + scoreWeek.get(3) + scoreWeek.get(4) + scoreWeek.get(5) + scoreWeek.get(6) + scoreWeek.get(7)}/50</span><br>
	<br>
	<br>
	<div align=center>
	
	<form name="correctform" method="post" action="question?cmd=correct">
		<a class="button2" href="javascript:correctsend()">정답 체크</a><br>
		<input type="hidden" name="correct" value="${correct}">
	</form>
	
	<br><br>
	<form name="incorrectform" method="post" action="question?cmd=incorrect">
		<a class="button2" href="javascript:incorrectsend()">오답 체크</a><br>
		<input type="hidden" name="incorrect" value="${incorrect}">
		<input type="hidden" name="answerList" value="${answerList}">
	</form>
	<br><br>
	
	<a class="button2" href='#'>다른 문제 풀기</a>
	</div>
  </div>
  	
</body>
</html>