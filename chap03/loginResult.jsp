<%@ page contentType="application/json; charset=utf-8" pageEncoding="utf-8"%>
<%
	boolean ok = (Boolean) request.getAttribute("ok");
	String jsonStr = String.format("{\"ok\":%b}", ok);
%><%=jsonStr%>