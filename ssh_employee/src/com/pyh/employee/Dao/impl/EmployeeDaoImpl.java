package com.pyh.employee.Dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;



import com.pyh.employee.Dao.EmployeeDao;
import com.pyh.employee.domain.Employee;
//员工管理的DAO的实现类
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao  {
	//Dao中根据用户名和密码查询用户的方法
	@Override
	public Employee findByUsernameAndPassword(Employee employee) {
		String hql="from Employee where username=? and password =?";
		List<Employee> list = (List<Employee>) this.getHibernateTemplate().find(hql, employee.getUsername(),employee.getPassword());
		if (list.size()>0) {
			return list.get(0);
		} 
		return null;
	}
	//统计员工的个数
	@Override
	public int findCount() {
		String hql = "select count(*) from Employee";
		 List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		 if (list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询
	@Override
	public List<Employee> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		List<Employee> list = (List<Employee>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}
	//添加员工
	@Override
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);
	}
	//DAO中的根据员工ID查询员工的方法
	@Override
	public Employee findById(Integer eid) {
		
		return this.getHibernateTemplate().get(Employee.class, eid);
	}
	//DAO中修改员工的方法
	@Override
	public void update(Employee employee) {
		this.getHibernateTemplate().update(employee);
		
	}
	//DAO中删除员工的方法
	@Override
	public void delete(Employee employee) {
		this.getHibernateTemplate().delete(employee);
		
	}
	
	
}
