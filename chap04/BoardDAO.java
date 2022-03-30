package com.tjoeun.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.tjoeun.vo.BoardVO;

public class BoardDAO {
	public static final String fname = "C:/test/BBS.txt";
	
	public boolean save(BoardVO board) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fname, true));
			pw.println(board);
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
		
	public List<BoardVO> getList(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = null;
			List<BoardVO> list = new ArrayList<>();
			while((line = br.readLine()) != null) {
				BoardVO board = new BoardVO();
				String[] token = line.split("\\|");
				board.setNum(Integer.parseInt(token[0]));
				board.setAuthor(token[1]);
				board.setTitle(token[2]);
				board.setContents(token[3]);
				java.sql.Date date = java.sql.Date.valueOf(token[4]); // 문자열 -> sql데이터 변환.
				board.setSqldate(date);
				list.add(board);
			}
			br.close();
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<BoardVO> list(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = null;
			List<BoardVO> list = new ArrayList<>();
			while((line = br.readLine()) != null) {
				BoardVO board = new BoardVO();
				String[] token = line.split("\\|");
				board.setNum(Integer.parseInt(token[0]));
				board.setAuthor(token[1]);
				board.setTitle(token[2]);
				board.setContents(token[3]);
				java.sql.Date date = java.sql.Date.valueOf(token[4]); // 문자열 -> sql데이터 변환.
				board.setSqldate(date);
				list.add(board);
			}
			br.close();
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public BoardVO getBoard(String num) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] token = line.split("\\|");				
					if(token[0].equals(num)){
						BoardVO vo = new BoardVO();
						vo.setNum(Integer.valueOf(token[0]));
						vo.setAuthor(token[1]);
						vo.setTitle(token[2]);
						vo.setContents(token[3]);
						vo.setSqldate(java.sql.Date.valueOf(token[4]));
						br.close();
						return vo;
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean update(BoardVO board) {
		//웹브라우져에서 받은 데이터값과 파일에 있는 데이터값을 바꾼다. 
		// 파일데이터 메모리에 로드, 동일한 레코드 검색, 새 데이터로 갱신,
		// 갱신된 리스트를 파일에 덮어쓰기.
			List<BoardVO> list = list();
			int idx = list.indexOf(board); //리스트자료형의 indexOf 메소드는 리스트에 들어있는 데이터와 보드의 동일한 데이터를 찾으라는 메소드.(동일성 검사)
			if(idx < 0) { // 음수가 나와야 돌아가지않는다.따라서 돌아간다.
				return false;
			}
			list.get(idx).setTitle(board.getTitle());
			list.get(idx).setContents(board.getContents());
			return overwrite(list);
			
//			위의 코드와 같다.			
//			List<BoardVO> list = list();
//			for(int i = 0; i < list.size(); i++) {
//				if(list.get(i).equals(board)) {
//					list.get(i).setTitle(board.getTitle());
//					list.get(i).setContents(board.getContents());
//					return overwrite(list);
//				}
//			}
//		return false;
	}
	
	public boolean overwrite(List<BoardVO> list) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fname)); // true를 쓰지않아서 덮어쓰기가 된다.
			for(int i = 0; i < list.size(); i++) {
				pw.println(list.get(i)); // println에 객체를 넣는다면, toString이 호출되어 데이터가 정렬되어 파일에 저장된다.				
			}
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(String num) {
		// 파일로드, 메모리에서 해당 글 삭제, 변경된 리스트를 파일에 덮어쓰기.
		List<BoardVO> list = getList();
		BoardVO board = new BoardVO();
		int sNum = Integer.valueOf(num);
		board.setNum(sNum);
		
		boolean deleted = list.remove(board); // 동일 데이터 찾아서 삭제하기.
		
		if(deleted) return overwrite(list);
		else return false;
	}
	
}
