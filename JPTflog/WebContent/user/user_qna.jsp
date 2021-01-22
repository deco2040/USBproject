<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>qna</title>
    <link href="mypage/css/bootstrap.min.css" rel="stylesheet">
    <link href="mypage/css/style.css" rel="stylesheet">

  </head>
  <body>
<div class="input-group col-md-6">
  <input type="text" class="form-control" placeholder="검색어 입력">
  <span class="input-group-btn">
    <button type="button" class="btn btn-primary">검색</button>
  </span>
</div>
<br>

    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-6">
					<button type="button" class="btn btn-primary btn-lg btn-block" onclick="location.href = 'user_notice.jsp'">
						공지사항
					</button>
				</div>
				<div class="col-md-6">
					<button type="button" class="btn btn-primary btn-lg btn-block" onclick="location.href = 'user_qna_pro.jsp'">
						자주 묻는 질문
					</button>
				</div>
			</div>
			<br>
			<h1 class="text-center">서비스 안내</h1>
			<dl>
				<hr />
				<dt>
					질문1
				</dt>
				<dd>
					답변1
				</dd>
				<br />
				<hr />
				<dt>
					질문2
				</dt>
				<dd>
					답변2
				</dd>
				<br />
				<hr />
				<dt>
					질문3
				</dt>
				<dd>
					답변3
				</dd>
				<br />
				<hr />
				<dt>
					질문4
				</dt>
				<dd>
					답변4
				</dd>
				<br />
			</dl>
		</div>
	</div>
</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>