package service.impl;

import junit.framework.Assert;

import org.junit.Test;

import service.UsersDAO;

import entity.Users;

public class TestUsersDAO {
	@Test
	public void testUsersLogin(){
		Users users = new Users(1,"Yuengh","123");
		UsersDAO uDao = new UsersDAOImpl();
		Assert.assertEquals(true, uDao.usersLogin(users)) ;
		
	}
}
