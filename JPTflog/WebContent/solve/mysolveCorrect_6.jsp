<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>내가 맞췄던 문제</h1>
<table>

<c:forEach var="vo" items="${solveL }" varStatus="status"> 
<tr><td>${status.count}번</td></tr>
<tr><td>${vo.qidx}번</td></tr>
<tr><td>part : ${vo.part }</td></tr>

<input type="hidden" name="qidxN${status.count}" value="${vo.qidx }">


<tr><td>난이도</td><td>${vo.part }</td></tr>
<tr><td>문제</td><td>${vo.question }</td></tr>

<tr><td>선택지</td>
<td><input type="radio" name="group${status.count}" value="${vo.choice[0]}">${vo.choice[0]}</td>
<td><input type="radio" name="group${status.count}" value="${vo.choice[1]}">${vo.choice[1]}</td>
<td><input type="radio" name="group${status.count}" value="${vo.choice[2]}">${vo.choice[2]}</td>
<td><input type="radio" name="group${status.count}" value="${vo.choice[3]}">${vo.choice[3]}</td>
</tr>


</c:forEach>
</table>
<table>
<c:forEach var="vo2" items="${solveL }" varStatus="status"> 
<tr>
<td>${vo2.qidx }번</td>
</tr>
<tr>
<td>답</td>
<td>${vo2.answer }</td>
</tr>
</c:forEach>
</table>

</body>
</html>