<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%				 // 랩퍼클래스 오토언박싱.
	boolean saved = (Boolean)request.getAttribute("saved");
	String msg = "";
	if(saved){
		msg = "사원정보 추가 성공";
	}else{
		msg = "사원정보 추가 실패!!!";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>사원정보 추가 결과</title>
</head>
<body>
<%--익스프래션태그 --%>
<%= msg%>
<div>
<a href = "/demo/emp?cmd=list">[목록보기]</a>
</div>
</body>
</html>