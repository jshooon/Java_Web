package com.tjoeun.svc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.MemberDAO;
import com.tjoeun.vo.MemberVO;

public class MemberService {
	HttpServletRequest request;
	HttpServletResponse response;
	
	
	
	public MemberService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String exec() {
		String cmd = this.request.getParameter("cmd");
		String viewPath = null;
		
		if((cmd == null) || (cmd.equals("list"))) {
			MemberDAO dao = new MemberDAO();
			List<MemberVO> list = dao.getList();
			
			request.setAttribute("list", list);

			viewPath = "/mem/memberList.jsp";
		}
		
		else if(cmd.equals("detail")) {
			String empno = request.getParameter("num");
			MemberVO detail = new MemberDAO().detail(empno);
			request.setAttribute("detail", detail);
			viewPath = "/mem/memberDetail.jsp";
		}
		
		else if(cmd.equals("edit")){
			String empno = request.getParameter("num");
			MemberVO edit = new MemberDAO().detail(empno);
			request.setAttribute("edit", edit);
			viewPath = "/mem/memberEdit.jsp";
		}
		
		else if(cmd.equals("update")) {
			int empno = Integer.parseInt(request.getParameter("num"));
			int deptno = Integer.parseInt(request.getParameter("deptno"));
			int sal = Integer.parseInt(request.getParameter("sal"));
			MemberVO mem = new MemberVO();
			mem.setEmpno(empno);
			mem.setDeptno(deptno);
			mem.setSal(sal);
			MemberDAO dao = new MemberDAO();
			boolean updated = dao.update(mem);
			request.setAttribute("updated", updated);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.printf("{\"updated\":%b}", updated);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(cmd.equals("delete")) {
			int empno = Integer.parseInt(request.getParameter("empno"));
			MemberVO mem = new MemberVO();
			mem.setEmpno(empno);
			boolean deleted = new MemberDAO().memDelete(mem);
			request.setAttribute("deleted", deleted);
			try {
				PrintWriter pw = response.getWriter();
				pw.printf("{\"deleted\" : %b}", deleted );
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(cmd.equals("add")) {
			int empno = Integer.valueOf(request.getParameter("empno"));
			String ename = request.getParameter("ename");
			int deptno = Integer.valueOf(request.getParameter("deptno"));
			java.sql.Date hiredate = java.sql.Date.valueOf(request.getParameter("hiredate"));
			int sal = Integer.valueOf(request.getParameter("sal"));
			
			MemberVO mem = new MemberVO();
			mem.setEmpno(empno);
			mem.setEname(ename);
			mem.setDeptno(deptno);
			mem.setHiredate(hiredate);
			mem.setSal(sal);
			
			try {
				boolean add = new MemberDAO().memAdd(mem);
				PrintWriter pw = response.getWriter();
				pw.printf("{\"add\" : %b}", add); // 성공/ 실패로 결정된 경우.
				pw.flush();
			} catch (SQLIntegrityConstraintViolationException e1) {
				// pk가 중복되어 DB에서 오류가 발생한 경우.
				// System.err.println("PK 중복 오류 발생");
				try {
					PrintWriter pw = response.getWriter();
					pw.printf("{\"add\" : false, \"cause\" : \"PK중복됨\"}");
					pw.flush();
				} catch (IOException e) {}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return viewPath;
	}
	
}
