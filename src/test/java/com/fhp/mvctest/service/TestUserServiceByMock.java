package com.fhp.mvctest.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fhp.mvctest.dao.IUserDao;
import com.fhp.mvctest.entity.User;

public class TestUserServiceByMock {
	
	private static IUSerService userService;
	
	@BeforeClass
	public static void init() throws DataSetException, IOException, SQLException {
		ApplicationContext ac =  new FileSystemXmlApplicationContext("classpath:service-context.xml");
		userService = (IUSerService) ac.getBean("userService");
		
	}
	@Test
	public void testGetByUsername() {		
		User u = new User();
		u.setUsername("admin");
		u.setPassword("12345");
		u.setNickname("管理员");
		
		IUserDao userDao = EasyMock.createStrictMock(IUserDao.class);
		EasyMock.expect(userDao.getByUsername("admin")).andReturn(u);
		EasyMock.replay(userDao);
		
		((UserService)userService).setUserDao(userDao);
		userService.getByUsername("admin");
		
		assertEquals("admin", u.getUsername());
		assertEquals("12345", u.getPassword());
		assertEquals("管理员", u.getNickname());
		
		EasyMock.verify(userDao);
	}
	
	@Test
	public void testAdd() {	
		User u = new User();
		u.setId(1);
		u.setUsername("admin");
		u.setPassword("12345");
		u.setNickname("管理员");
		
		IUserDao userDao = EasyMock.createStrictMock(IUserDao.class);
		userDao.Add(u);
		EasyMock.expectLastCall().andAnswer(new IAnswer<User>() {

			@Override
			public User answer() throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
	}
}
