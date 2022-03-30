<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	boolean saved = (Boolean)request.getAttribute("saved");
	String jsonStr = String.format("{\"ok\" : %b}", saved);
%><%=jsonStr %>