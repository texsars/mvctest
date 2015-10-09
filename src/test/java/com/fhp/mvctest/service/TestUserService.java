package com.fhp.mvctest.service;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fhp.mvctest.dao.AbstractDbUnitTestCase;
import com.fhp.mvctest.entity.User;

public class TestUserService extends AbstractDbUnitTestCase {
	private static IUSerService userService;
	
	@BeforeClass
	public static void init() throws DataSetException, IOException, SQLException {
		ApplicationContext ac =  new FileSystemXmlApplicationContext("classpath:service-context.xml");
		userService = (IUSerService) ac.getBean("userService");
		AbstractDbUnitTestCase.init();
		/*Create dbunit connection and dataset.*/
		
	}
	
	@Test
	public void TestQueryAll() {
		List<User> users = userService.Query(null, 0, 15, null, null);
		assertEquals(15, users.size());
		for(User user : users) {
			System.out.println(user.getId() + "|" + user.getUsername() + "|" + user.getNickname());
//			assertEquals("管理员", user.getNickname());
//			
		}
	}
	
	@Test
	public void TestQueryByUsername() {
		User user = new User();
		user.setUsername("user");
		List<User> users = userService.Query(user, 0, 15, null, null);
		assertEquals(15, users.size());
	}
	
	@Test
	public void TestQueryByNickname() {
		User user = new User();
		user.setNickname("普通用户");
		List<User> users = userService.Query(user, 0, 15, null, null);
		assertEquals(15, users.size());
	}
	
	@Test
	public void TestQueryByNicknameAndUsername() {
		User user = new User();
		user.setNickname("普通用户");
		user.setUsername("user");
		List<User> users = userService.Query(user, 0, 15, null, null);
		assertEquals(15, users.size());
	}
	
	@Test
	public void testGetByUsername() throws SQLException, IOException, DatabaseUnitException {		
		userService.getByUsername("admin");
	}
	
	@Test
	public void TestGetById() {
		User user = userService.getById(1);
		assertEquals("管理员", user.getNickname());
	}
	@Test
	public void TestAdd() throws UserExistsException {
		User u = new User();
		u.setUsername("adminx");
		u.setNickname("管理");
		u.setPassword("xxxxx");
		userService.Add(u);
		User user_add = userService.getByUsername("adminx");
//		assertThat(user_add.getId(), equalTo(1));
		assertThat(user_add.getPassword(), equalTo("xxxxx"));
		assertThat(user_add.getNickname(), equalTo("管理"));
	}
	
	@Test(expected=UserExistsException.class)
	public void TestAddAlreadyExists() throws UserExistsException {
		User u = new User();
		u.setUsername("admin");
		u.setNickname("管理");
		u.setPassword("xxxxx");
		userService.Add(u);
	}
	
	@Test
	public void TestUpdate() {
		User u = userService.getById(1);
		u.setNickname("管理员改");
		userService.Update(u);
		User user_update = userService.getByUsername("admin");
	}
	
	@Test
	public void TestDelete() {
		userService.Delete(1);
		User u = userService.getById(1);
		assertThat(u, is(nullValue()));
	}
}
