package com.pyh.employee.service;

import java.util.List;

import com.pyh.employee.domain.Department;
import com.pyh.employee.domain.PageBean;

/*
 * 部门管理的业务层的接口类
 */
public interface DepartmentService {

	PageBean<Department> findByPage(Integer currPage);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
