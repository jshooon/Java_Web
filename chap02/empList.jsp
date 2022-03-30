<%--
파일이름 : emplist.jsp
작 성 자 : 지 성훈
작 성 일 : 2022. 03. 02(수)
프로그램 설명 : JSP 사용법에 대한 예제 사원리스트 웹브라우져 출력 실습 내용.
 --%>
<%@page import="com.tjoeun.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	List<EmpVO> list = (List<EmpVO>)request.getAttribute("list");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 리스트</title>
	<style>	  /*border : 테두리선, solid : 실선, border-spacing : 테두리 간격*/
		table { border : 1px solid black; border-spacing:0; }
			  /* 디폴트 상태에서의 글자높이 1em, padding : 태그박스의 테두리와 컨텐트의 내부간격 margin : 태그와 태그와의 외부간격*/
		th,td { border : 1px solid black; padding:0.2em; }
			/* border-bottom : 테두리선의 바닥*/
		th { border-bottom: 3px solid black; }
		td { border:1px solid black;}
		td { border-bottom: 1px dotted black; }
	</style>
</head>
<body>
<h3>사원정보 리스트</h3>
<table>
<tr><th>번호</th><th>이름</th><th>전화</th><th>이메일</th></tr>
<%
	for(int i=0; i<list.size(); i++) {
		EmpVO emp = list.get(i); %>
		<tr> <%--한행에 td 네개 --%>								
			<td><%=emp.getNum()%></a></td>  <%--서버 번호요청. --%>
			<td><a href="/demo/emp?cmd=find&num=<%=emp.getNum()%>"><%=emp.getName()%></td>
			<td><%=emp.getPhone()%></td>
			<td><%=emp.getEmail()%></td>
		</tr>
<% 		
	}
%>
</table>
<hr>
[<a href ="empInputForm.jsp">사원정보 추가</a>]
</body>
</html>