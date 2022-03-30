<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 추가 양식</title>
</head>
<body>
<h3>사원정보 추가 양식</h3>
<form method ="post" action = "/demo/emp">
	<%--지금하는 요청은 데이터를 추가하라는 요청이다라는 코드--%>
	<input type = "hidden" name = "cmd" value = "add">
		<%--label: 문자  input:입력박스--%>
		<%--label 앞에 for=를 해줌으로 써 input과 연결된다.--%>
	<div><label for="num">번호</label> <input type="text" id="num" name="num"></div>
	<div><label for="name">이름</label> <input type="text" id="name" name="name"></div>
	<div><label for="phone">전화</label> <input type="text" id="phone" name="phone"></div>
	<div><label for="email">이메일</label> <input type="text" id="phone" name="email"></div>
	<div>&nbsp;</div>
	<div>
		<button type = "submit">전송</button>
		<button type = "reset">취소</button>
	</div>
</form>
</body>
</html>