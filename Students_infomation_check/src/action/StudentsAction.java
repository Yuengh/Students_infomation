package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entity.Students;

import service.StudentsDAO;
import service.impl.StudentsDAOImpl;

//学生Action类
public class StudentsAction extends SuperAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//实现查询所有学生的动作
	public String query(){
		StudentsDAO sDao = new StudentsDAOImpl();
		List<Students> list = sDao.queryAllStudents();
		//传进来的集合放到session中
		if (list!=null&&list.size()>0) {
			session.setAttribute("students_list", list);
			
		}
		return "query_success";
	}
	//删除学生动作
	public String delete(){
		StudentsDAO sDao = new StudentsDAOImpl();
		String sid = request.getParameter("sid");
		sDao.deleteStudents(sid);//调用删除方法
		return "delete_success";
	}
	//添加学生
	public String add() throws Exception{
		Students students = new Students();
		students.setSname(request.getParameter("sname"));
		students.setGender(request.getParameter("gender"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		students.setBirthday(sdf.parse(request.getParameter("birthday")));
		students.setAddress(request.getParameter("address"));
		StudentsDAO sDao = new StudentsDAOImpl();
		sDao.addStudents(students);
		return "add_success";
	}
	//修改学生动作
	public String modify(){
		//获得传递过来的学生编号
		String sid =request.getParameter("sid");
		StudentsDAO sDao = new StudentsDAOImpl();
		Students students = sDao.queryStudentsBySid(sid);
		//保存在会话中
		session.setAttribute("modify_students", students);
		return "modify_success";
		
	}
	//保存修改后的学生动作
	public String save() throws Exception{
		Students students = new Students();
		students.setSid(request.getParameter("sid"));
		students.setSname(request.getParameter("sname"));
		students.setGender(request.getParameter("gender"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		students.setBirthday(sdf.parse(request.getParameter("birthday")));
		students.setAddress(request.getParameter("address"));
		StudentsDAO sDao = new StudentsDAOImpl();
		sDao.updateStudents(students);
		return "save_success";
	}
}
