<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	boolean delete = (Boolean)request.getAttribute("delete");
	String msg = null;
	if(delete){
		msg = "사원정보 삭제 성공";
	}else{
		msg = "사원정보 삭제 실패";
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 삭제 결과</title>
</head>
<body>
<h3>사원정보 삭제 결과</h3>
<%= msg %>
<div>[<a href = "/demo/emp?cmd=list">목록보기</a>]</div>
</body>
</html>