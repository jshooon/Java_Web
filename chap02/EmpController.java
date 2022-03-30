package com.tjoeun.servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.EmpDAO;
import com.tjoeun.svc.EmpService;
import com.tjoeun.vo.EmpVO;

@WebServlet("/emp") // demo/emp?cmd=list
public class EmpController extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		EmpService svc = new EmpService(request);
		String viewPath = svc.exec();
		getServletContext().getRequestDispatcher(viewPath).forward(request, response);
	}
}
