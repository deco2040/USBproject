<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>

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
      	예상점수 시험이 종료 되었습니다.
      </div>
     
  </div>

	<div class="wrapper flex">
	<span class=title>예상점수 결과</span><br>
	
	<div class=header></div>
	<span id=bun>예상점수 : </span><span id=bun style="float:right;"> ${expert}</span><br>
	<br>
	<br>
	<div align=center>
	

	
	</div>
  </div>
  	
</body>
</html>