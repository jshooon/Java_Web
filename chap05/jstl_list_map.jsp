<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Map<String, Object> emp1 = new HashMap<>();
	emp1.put("num", 11);
	emp1.put("name", "Koon");
	emp1.put("dept", 20);
	emp1.put("sal", 3000);
	
	Map<String, Object> emp2 = new HashMap<>();
	emp2.put("num", 12);
	emp2.put("name", "Harim");
	emp2.put("dept", 30);
	emp2.put("sal", 5000);
	
	List<Map<String, Object>> list = new ArrayList<>();
	list.add(emp1);
	list.add(emp2);
	
	session.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>title</title>
</head>
<body>
<!-- 리스트 안에 맵이 저장된 자료구조를 JSTL, EL을 사용하여 표시해보세요. -->
<c:forEach var="b" items="${list}">
	<div>${b.num} ${b.name} ${b.dept} ${b.sal}</div>
</c:forEach>

<c:forEach begin="0" end="3" varStatus="status" >
	<div>${list[status.index].num} ${list[status.index].name} ${list[status.index].dept} ${list[status.index].sal}</div>
</c:forEach>
</body>
</html>