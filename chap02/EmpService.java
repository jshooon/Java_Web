package com.tjoeun.svc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tjoeun.dao.EmpDAO;
import com.tjoeun.vo.EmpVO;

public class EmpService 
{
	private HttpServletRequest request;
	
	public EmpService(HttpServletRequest req)
	{
		this.request = req;
	}
	
	public String getCMD()
	{
		String cmd = this.request.getParameter("cmd");
		System.out.println("SVC, cmd="+cmd);
		return cmd;
	}

	public String exec() 
	{
		String cmd = getCMD();
		if(cmd==null || cmd.equals("list")) {
			List<EmpVO> list = new EmpDAO().getList();
			request.setAttribute("list", list);
			return "/empList.jsp";
		}
		else if(cmd.equals("find")) {
			String sNum = request.getParameter("num");
			EmpVO emp = new EmpDAO().find(sNum);
			request.setAttribute("emp", emp);
			return "/empDetail.jsp";
		}
		else if (cmd.equals("add")) {
			String sNum = request.getParameter("num");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String line = String.format("%s|%s|%s|%s", sNum, name, phone, email);
			boolean saved = new EmpDAO().addEmp(line);
			request.setAttribute("saved", saved);
			return "/addResult.jsp";
			
		} else if (cmd.equals("edit")) {
			String sNum = request.getParameter("num");
			EmpVO emp = new EmpDAO().find(sNum);
			request.setAttribute("emp", emp);
			return "/empEdit.jsp";
			
		} else if (cmd.equals("update")) {
			String sNum = request.getParameter("num");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			// ��������� �޸𸮿� �ε��ϰ� �޸𸮿��� ������ �� ���Ͽ� �ݿ��Ѵ�
			int keyNum = Integer.parseInt(sNum);
			EmpDAO dao = new EmpDAO();
			List<EmpVO> list = dao.getList();
			for (int i = 0; i < list.size(); i++) {
				EmpVO emp = list.get(i);
				if (emp.getNum() == keyNum) { // ������� ���� ã��
					emp.setPhone(phone);
					emp.setEmail(email);
					break;
				}
			}
			// �޸𸮿��� ������ ������ ���Ͽ� �����Ѵ�
			boolean updated = dao.overwrite(list);
			request.setAttribute("updated", updated);
			return "/updateResult.jsp";
		}
		else if(cmd.equals("delete")) {
			String sNum = request.getParameter("num");
			int num = Integer.valueOf(sNum);
			EmpDAO dao = new EmpDAO();
			List<EmpVO> list = dao.getList();   // ���Ͽ��� ������� �ε�
			for(int i=0;i<list.size();i++) {
				EmpVO emp = list.get(i);
				if(emp.getNum()==num){          // ������� ������� ã��
					list.remove(i);	            // �޸𸮿��� ������� ����
					break;
				}
			}
			boolean deleted = dao.overwrite(list);  // �޸𸮿��� ���Ϸ� ����
			request.setAttribute("deleted", deleted);
			return "/deleteResult.jsp";
		}
		
		else return null;
	}
}
