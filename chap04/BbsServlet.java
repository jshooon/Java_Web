package com.tjoeun.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.svc.BbsService;

@WebServlet("/bbs")
public class BbsServlet extends HttpServlet {

	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		BbsService svc = new BbsService(request,response);
		String viewPath = svc.exec();
		if (viewPath == null)return; /* 화면바뀌지않는다 */
												/* 응답 */ /* jsp로 보낸다. 브라우져에 출력한다는 뜻. */
		getServletContext().getRequestDispatcher(viewPath).forward(request, response);
	}
}
