package com.tjoeun.svc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.vo.BoardVO;

public class BbsService 
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BbsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String exec() 
	{
		String cmd = request.getParameter("cmd");
		String viewPath = null;
		
		if(request.getSession().getAttribute("uid")==null) {
			request.getSession().setAttribute("target", "/demo/bbs?cmd=inputForm");
			viewPath = "/login/loginForm.jsp";	
		}
		
		else if(cmd==null || cmd.equals("inputForm")) {
			viewPath = "/bbs/board_input.jsp";
		}
		
		else if(cmd.equals("save")) {
			String num = request.getParameter("num");
			String uid = (String)request.getSession().getAttribute("uid");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			java.util.Date today = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());
			
			// 현재 저장된 마지막 글번호를 구하여 1을 더하여 새로 저장할 글번호 계산.
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.getList();
			int boardNum = 1;
			if(list.size() !=0) {
				boardNum = list.get(list.size()-1).getNum()+1;
			}
				
				BoardVO board = new BoardVO();
				board.setNum(boardNum);
				board.setAuthor(uid);
				board.setTitle(title);
				board.setContents(contents);
				board.setSqldate(sqlDate);
			
			boolean saved = dao.save(board);
			
			
			try {
				PrintWriter out = response.getWriter(); //응답을 위한 통로선로
				out.print(String.format("{\"saved\": %b}", saved));/* 브라우저에 도착한다. les에 보낸다*/
										//  중괄호 도 들어가야한다. 따옴표가 출력되기위하여. 역슬래쉬 키밸류값. 
				out.flush(); //데이터가 너무 작아서 메모리에 찰 때까지 기다리기때문에 플러쉬사용하여 강제로 내보낸다.
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// �ۼ���, ����, �۳���, �ۼ���
		}
		
		else if(cmd.equals("list")) {
			BoardDAO dao = new BoardDAO(); /* 데이터 입출력을 위해 DAO생성 */
			List<BoardVO> list = dao.getList(); /* 파일에서 여러 객체의(List<BoardVO>) 데이터를 가져와서 List<BoardVO>에 저장 */
			request.setAttribute("list", list); /* jsp에서 사용할 수 있도록 request영역에 저장 */
			viewPath = "/bbs/BoardList.jsp";
		}
		else if(cmd.equals("add")) {
			String num = request.getParameter("num");
			String uid = (String)request.getSession().getAttribute("uid");
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			java.util.Date today = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(today.getTime());
			
			
			BoardDAO dao = new BoardDAO();
			List<BoardVO> list = dao.getList();
			int boardNum = 1;
			if(list.size() !=0) {
				boardNum = list.get(list.size()-1).getNum()+1;
			}
				
				BoardVO board = new BoardVO();
				board.setNum(boardNum);
				board.setAuthor(uid);
				board.setTitle(title);
				board.setContents(contents);
				board.setSqldate(sqlDate);
		}
		
		else if(cmd.equals("detail")) {
			String num = request.getParameter("num");
			BoardDAO dao = new BoardDAO();
			BoardVO board = dao.getBoard(num);
			request.setAttribute("board", board);
			viewPath = "/bbs/detail.jsp";
			
		}
		
		else if(cmd.equals("edit")) {
			String strNum = request.getParameter("num");
			BoardVO board = new BoardDAO().getBoard(strNum);
			request.setAttribute("board", board);
			viewPath = "/bbs/board_edit.jsp";
		}
		
		else if(cmd.equals("update")) {
			// 파라미터 접수, BoardVO에 저장, 
			// BoardDAO에게 전달하여 갱신요청, 그 결과를 json문자열화.
			int num = Integer.parseInt(request.getParameter("num"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");	

				BoardVO board = new BoardVO();
				board.setNum(Integer.valueOf(num));
				board.setTitle(title);
				board.setContents(contents);
				
				BoardDAO dao = new BoardDAO();
				boolean updated = dao.update(board);

			try {
				PrintWriter out = response.getWriter(); //요청한 클라이언트 주소를 받아서 응답한다.
				out.printf("{\"updated\" : %b}", updated);
				out.flush(); // close를 안하는 이유는 출력할 것이 남아있기때문.
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		else if(cmd.equals("delete")) {
			String num = request.getParameter("num");
			BoardDAO dao = new BoardDAO();
			boolean deleted = dao.delete(num);
			try {
				PrintWriter out = response.getWriter();
				out.printf("{\"deleted\" : %b}" , deleted);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return viewPath;
	}	// end of exec() method

}
