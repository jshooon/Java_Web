package com.tjoeun.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewJDBC {
	   public static void main(String[] args){
		   ReviewJDBC dao = new ReviewJDBC();
		   List<EmpVo> list = dao.getList();
		   for(int i = 0; i < list.size(); i++) {
			   System.out.println(list.get(i));
		   }
		   
		   EmpVo v = new ReviewJDBC().getEmp(12);
		   System.out.println(v);
		   
	      // Connection클래스는 MySQL에 연결통로를 만드는 클래스.
	      Connection conn = null;
	      // Statement클래스는 MySQL에 데이터를 전송하기 위한 클래스. 
	      Statement stmt = null;
	      try{
	         System.out.println("Connecting to database...");
	         
	         // DB에 연결하여 데이터를 가져온다. 
	         //mydb 부분은 MySQL의 스키마 이름이다. 따라서 가져올 데이터가 들어있는 스키마명을 입력한다.
	         String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
	         Class.forName("com.mysql.cj.jdbc.Driver"); //JAVA에서 MySQL을 접속가능하도록  자바에는 데이터베이스를 연결하려는 인터페이스가 있다. 자바.sql
	         conn = DriverManager.getConnection(url, "root", "tjoeun");
	   
	         System.out.println("Creating statement...");
	         stmt = conn.createStatement();
	         
	         // sql에 가져올 데이터를 저장한다.
	         String sql = "SELECT * FROM emp";
	         // executeQuery();메소드는 MySQL에 전송되어 실행되고 Java로 리턴된다.
	         // 가져올 데이터를 executeQuery();메소드 괄호안에 넣고,
	         // 리턴된 메모리집합이 rs에 담긴다. 
	         ResultSet rs = stmt.executeQuery(sql); 
	         
	         // next를 한다면 행의 맨 처음행으로 지정되고,
	         // 한번 돌고 다음행으로 포인트가 지정된다.
	         // 포인트가 낮춰지면서 반복한다.
	         while(rs.next()){
	            //Retrieve by column name
	        	// SQL에서 가져온 데이터를 변수를 지정하여 저장한다.
	        	int empno = rs.getInt("EMPNO");
	            String ename  = rs.getString("ENAME");
	            int deptno = rs.getInt("DEPTNO");
	            java.sql.Date hiredate = rs.getDate("HIREDATE");
	            int sal = rs.getInt("SAL");
	            //Display values
	            // 변수에 저장된 데이터를 출력한다.
	            System.out.printf("%d\t%s\t%d\t%s\t%d \n",
	            		empno,ename,deptno,hiredate,sal);
	         }
	   
	         rs.close();
	         stmt.close();
	         conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{
	            if(stmt!=null)
	               stmt.close();
	         }catch(SQLException se2){
	            try{
	               if(conn!=null)
	                  conn.close();
	            }catch(SQLException se){
	               se.printStackTrace();
	            }
	         }//end of catch
	         System.out.println("\nGoodbye!");
	      }//end finally
	   }//end of main()
	   
	   // list 가져오는 기능 메소드
	   public List<EmpVo> getList(){
		   // DB연결
		   // SQL 실행
		   // RESULT SET에서 emp컬럼값 추출. 
		   // EmpVo 객체 생성
		   // List 담기
		   // List 리턴
			    //Connection클래스는 MySQL에 연결통로를 만드는 클래스.
			     Connection conn = null;
			    // Statement클래스는 MySQL에 데이터를 전송하기 위한 클래스. 
			     Statement stmt = null;
		   try{
		         System.out.println("Connecting to database...");
		         
		         // DB에 연결하여 데이터를 가져온다. 
		         //mydb 부분은 MySQL의 스키마 이름이다. 따라서 가져올 데이터가 들어있는 스키마명을 입력한다.
		         String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
		         Class.forName("com.mysql.cj.jdbc.Driver"); //JAVA에서 MySQL을 접속가능하도록  자바에는 데이터베이스를 연결하려는 인터페이스가 있다. 자바.sql
		         conn = DriverManager.getConnection(url, "root", "tjoeun");
		   
		         System.out.println("Creating statement...");
		         stmt = conn.createStatement();
		         
		         String sql = "SELECT * FROM emp";
		         ResultSet rs = stmt.executeQuery(sql); 
		         
		         List<EmpVo> list = new ArrayList<EmpVo>();
		         
		         while(rs.next()){
		        	int empno = rs.getInt("EMPNO");
		            String ename  = rs.getString("ENAME");
		            int deptno = rs.getInt("DEPTNO");
		            java.sql.Date hiredate = rs.getDate("HIREDATE");
		            int sal = rs.getInt("SAL");

		            EmpVo emp = new EmpVo();
		            emp.setEmpno(empno);
		            emp.setEname(ename);
		            emp.setDeptno(deptno);
		            emp.setHiredate(hiredate);
		            emp.setSal(sal);
		            list.add(emp);
		         }
		         rs.close();
		         stmt.close();
		         conn.close();
		         return list;
		      }catch(Exception e){
			         e.printStackTrace();
		      }
		   return null;
	   }
	   
	   public EmpVo getEmp(int no){
		   List<EmpVo> list = getList();
		   for(int i = 0; i < list.size(); i++) {
			   if(no == list.get(i).getEmpno()) {
				   EmpVo vo = new EmpVo();
				   vo.setEmpno(list.get(i).getEmpno());
				   vo.setEname(list.get(i).getEname());
				   vo.setDeptno(list.get(i).getDeptno());
				   vo.setHiredate(list.get(i).getHiredate());
				   vo.setSal(list.get(i).getSal());
				   return vo;
			   }
		   }
		   return null;
	   }
}