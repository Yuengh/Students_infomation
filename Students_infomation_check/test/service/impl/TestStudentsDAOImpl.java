package service.impl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import service.StudentsDAO;

import entity.Students;

public class TestStudentsDAOImpl {
	@Test
	public void testQueryAllStudents(){
		StudentsDAO sDao= new StudentsDAOImpl();
		List<Students> list  = sDao.queryAllStudents();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	/*@Test
	public void testGetNewSid(){
		StudentsDAOImpl sDaoImpl = new StudentsDAOImpl();
		System.out.println(sDaoImpl.getNewSid());
	}*/
	@Test
	public void testAddStudents(){
		Students students = new Students();
		students.setSname("张三丰");
		students.setGender("男");
		students.setBirthday(new Date());
		students.setAddress("武当山");
		StudentsDAO sDao = new StudentsDAOImpl();
		Assert.assertEquals(true, sDao.addStudents(students));
	}
}
