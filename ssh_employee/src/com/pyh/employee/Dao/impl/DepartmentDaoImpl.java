package com.pyh.employee.Dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.pyh.employee.Dao.DepartmentDao;
import com.pyh.employee.domain.Department;

/*
 * 部门管理的DAO层的实现类
 */
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

	@Override
	public int findCount() {
		String hql = "select count(*) from Department";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询部门
	@Override
	public List<Department> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		List<Department> list = (List<Department>) this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
		return list;
	}
	//DAO保存部门的方法
	@Override
	public void save(Department department) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(department);
	}
	//根据部门的ID查询部门的方法
	@Override
	public Department findById(Integer did) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Department.class, did);
	}
	//DAO中去修改部门的方法
	@Override
	public void update(Department department) {
		this.getHibernateTemplate().update(department);
		
	}
	//DAO中去删除部门的方法
	@Override
	public void delete(Department department) {
		this.getHibernateTemplate().delete(department);
		
	}
	//查询所有部门的方法
	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return (List<Department>) this.getHibernateTemplate().find("from Department");
	}

}
