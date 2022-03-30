<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	boolean updated = (Boolean)request.getAttribute("updated");
	String msg = null;
	if(updated) msg = "수정 성공";
	else msg = "수정 실패";
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>수정 결과</title>
</head>
<body>
<%=msg %>
</body>
</html>