package com.tjoeun.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset =utf-8");
		
		String[] hobby = request.getParameterValues("hobby");
		for(int i = 0; i < hobby.length; i++) {
			System.out.println(hobby[i]);
		}
		String gender = request.getParameter("gender");
			System.out.printf("성별 : %s\n", gender);
			
		String subject = request.getParameter("subject");
			System.out.printf("과목 : %s\n", subject);
			
		String mycolor = request.getParameter("mycolor");
			System.out.printf("색상 : %s\n", mycolor);
			
		String level = request.getParameter("level");
			System.out.printf("level : %s\n ", level);
	}

}
