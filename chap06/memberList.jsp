<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원 리스트</title>
<style>
	table{border : 1px solid black; border-spacing: 0; margin : 0 auto;}
	table>caption {font-size : X-Large; margin-bottom: 5px;}
	th{border : 1px solid black; border-bottom: 3px double black; background-color : khaki;}
	td{border-bottom : 1px dashed black; border-right: 1px dashed black;}
	th, td{padding : 5px 10px;}
	td:nth-child(1) {width : 50px;}
	td:nth-child(4) {width : 100px;}
	td:last-child {border-right : none;}
	tr:nth-child(odd) {background-color : khaki;} /* tr:nth-child(odd) : tr의 홀수행들을 지정하는 키워드 odd는 홀수 even은짝수 */
	tr:last-child>td{border-bottom: none;} /*tr:last-child>td : tr인데 마지막 자식인 tr의 자식으로 있는 td를 지정하는 키워드*/
</style>
</head>
<body>
<table>
<caption>사원 리스트</caption>
<tr><th>사원번호</th><th>사원이름</th><th>부서번호</th><th>입사일</th><th>급여</th></tr>
<c:forEach var="list" items="${requestScope.list}">
		<tr>
			<td>${list.empno}</td>
			<td><a href = "mem?cmd=detail&num=${list.empno}">${list.ename}</a></td>
			<td>${list.deptno}</td>
			<td>${list.hiredate}</td>
			<td>${list.sal}</td>
		</tr>
</c:forEach>
</table>
<div>[<a href = "/demo/mem/addmem.jsp">사원정보 추가</a>]</div>
</body>
</html>