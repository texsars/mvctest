package com.fhp.mvctest.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fhp.mvctest.entity.User;

public class TestUserService {
	private static IUSerService userService;
	
	@BeforeClass
	public static void setUp() {
		ApplicationContext ac =  new FileSystemXmlApplicationContext("src/main/resources/service-context.xml");
		userService = (IUSerService) ac.getBean("userService");
	}
	
	@Test
	public void TestQuery() {
		User u = new User();
		u.setUsername("adminx");
		List<User> users = userService.Query(u, 0, 15, null, null);
		assertEquals(1, users.size());
		for(User user : users) {
			System.out.println(user.getId() + "|" + user.getUsername() + "|" + user.getNickname());
			assertEquals("管理", user.getNickname());
			
		}
	}
	
	@Test
	public void TestGetByUsername() {
		User user = userService.getByUsername("adminx");
		assertEquals("管理", user.getNickname());
	}
	
	@Test
	public void TestGetById() {
		User user = userService.getById(1);
		assertEquals("管理", user.getNickname());
	}
//	@Test
	public void TestAdd() throws UserExistsException {
		User u = new User();
		u.setUsername("adminx");
		u.setNickname("管理");
		u.setPassword("xxxxx");
		userService.Add(u);
	}
	
//	@Test(expected=UserExistsException.class)
	public void TestAddAlreadyExists() throws UserExistsException {
		User u = new User();
		u.setUsername("adminx");
		u.setNickname("管理");
		u.setPassword("xxxxx");
		userService.Add(u);
	}
	
//	@Test
	public void TestUpdate() {
		User u = userService.getById(1);
		u.setNickname("管理员");
		userService.Update(u);
	}
	
//	@Test
	public void TestDelete() {
		userService.Delete(1);
		User u = userService.getById(1);
	}
}
