package com.fhp.mvctest.service;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fhp.mvctest.dao.IUserDao;
import com.fhp.mvctest.entity.User;

public class TestLoginServiceByMock {
	
	private static ILoginService loginService;
	
	@BeforeClass
	public static void init() {
		ApplicationContext ac =  new FileSystemXmlApplicationContext("classpath:service-context.xml");
		loginService = (ILoginService)ac.getBean("loginService");
	}
	
	@Test
	public void testLoginSuccess() {
		User u = new User();
		u.setId(2);
		u.setUsername("admin");
		u.setPassword("12345");
		IUserDao userDao = EasyMock.createStrictMock(IUserDao.class);
		EasyMock.expect(userDao.getByUsername("admin")).andReturn(u);
		EasyMock.replay(userDao);
		
		((LoginService)loginService).setUserDao(userDao);
		int result = loginService.Login("admin", "12345");
		assertEquals(2, result);
		
		EasyMock.verify(userDao);
	}
	
	@Test
	public void testLoginUsernameError() {
		User u = new User();
		u.setId(2);
		u.setUsername("admin");
		u.setPassword("12345");
		IUserDao userDao = EasyMock.createStrictMock(IUserDao.class);
		EasyMock.expect(userDao.getByUsername("admin")).andReturn(null);
		EasyMock.replay(userDao);
		
		((LoginService)loginService).setUserDao(userDao);
		int result = loginService.Login("admin", "12345");
		assertEquals(-1, result);
		
		EasyMock.verify(userDao);
	}
	
	@Test
	public void testLoginPasswordError() {
		User u = new User();
		u.setId(2);
		u.setUsername("admin");
		u.setPassword("12345");
		IUserDao userDao = EasyMock.createStrictMock(IUserDao.class);
		EasyMock.expect(userDao.getByUsername("admin")).andReturn(u);
		EasyMock.replay(userDao);
		
		((LoginService)loginService).setUserDao(userDao);
		int result = loginService.Login("admin", "xxxxx");
		assertEquals(-2, result);
		
		EasyMock.verify(userDao);
	}
}
