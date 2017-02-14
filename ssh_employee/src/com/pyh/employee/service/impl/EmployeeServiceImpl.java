package com.pyh.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pyh.employee.Dao.EmployeeDao;
import com.pyh.employee.domain.Department;
import com.pyh.employee.domain.Employee;
import com.pyh.employee.domain.PageBean;
import com.pyh.employee.service.EmployeeService;
//员工管理的业务层实现类
@Transactional
public class EmployeeServiceImpl  implements EmployeeService{
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	//业务层的登陆的方法
	@Override
	public Employee login(Employee employee) {
		Employee existEmployee  = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}
	@Override
	//业务层的分页查询的方法
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		//封装当前的页数
		pageBean.setCurrPage(currPage);
		//封装每页显示多少条记录
		int pageSize=3;
		pageBean.setPageSize(pageSize);
		//封装显示一共多少条记录
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装一共有多少页
		double no = totalCount;
		Double num = Math.ceil(no/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每一页所显示的集合数据
		int begin = (currPage-1)*pageSize;
		List<Employee> list = employeeDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	//添加员工信息
	@Override
	//业务层保存员工的方法
	public void save(Employee employee) {
		employeeDao.save(employee);
		
	}
	//业务层根据员工id查询的方法
	@Override
	public Employee findById(Integer eid) {
		
		return employeeDao.findById(eid);
	}
	@Override
	//业务层修改员工的方法
	public void update(Employee employee) {
		employeeDao.update(employee);
		
	}
	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
		
	}
	
	
	
}
