<%@page import="com.tjoeun.vo.EmpVO"%>
<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	EmpVO emp = (EmpVO) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 편집</title>
</head>
<body>
<h3>사원정보 수정</h3>
<form method ="post" action = "/demo/emp">
	<%--지금하는 요청은 데이터를 추가하라는 요청이다라는 코드--%>
	<input type = "hidden" name = "cmd" value = "update">
		<%--label: 문자  input:입력박스--%>
		<%--label 앞에 for=를 해줌으로 써 input과 연결된다.--%>
	<div><label for="num">번호</label>
	<input type="text" id="num" name="num" value ="<%=emp.getNum()%>" readonly></div>
	
	<div><label for="name">이름</label> 
	<input type="text" id="name" name="name" value ="<%=emp.getName()%>" disabled></div>
	
	<div><label for="phone">전화</label> 
	<input type="text" id="phone" name="phone" value=<%=emp.getPhone() %>></div>
	
	<div><label for="email">이메일</label> 
	<input type="text" id="email" name="email" value=<%=emp.getEmail() %>></div>
	
	<div>&nbsp;</div>
	<div>
		<button type = "submit">전송</button>
		<button type = "reset">취소</button>
	</div>
</form>
</body>
</html>