package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tjoeun.vo.MemberVO;


public class MemberDAO {
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
	   
	   public List<MemberVO> getList() { 
		   //DB 연결/ SQL실행/ EMPVo생성/ DB해제/ EmpVo 리턴
		   	this.conn = getConn();
		   	try {
		   		
		   		
		   		//String sql = "SELECT * FROM emp";
		   		String sql = "SELECT * FROM emp";
		   		this.pstmt = conn.prepareStatement(sql); // createStatement(); 메소드는 파라미터서식 SQL문장을 MySQL에 전달하여 실행한 뒤 데이터를 가져온다.
		   		this.rs = pstmt.executeQuery(); 
		   		
		   		List<MemberVO> list = new ArrayList<MemberVO>();
		   		
		   		while (rs.next()){
		   			int empno = rs.getInt("EMPNO");
		   			String ename  = rs.getString("ENAME");
		   			int deptno = rs.getInt("DEPTNO");
		   			java.sql.Date hiredate = rs.getDate("HIREDATE");
		   			int sal = rs.getInt("SAL");
		   			
		   			MemberVO emp = new MemberVO();
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
	   // 선생님이 하신것.
	   public boolean update(MemberVO mem) {
		   // 전달된 데이터로 기존 데이터를 갱신한다.
//				String sql = "UPDATE emp SET deptno="+mem.getDeptno()+", "+
//				"sal="+mem.getSal() +" WHERE empno=" + mem.getEmpno();
				
				//서식 문자열로 표현. 위와 같다.
		   		this.conn = getConn();
				String sql = String.format("UPDATE emp SET deptno=?, sal=? WHERE empno = ?");
				
				try {
					this.pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, mem.getDeptno());
					pstmt.setInt(2, mem.getSal());
					pstmt.setInt(3, mem.getEmpno());
					
					int rows = pstmt.executeUpdate();
					return rows>0 ? true : false;
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					closeAll();
				}
				return false;
			}
	   
	   
	   public MemberVO getEmp(int empno) { 
		   //DB 연결/ SQL실행/ EMPVo생성/ DB해제/ EmpVo 리턴
		   	this.conn = getConn();
		   	try {
		   		
		   		// this.pstmt = conn.createStatement(); // createStatement(); 메소드는 SQL문장을 MySQL에 전달하여 실행한 뒤 데이터를 가져온다.
		   		
		   		//String sql = "SELECT * FROM emp";
		   		
		   		String sql = "SELECT * FROM emp WHERE empno =? "; 
		   		this.pstmt = conn.prepareStatement(sql); // pstmt와는 다르게 변환 문자를 먼저 넘긴다.
		   		pstmt.setInt(1, empno); // 숫자1은 첫번째 물음표에 인자 값을 넣어주라는 뜻.
		   		
		   		//this.rs = pstmt.executeQuery(sql);
		   		this.rs = pstmt.executeQuery(); // 위에서 먼저 넘겼기때문에 괄호안에 sql문장이 들어가지않는다.
		   		
		   		MemberVO emp = null;
		   		
		   		//List<EmpVo> list = new ArrayList<EmpVo>();
		   		// 다음줄의 데이터 한행이 있나 없나 확인을 next로 확인
		   		// 없으면 false
		   		if(rs.next()){
		   			//int empno = rs.getInt("EMPNO");
		   			String ename  = rs.getString("ENAME");
		   			int deptno = rs.getInt("DEPTNO");
		   			java.sql.Date hiredate = rs.getDate("HIREDATE");
		   			int sal = rs.getInt("SAL");
		   			
		   			emp = new MemberVO();
		   			emp.setEmpno(empno);
		   			emp.setEname(ename);
		   			emp.setDeptno(deptno);
		   			emp.setHiredate(hiredate);
		   			emp.setSal(sal);
		   		}
		   		return emp;
		   	}catch(Exception e) {
		   		e.printStackTrace();
		   	}finally {
				closeAll();
			}
		   	return null;
	   }
	   
	   public boolean memAdd(MemberVO mem) 
			   throws java.sql.SQLIntegrityConstraintViolationException { //에러를 잡아서 예외를 던진다. 그래서 경우의 수가 3가지가 된다.
		   this.conn = getConn();
		   
		   String sql = "INSERT INTO emp VALUES(?, ?, ?, ?, ?);";
		   // sql 컬럼의 순서대로 넣어준다. 순서대로 안하려면 emp뒤에 컬럼명을 명시해준다.
		   try {
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem.getEmpno());
			pstmt.setString(2, mem.getEname());
			pstmt.setDate(3, mem.getHiredate());
			pstmt.setInt(4, mem.getDeptno());
			pstmt.setInt(5, mem.getSal());
			
			int rows = pstmt.executeUpdate();
			return rows > 0 ? true : false;
			
		} catch (SQLException e) {
			if(e instanceof java.sql.SQLIntegrityConstraintViolationException) {
				throw (java.sql.SQLIntegrityConstraintViolationException) e;
			}
			e.printStackTrace();
		} finally {
			closeAll();
		}
		   return false;
	   }


	public MemberVO detail(String num) {
		List<MemberVO> list = getList();
		int sNum = Integer.parseInt(num);
		for(int i = 0; i < list.size(); i++) {
			if(sNum == list.get(i).getEmpno()) {
				MemberVO vo = new MemberVO();
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

	public boolean memDelete(MemberVO mem) {
		this.conn = getConn();
		
		String sql = "DELETE FROM emp WHERE empno = ?;";
		try {
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem.getEmpno()); //sql 완성
			int rows = pstmt.executeUpdate(); //sql에서 실행되고, 실행된 수를 리턴반환.
			return rows > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}

}
