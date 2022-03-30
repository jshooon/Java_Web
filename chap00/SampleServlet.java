package com.tjoeun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample")
public class SampleServlet extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service()");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println(" span { color:red; }");
		out.println("</style>");
		out.println("<br>");
		out.println("ÀÀ´ä µµÂø~");
		out.println("</head>");
		out.println("<body>");
		out.println("<span>Hello</span> your response~");
		out.println("<br>");
		out.println("ÀÀ´ä µµÂø~");
		out.println("</body>");
		out.println("</html>");
	}
	/*
	 * @Override protected void doGet(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * System.out.println("doGet()");
	 * //response.getWriter().append("Served at: ").append(request.getContextPath())
	 * ; }
	 * 
	 * @Override protected void doPost(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * System.out.println("doPost()"); doGet(request, response); }
	 */

}
