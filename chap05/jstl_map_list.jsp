<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// Map안에 List가 저장된 자료구조를 정의하고, 값을 저장하고, 화면에 표시
	Map<String, List<String>> friends = new HashMap<>();
	
	List<String> school = new ArrayList<>();
	school.add("middle");
	school.add("high");
	school.add("university");
	friends.put("school", school);
	
	List<String> name = new ArrayList<>();
	name.add("Koon");
	name.add("Harim");
	name.add("Seokgu");
	friends.put("name", name);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>title</title>
</head>
<body>														<!-- 태그에 바디내용이 없다면 /로 끝낼 수 있다. -->

<c:set var ="friends" value = "<%=friends %>" scope="page" /> <!-- 영역안에 저장하는 JSTL태그 -->


<c:forEach var="f" items="${friends.school}"> 
	<div>${f}</div> 
</c:forEach> 

<c:forEach var="n" items="${friends.name}">
	<div>${n}</div>
</c:forEach>
<p>
<!-- 루프 안에서 Map 다루기 -->
<c:forEach var="item" items="${friends}"> 
	<div>KEY = ${item.school}</div>
	<div>VALUES = 
	    <c:forEach var ="e" items="${item.value}">
	    	${e}
	    </c:forEach>
	</div> 
</c:forEach>
</p> 
</body>
</html>