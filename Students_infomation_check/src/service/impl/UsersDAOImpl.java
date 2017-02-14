package service.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;

import entity.Users;
import service.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public boolean usersLogin(Users u) {
		// TODO Auto-generated method stub
		//创建事务对象
		Transaction transaction = null;
		String hql="";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//开启事务
			transaction = session.beginTransaction();
			hql = "from Users where username=? and password=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUsername());
			query.setParameter(1, u.getPassword());
			List list = query.list();
			transaction.commit();//提交事务
			if (list.size()>0) {
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			if (transaction!=null) {
				
				transaction = null;
				
			}
		}
	}
	

}
