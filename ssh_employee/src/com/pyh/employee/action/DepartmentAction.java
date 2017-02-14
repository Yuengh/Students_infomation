package com.pyh.employee.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pyh.employee.domain.Department;
import com.pyh.employee.domain.PageBean;
import com.pyh.employee.service.DepartmentService;

/*
 * 部门管理的Action的类
 */
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {
	//模型驱动使用的对象
	private Department department = new Department();
	@Override
	public Department getModel() {
		// TODO Auto-generated method stub
		return department;
	}
	//分页的方法
	private Integer currPage =1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	//注入部门管理的service
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	//提供一个查询的方法
	public String findAll(){
		PageBean<Department> pageBean = departmentService.findByPage(currPage);
		//将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findAll";
		
	}
	//跳转到添加部门的页面的方法--增
	public String saveUI(){
		return "saveUI";
	}
	//添加部门的执行方法--增
	public String save(){
		departmentService.save(department);
		return "saveSuccess";
	}
	//编辑部门的执行的方法--改
	public String edit(){
		department = departmentService.findById(department.getDid());
		return "editSuccess";
	}
	//修改部门的执行的方法---改
	public String update(){
		departmentService.update(department);
		return "updateSuccess";
	}
	public String delete(){
		department = departmentService.findById(department.getDid());
		departmentService.delete(department);
		return "deleteSuccess";
	}
}
