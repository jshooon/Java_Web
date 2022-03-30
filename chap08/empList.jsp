<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 리스트</title>
<style>
		table {border : 1px solid black; border-spacing: 0; margin : 0 auto;}
		table > caption {font-size : X-Large; margin-bottom: 5px;}
		th {border: 1px solid black; border-bottom: 3px double black; background-color: khaki;}
		td {border-bottom : 1px dashed black; border-right: 1px dashed black;}
		th, td {padding : 5px 10px; text-align: center;}
		td:nth-child(3) {width : 70px;}
		td:nth-child(5) {width : 100px;}
		td:last-child {border-right: none;}
		tr:nth-child(odd) {background-color : khaki;}
		tr:last-child>td {border-bottom: none;}
		p {text-align: center}
		a {text-decoration: none;}
		.red {color:red;}
		.black {color:black;}
</style>
</head>
<body>
<table>
	<caption>사원정보 리스트</caption>
<tr><th>번호</th><th>이름</th><th>부서번호</th><th>급여</th><th>입사일</th></tr>
<c:forEach var = "l" items = "${pgutil.list}">
	<tr>
		<td>${l.empno}</td>
		<td>${l.ename}</td>
		<td>${l.deptno}</td>
		<td>${l.sal}</td>		
		<td>${l.hiredate}</td>
	</tr>
</c:forEach>
</table>
 <c:forEach var = "n" items = "${pgutil.navLinks}"> 
	<%-- <c:forEach var = "n" items = "${test}"> --%>
	<c:if test = "${n==pgutil.currPage}"> <!-- n이 현재 페이지와 같다면, -->
		<c:set var ="cls" value = "red"/>
	</c:if>
	<c:if test = "${n!=pgutil.currPage}"> <!-- n이 현재 페이지와 같지 않다면 -->
		<c:set var ="cls" value = "black"/>
	</c:if>
	<a class="${cls}" href = "/demo/paging?page=${n}">${n}</a>
</c:forEach>




</body>
</html>