<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="container">
	<div class="row">
		<h2 class="text-center">mypage</h2>
		<div></div>
		<div>
			<div class="box-content">
				<h1 class="tag-title">내 예상점수 / 목표점수</h1>
				<hr />
				<p>[그래프위치]</p>
				<br />
			</div>
			<button type="button" class="btn btn-lg btn-primary" onclick="location.href = 'user_scoremore.jsp'">More..</button>
		</div>

		<div>
			<div class="box-content">
				<h1 class="tag-title">파트별 약점</h1>
				<hr />
				<p>[그래프위치]</p>
				<br />
			</div>
			<button type="button" class="btn btn-lg btn-primary" onclick="location.href = 'user_weakness.jsp'">More..</button>
		</div>
		<div>
			<div class="box-content">
				<h1 class="tag-title">성취율</h1>
				<hr />
				<h2>현재 성취율은 []%입니다.</h2>
				<br />
			</div>
			<button type="button" class="btn btn-lg btn-primary" onclick="location.href = 'user_scoremore.jsp'">More..</button>
		</div>
	</div>
</div>
