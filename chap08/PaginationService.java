package com.tjoeun.svc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tjoeun.dao.PaginationDAO;
import com.tjoeun.util.PageUtill;


public class PaginationService {
	HttpServletRequest request;

	public PaginationService(HttpServletRequest request) {
		this.request = request;
	}
	
	public String exec() {
		String cmd = this.request.getParameter("cmd");
		String viewPath = null;
		
		if((cmd == null) || (cmd.equals("list"))) {
			String sPage = request.getParameter("page");
			int page = (sPage == null) ? 1 : Integer.parseInt(sPage); // 현재 페이지
			
			PaginationDAO dao = new PaginationDAO();
			
			List<Map> list = dao.getPageTotal(page, 2);
			
			int totalCnt = (Integer)list.get(0).get("totalCnt");
//			System.out.println("총 행수 : " + totalCnt);
			int totalPages = (int)Math.ceil(totalCnt/2.0);
//			System.out.printf("총 행수 = %d, 총 페이지수 =%d \n", totalCnt, totalPages);
			
			PageUtill pu = new PageUtill();
			pu.setList(list);  			// 게시 글 목록.
			pu.setRowsPerScreen(2); 	// 화면하나에 보여줄 행수.
			pu.setCurrentPage(page);	// 현재페이지.
			pu.setTotalPage(totalPages);// 총 페이지 수.
			pu.setTotalRows(totalCnt);	// 총 행수.
			pu.setNavCount(5);			// 페이지 표시 수.
			request.setAttribute("pgutil", pu);
			
//			현재 페이지가 7 이라면, 네비게이션 링크를 5개 보여주고자 할 경우,
//			 5 6 7 8 9
			/*
//			현재 페이지-2 했을 때 1보다 작다면 1을 부여, 아니면 페이지-2를 보낸다.
			int low = (page-2 < 1) ? 1 : page-2; 
//			low페이지 +4 했을 때 토탈보다 더 크다면 토탈페이지를 아니라면 low페이지+4를 보낸다.
			int high = (low+4 > totalPages) ? totalPages : low + 4; 
			low = high<=5 ? 1 : high-4;
			// 총 페이지 방 수가 나온다.
			System.out.printf("%d~%d \n", low, high);
			int [] links = new int[(high-low)+1]; // 9-5 = 4 + 1
			
			for(int n = low, i = 0; n <= high; n++, i++){
				links[i] = n;
			}

//			for(int i = 0, k = low; i < (high-low)+1; i++){
//				links[i] = k++;
//			}
//			int[] test = new int[totalPages];
//			for(int i=0; i<totalPages ; i++) {
//				test[i] = i+1;
//			}
//			request.setAttribute("test", test);
			
			
			
			request.setAttribute("totalcnt", totalCnt);
			request.setAttribute("totalpages", totalPages);
			request.setAttribute("links", links);
			request.setAttribute("current", page);
			request.setAttribute("list", list);
			*/
			viewPath = "/pagination/empList.jsp";
		}

		return viewPath;
	}
}
