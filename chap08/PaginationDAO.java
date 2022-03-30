package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tjoeun.vo.PaginationVO;

public class PaginationDAO {
	   private Connection conn;
	   private PreparedStatement pstmt; 
	   private ResultSet rs;
	   
	   private Connection getConn() {
		      try{		         
		         String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
		         Class.forName("com.mysql.cj.jdbc.Driver"); //JAVA에서 MySQL을 접속가능하도록  자바에는 데이터베이스를 연결하려는 인터페이스가 있다. 자바.sql
		         Connection conn = DriverManager.getConnection(url, "root", "tjoeun"); // DB연결
		         return conn; // 커넥션 객체가 return되어 연결됨.
		      }catch(Exception ex) {
		    	 System.err.println("DB 접속 실패!");
		    	 ex.printStackTrace();
		      }
		   return null;
	   }
	   
	   private void closeAll() {
		   try {
			   if(rs!=null) {
				   rs.close();
			   }
			   if(pstmt!=null) {
				   pstmt.close();
			   }			   
			   if(conn!=null) {
				   conn.close();
			   }
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    }
		   
	   }
	   
	   public List<PaginationVO> getList() { 
		   //DB 연결/ SQL실행/ EMPVo생성/ DB해제/ EmpVo 리턴
		   	this.conn = getConn();
		   	try {
		   		//String sql = "SELECT * FROM emp";
		   		String sql = "SELECT * FROM emp";
		   		this.pstmt = conn.prepareStatement(sql); // createStatement(); 메소드는 파라미터서식 SQL문장을 MySQL에 전달하여 실행한 뒤 데이터를 가져온다.
		   		this.rs = pstmt.executeQuery(); 
		   		
		   		List<PaginationVO> list = new ArrayList<PaginationVO>();
		   		
		   		while (rs.next()){
		   			int empno = rs.getInt("EMPNO");
		   			String ename  = rs.getString("ENAME");
		   			int deptno = rs.getInt("DEPTNO");
		   			java.sql.Date hiredate = rs.getDate("HIREDATE");
		   			int sal = rs.getInt("SAL");
		   			
		   			PaginationVO emp = new PaginationVO();
		   			emp.setEmpno(empno);
		   			emp.setEname(ename);
		   			emp.setDeptno(deptno);
		   			emp.setHiredate(hiredate);
		   			emp.setSal(sal);
		   			list.add(emp);
		   		}
		   		return list;
		   	}catch(Exception e) {
		   		e.printStackTrace();
		   	}finally {
				closeAll();
			}
		   	return null;
	   }
	   
	   public List<PaginationVO> getPage(int pnum, int num) { 
		   //DB 연결/ SQL실행/ EMPVo생성/ DB해제/ EmpVo 리턴
		   	this.conn = getConn();
		   	try {
		   		//String sql = "SELECT * FROM emp";
		   		String sql = "SELECT * FROM "
		   				+ "(SELECT * FROM emp LIMIT ?, ?)t1"
		   				+ "CROSS JOIN "
		   				+ "(SELECT COUNT(*)totalcnt FROM emp)t2";
		   		this.pstmt = conn.prepareStatement(sql); // createStatement(); 메소드는 파라미터서식 SQL문장을 MySQL에 전달하여 실행한 뒤 데이터를 가져온다.
		   		pstmt.setInt(1, (pnum-1) * 3);
		   		pstmt.setInt(2, num);
		   		this.rs = pstmt.executeQuery(); 
		   		
		   		List<PaginationVO> list = new ArrayList<PaginationVO>();
		   		
		   		while (rs.next()){
		   			int empno = rs.getInt("EMPNO");
		   			String ename  = rs.getString("ENAME");
		   			int deptno = rs.getInt("DEPTNO");
		   			java.sql.Date hiredate = rs.getDate("HIREDATE");
		   			int sal = rs.getInt("SAL");
		   			
		   			
		   			PaginationVO emp = new PaginationVO();
		   			emp.setEmpno(empno);
		   			emp.setEname(ename);
		   			emp.setDeptno(deptno);
		   			emp.setHiredate(hiredate);
		   			emp.setSal(sal);
		   			list.add(emp);
		   		}
		   		return list;
		   	}catch(Exception e) {
		   		e.printStackTrace();
		   	}finally {
				closeAll();
			}
		   	return null;
	   }
	   
	   public List<Map> getPageTotal(int pnum, int num) { 
		   //DB 연결/ SQL실행/ EMPVo생성/ DB해제/ EmpVo 리턴
		   this.conn = getConn();
		   try {
			   //String sql = "SELECT * FROM emp";
			   String sql = "SELECT * FROM "
					   + "(SELECT * FROM emp LIMIT ?, ?) t1"
					   + "CROSS JOIN "
					   + "(SELECT COUNT(*)totalCnt FROM emp) t2";
			   this.pstmt = conn.prepareStatement(sql); // createStatement(); 메소드는 파라미터서식 SQL문장을 MySQL에 전달하여 실행한 뒤 데이터를 가져온다.
			   pstmt.setInt(1, (pnum-1) * num);
			   pstmt.setInt(2, num);
			   this.rs = pstmt.executeQuery(); 
			   
			   List<Map> list = new ArrayList<>();
			   
			   while (rs.next()){
				   int empno = rs.getInt("EMPNO");
				   String ename  = rs.getString("ENAME");
				   int deptno = rs.getInt("DEPTNO");
				   java.sql.Date hiredate = rs.getDate("HIREDATE");
				   int sal = rs.getInt("SAL");
				   int totalCnt = rs.getInt("TOTALCNT");
//				   System.out.println("총 행수 : " + totalCnt);
				   
				   HashMap<String, Object> map = new HashMap<>();
				   map.put("empno", empno);
				   map.put("ename", ename);
				   map.put("deptno", deptno);
				   map.put("hiredate", hiredate);
				   map.put("sal", sal);
				   map.put("totalCnt", totalCnt);
				   
				   list.add(map);
			   }
			   return list;
		   }catch(Exception e) {
			   e.printStackTrace();
		   }finally {
			   closeAll();
		   }
		   return null;
	   }
}
