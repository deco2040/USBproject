<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/login3.css">
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<script src="/js/login3.js"></script>
<title>CSS MenuMaker</title>
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
#bun a{
	margin-right: 10px;
	color: rgb(221, 221, 221);
	line-height: 48px;
	font-weight: 600;
	font-size: 16pt;
}
a:link {text-decoration:none; color: rgb(221, 221, 221);}
a:visited {text-decoration:none; color: rgb(221, 221, 221);}
a:active {text-decoration:none; color:rgb(240, 240, 240);}
a:hover {text-decoration:none;}

.clear {clear:both;}
.clear:after {content:""; display:block; clear:both;}
.btnc {
	background:#6799FF;
	padding:15px 22px;
	color:#fff;
}
.btns {
	background:#F78181;
	padding:15px 22px;
	color:#fff;
}
.serch_group {
	position: fixed;
	right: 0;
	top: 0;
}
.bord_list {clear:both; padding-top:40px;}
.bord_table {width:100%;}
.bord_list th {border-top:1px solid #333;border-bottom:1px solid #333; padding:15px 5px;}
.bord_table td {border-bottom:1px solid #e0e0e0; padding:15px 5px; text-align:center;}
.bord_table td.title {text-align:left;}
.search_wrap {margin-top:100px;}
.record_group {float:left;}
.search_group {float:right;}
.record_group span {color:#007dc6;}
.search_group .select {height:44px; border:1px solid #e0e0e0; padding:5px;}
.search_group .search_word {height:44px; border:1px solid #e0e0e0; padding:5px; width:220px;}
.search_group .btn_search {height:44px; padding:5px 15px; border:1px solid #e0e0e0; background:transparent; /*투명하게*/ font-size:16px;font-color:#fff;vertical-align:bottom;}
select {
    vertical-align: middle;
    font-size: inherit;
    box-sizing: border-box;
    padding: 0 25px 0 5px;
    background: #fff url(../images/arr_down.png) no-repeat right 50%;
    -webkit-appearance: none;
    -moz-appearance: none;
    -webkit-box-shadow: none;
}
.paging {clear:both; padding-top:50px; text-align:center;}
.paging a  {padding:10px; border:1px solid #e0e0e0;}
.paging a.active {background:#007dc6; color:#fff;}
</style>
<body>
 	<div id=#jb-content>
		<h2 class="title">저장한 문제</h2>
	<div class="container">
	  <div class="search_wrap">
	  <div class="record_group">
						<p>
							총 게시글<span>${count}</span>건
						</p>qkf
					</div>
		<div class="search_group">
			<form name="search" method="post" action="report_list">
				<select name="search" size="1" style="font-family: 돋움체">
								<option value="r_title"
									<c:if test = "${query eq 'r_title'}"> selected</c:if>>글제목
								</option>
								<option value="r_writer"
									<c:if test = "${query eq 'r_writer'}"> selected</c:if>>작성자</option>
								<option value="r_content"
									<c:if test = "${query eq 'r_content'}"> selected</c:if>>글내용</option>
						</select> 
				<input type="text" name="key" class="search_word" value="${key}">
				<button class="btns" onClick="send()">검색<i class="fa fa-search"></i><span class="sr-only"></span></button>
			</form>
		</div>
	  </div> <!-- search end -->
	  <div class="bord_list">
		<table class="bord_table" summary="이표는 번호,제목,글쓴이,날자,조회수로 구성되어 있습니다">
			<colgroup>
				<col width="10%">
				<col width="10%">
				<col width="*">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th class=title>문제수</th>
					<th class=title>문제번호</th>
					<th class=title>문제</th>
					<th class=title>파트</th>
				</tr>
			</thead>
 				<tr onMouseOver="style.backgroundColor='#BDBDBD'" onMouseOut="style.backgroundColor=''">
        			<td align="center" height="25" colspan="6">
        			<font face="" size="2" color="#FFFFFF">저장된 문제가 없습니다</font></td>
    			</tr>
				<tr>
					<td align=center><font color="#FFFFFF">1</font></td>
					<td align=center><font color="#FFFFFF">20</font></td>
					<td align=center><a href='#'>12345....</a></td>
					<td align=center><font color="#FFFFFF">Part5</font></td>
				</tr>

		</table>
		<div class="paging">
			${pageSkip}
		</div>
	  </div>
	</div>
	<!-- end contents -->
		</div>
	</section>
	
</body>

</html>
