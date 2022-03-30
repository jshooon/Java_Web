package com.tjoeun.vo;

import java.util.Objects;

public class BoardVO2 {
	int num;
	String author;
	String title;
	String contents;
	java.sql.Date sqldate;
	
	public BoardVO2() {}
	
	public BoardVO2(int num, String title) {
		this.num = num;
		this.title = title;
	}

	@Override
	public boolean equals(Object obj) {
		BoardVO v = (BoardVO)obj;
		if(this.num == v.getNum()) {
			return true;
		}else {
			return false;			
		}
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.num);
	}
//	@Override
//	public String toString() {
//		return String.format("%d|%s|%s|%s|%s", num, author, title, contents, sqldate.toString());
//	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getStr() {
		return String.format("%d|%s|%s|%s|%s", num, author, title, contents, sqldate);
	}
	public java.sql.Date getSqldate() {
		return sqldate;
	}
	public void setSqldate(java.sql.Date sqldate) {
		this.sqldate = sqldate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

}
