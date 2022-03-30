<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Map<String, Object> emp = new HashMap<>();
	emp.put("num", 11);
	emp.put("name", "Koon");
	emp.put("dept", 20);
	emp.put("sal", 3000);
	request.setAttribute("emp", emp);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>JSTL, Map 다루기</title>
</head>
<body>
<div>번호 : ${emp.num}</div>
<div>이름 : ${emp.["name"]}</div> <!-- 이렇게 사용해도 똑같다. -->
<div>부서 : ${emp.dept}</div>
<div>급여 : ${emp.sal}</div>
</body>
</html>