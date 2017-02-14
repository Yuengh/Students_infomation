package com.pyh.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pyh.employee.Dao.DepartmentDao;
import com.pyh.employee.domain.Department;
import com.pyh.employee.domain.PageBean;
import com.pyh.employee.service.DepartmentService;
/*
 * 部门管理的业务层的实现类
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	//注入部门管理的DAO
	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	//分页查询部门的方法
	public PageBean<Department> findByPage(Integer currPage) {
		// TODO Auto-generated method stub
		PageBean<Department> pageBean = new PageBean<Department>();
		//首先封装当前的页数
		pageBean.setCurrPage(currPage);
		//封装每页显示记录数
		int pageSize=3;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount= departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总的页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页所显示的数据
		int begin = (currPage -1)*pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	//业务层保存业务的方法
	public void save(Department department) {
		departmentDao.save(department);
		
	}
	//根据部门的ID查询部门的方法
	@Override
	public Department findById(Integer did) {
		// TODO Auto-generated method stub
		return departmentDao.findById(did);
	}
	//实现业务的更新操作
	@Override
	public void update(Department department) {
		departmentDao.update(department);
		
	}
	//实现业务的删除操作
	@Override
	public void delete(Department department) {
		departmentDao.delete(department);
		
	}
	//查询所有部门的方法
	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentDao.findAll();
	}
	
}
