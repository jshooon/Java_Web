/*
 파일이름 : GugudanModel1.java
 작 성 자 : 지 성훈
 작 성 일 : 2022. 03. 02(금)
 프로그램 설명 : JSP 사용법에 대한 예제 구구단 실습 내용.
 */
package com.tjoeun.model;
public class GugudanModel1 {
	// DAO(Data Access Object 데이터 입출력 기능 객체), VO(Value Object 데이터 그 자체)
	public String getGugudan(int dan) {
		
		String sGugu = "";
		for(int i = 1; i <= 9; i++) {
			sGugu += String.format("%d * %d = %d<br>", dan, i, dan * i);
		}
		return sGugu;
	}

}
