package com.pyh.employee.action;

import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pyh.employee.domain.Department;
import com.pyh.employee.domain.Employee;
import com.pyh.employee.domain.PageBean;
import com.pyh.employee.service.DepartmentService;
import com.pyh.employee.service.EmployeeService;

/*
 * 员工管理的Action类
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	//模型驱动使用的对象
	private Employee employee = new Employee();
	@Override
	public Employee getModel() {
		
		return employee;
	}
	//注入业务层的实现方法
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/*
	 * 登陆执行的方法
	 */
	public String login(){
		System.out.println("login方法执行了");
		//调用业务层的方法
		Employee existEmployee  = employeeService.login(employee);
		if (existEmployee==null) {
			//登陆失败
			this.addActionError("用户名或者密码错误");
			return INPUT;
		}else {
			//登陆成功
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}		
	}
	//接收当前的页数
	private Integer currPage=1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	/*
	 * 当前页数的员工查询
	 */
	public String findAll(){
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		//将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public String saveUI(){
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	public String save(){
		employeeService.save(employee);
		return "saveSuccess";
	}
	public String edit(){
		employee = employeeService.findById(employee.getEid());
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
	}
	public String update(){
		employeeService.update(employee);
		return "updateSuccess";
	}
	public String delete(){
		employee = employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}

	
}
