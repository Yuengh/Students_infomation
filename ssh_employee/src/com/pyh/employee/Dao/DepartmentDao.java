package com.pyh.employee.Dao;

import java.util.List;

import com.pyh.employee.domain.Department;

/*
 * 部门管理的DAO层的接口类
 */
public interface DepartmentDao {

	int findCount();

	List<Department> findByPage(int begin, int pageSize);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();

}
