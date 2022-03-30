/*
 파일이름 : GugudanBean.java
 작 성 자 : 지 성훈
 작 성 일 : 2022. 03. 02(금)
 프로그램 설명 : JSP java bean규칙 사용법에 대한 실습 내용.
 */
package com.tjoeun.bean;

import javax.servlet.http.HttpServletRequest;

public class GugudanBean {
	public GugudanBean() {
		System.out.println("GugudanBean 생성");
	}
	
	public int getDan(HttpServletRequest request) {
		
		String sDan = request.getParameter("dan"); /* request에 브라우져에서 입력된 요청값이 들어있다 */
		
		if(sDan == null || sDan.equals("")){
			sDan = "2";
		}
		int dan = Integer.parseInt(sDan);
		return dan;
	}
	
	public String getGugu(int dan) {
		String strGugu = "";
		for(int i = 1; i <= 9; i++){
			strGugu += String.format("%d * %d = %d <br>", dan, i, dan * i);
		}
		return strGugu;
	}
}
