/*
 파일이름 : GugudanService.java
 작 성 자 : 지 성훈
 작 성 일 : 2022. 03. 02(금)
 프로그램 설명 : JSP 사용법에 대한 예제 구구단 실습 내용.
 */
package com.tjoeun.svc;

import javax.servlet.http.HttpServletRequest;

public class GugudanService {
	private HttpServletRequest req;
	
	public GugudanService(HttpServletRequest req){
		this.req = req;
	}
	
	public int getDan() {
		String sDan = req.getParameter("dan");
		int dan = 0;
		if(sDan == null || sDan.equals("")) {
			sDan = "2";
		}
		dan = Integer.valueOf(sDan);
		return dan;
	}
}
