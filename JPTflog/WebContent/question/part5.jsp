<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Set"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>part5 JPT 문제 풀기</title>
<link rel="stylesheet" href="/css/radio.css">

<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	function post_to_url(path, params, method) {
		method = method || "post";
		var form = document.createElement("form");
		form.setAttribute("method", method);
		form.setAttribute("action", path);
		for ( var key in params) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);
			form.appendChild(hiddenField);
		}
		document.body.appendChild(form);
		form.submit();
	}
</script>


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

.jb-content {
	display: block;
	width: auto;
	padding: 20px;
	margin-bottom: 20px;
	float: left;
}

.title {
	margin-right: 10px;
	color: rgb(221, 221, 221);
	line-height: 48px;
	font-weight: 600;
	font-size: 16pt;
}

#bun {
	margin-right: 10px;
	color: rgb(221, 221, 221);
	line-height: 48px;
	font-weight: 600;
	font-size: 16pt;
}

#bun a {
	margin-right: 10px;
	color: rgb(221, 221, 221);
	line-height: 48px;
	font-weight: 600;
	font-size: 16pt;
}

a:link {
	text-decoration: none;
	color: rgb(221, 221, 221);
}

a:visited {
	text-decoration: none;
	color: rgb(221, 221, 221);
}

a:active {
	text-decoration: none;
	color: rgb(240, 240, 240);
}

a:hover {
	text-decoration: none;
}

.clear {
	clear: both;
}

.clear:after {
	content: "";
	display: block;
	clear: both;
}

.btnc {
	background: #6799FF;
	padding: 15px 22px;
	color: #fff;
}

.btns {
	background: #F78181;
	padding: 15px 22px;
	color: #fff;
}

#RightBottomLayerFixed {
	position: fixed;
	right: 0;
	bottom: 0;
}
</style>
<body>
	<div>
		<hr>
		<div id=#jb-container>

			<c:set var="counter" value="${counter+1 }"></c:set>
			<c:forEach var="vo" items="${part5 }" varStatus="status">
				<form name="question" id="qidx">
					<input type="hidden" name="question${counter}" value="${vo.qidx }">
					<span class="title clear" style="float: left;">난이도 
					<c:if test ="${ vo.difficulty eq 1}"> <img width=23 height=23 src="/images/star.png"> <img width=23 height=23 src="/images/emptyStar.png"> <img width=23 height=23 src="/images/emptyStar.png"></c:if>
					<c:if test ="${ vo.difficulty eq 2}"> <img width=23 height=23 src="/images/star.png"> <img width=23 height=23 src="/images/star.png"> <img width=23 height=23 src="/images/emptyStar.png"></c:if>
					<c:if test ="${ vo.difficulty eq 3}"> <img width=23 height=23 src="/images/star.png"> <img width=23 height=23 src="/images/star.png"> <img width=23 height=23 src="/images/star.png"></c:if>
					</span>
					<div class="jb-content clear">
						<span class=title style="float: left;"> ${counter}.
							${vo.question } </span> <span class="title clear"><input
							type="checkbox" name="forvite${counter}" value="${vo.qidx }"
							<c:if test = "${favoriteSet.contains(vo.qidx) }"> checked</c:if>></span><br>
						<div class="radio-group">
							<label class=radio> <input type="radio"
								name="group${counter}" value="1">1. ${vo.choice[0]} <span></span>
							</label><br> <label class=radio> <input type="radio"
								name="group${counter}" value="2">2 .${vo.choice[1]} <span></span>
							</label><br> <label class=radio> <input type="radio"
								name="group${counter}" value="3">3 .${vo.choice[2]} <span></span>
							</label><br> <label class=radio> <input type="radio"
								name="group${counter}" value="4">4 .${vo.choice[3]} <span></span>
							</label>
							<c:set var="counter" value="${counter+1 }"></c:set>
				</form>
		</div>
	</div>
	</c:forEach>

	</div>
	</div>
	<div id=RightBottomLayerFixed>
		<input class="btns" type="button" value="제출" id="submit"> <input
			class="btnc" type="button" value="취소" onclick="window.history.back()">
	</div>
</body>

<script>
	$("#submit").click(function() {
		
			var count = 0;
			var radios = $(":radio");	
			for(var i = 0; i < radios.length; i=i+4) {			
				for(var j = i; j<i+4; j++){
					var $this = $(radios[j]);
					if($this.is(":checked")){
						count ++;
					}
				}
				if(count==0){
					if(i==0){
						alert("1번 문제 체크가 되지 않았습니다.");
					}else{
						alert((i+4)/4 + "번 문제 체크가 되지 않았습니다.");
					}
					
					return;
				}
				count = 0;	
			}
		
		

		$.ajax({
			type : "POST",
			url : "question",
			data : $("form").serialize(),
			success : function(data) {
				post_to_url("question?cmd=testEnd", {
					arg : $("form").serialize()
				});
			}
		});

	});
</script>


</html>
