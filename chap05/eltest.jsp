<%@page import="com.tjoeun.vo.BoardVO2"%>
<%@page import="java.util.*"%>
<%@page import="com.tjoeun.vo.BoardVO"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%
	//String msg = "Hello";
	//request.setAttribute("msg","Hello");
	BoardVO board = new BoardVO();
	board.setNum(11);
	board.setAuthor("Koon");
	board.setTitle("게시판 테스트");
	board.setContents("많이 사용해 주세요");
	
	List<String> list = new ArrayList<>();
	list.add("Scott");
	list.add("Blake");
	list.add("Jone");
	list.add("Smith");
	list.add("King");
	
	List<BoardVO2> list2 = new ArrayList<>();
	list2.add(new BoardVO2(11,"게시판테스트"));
	list2.add(new BoardVO2(12,"안녕하세요 ?"));
	list2.add(new BoardVO2(13,"반갑습니다."));
	list2.add(new BoardVO2(14,"감사합니다."));
	list2.add(new BoardVO2(15,"성공하세요."));
		
	pageContext.setAttribute("board", board);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("list2", list2);
	// Scope Object(영역객체) : pageContext, request, session, application.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>EL 테스트</title>
</head>
<body>
<!-- EL(Expression Language), 메모리상의 데이터를 화면에출력. -->

<div>1. Hello</div> <!-- 1 정적인 문자열 -->
<%
	BoardVO b = (BoardVO)pageContext.getAttribute("board");
	out.println("번호" + b.getNum());
%>
<div>제목 : <%=b.getTitle()%></div> <!-- 메모리상의 문자열 익스프레션을 이용하여 화면에 출력. -->
 <!-- 2,3 동적인 문자열 -->
<div>
<%
	String msg = (String)request.getAttribute("msg");
	out.println(msg);
%>
<%
	List<String> names = (List<String>) pageContext.getAttribute("list");
	for(int i = 0; i < names.size(); i++){
		String name = names.get(i); %>
		<div><%=name %></div>
<%
	}
%>

</div>
<div>EL표기법 화면출력</div>
<div>${board.contents}</div> <!-- EL을 사용하여 BoardVO에있는 컨텐츠 호출법 -->
<div>${msg}</div> <!-- EL표기법 화면출력 -->
<div>${pageScope.msg}</div>
<div>${ 1 + 5 }</div> 
<div> 1 + 5 </div>
<div>${list[0]}</div>
<div>${list[1]}</div>
<div>${list2[0].num},${list2[0].title}</div>
<!-- JSTL 루프를 사용하기 위해서 라이브러리가 요구됨. -->
<!-- https://mvnrepository.com/artifact/javax.servlet/jstl/1.2 jar 다운! -->
<!-- webapp/WEB-INF/lib/jstl-1.2.jar넣기 -->
<c:forEach var="b" items="${list2}">   <!-- 태그이름 foEach 프리픽스는 c -->
	<div>${b.num},${b.title}</div>
</c:forEach>

<c:forEach begin="0" end="4" varStatus="status">
	<div>${list2[status.index].num }, ${list2[status.index].title }</div>
</c:forEach>
</body>
</html>