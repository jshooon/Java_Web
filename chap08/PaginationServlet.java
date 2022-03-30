package com.tjoeun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.svc.PaginationService;

@WebServlet("/paging")
public class PaginationServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PaginationService svc = new PaginationService(request); // 요청접수
		String viewPath = svc.exec();
		if(viewPath==null) return;
		// JSP에게 보낸다.
		getServletContext().getRequestDispatcher(viewPath).forward(request, response);
		// emp 테이블에서 모든 행을 가져와서 화면에 표시해보세요.
		// webapp/pagination/empList.jsp
	}
}
