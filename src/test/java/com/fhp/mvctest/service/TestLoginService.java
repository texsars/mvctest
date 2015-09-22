package com.fhp.mvctest.service;

import static org.junit.Assert.*;

import org.apache.taglibs.standard.lang.jstl.EqualityOperator;

import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestLoginService {
	
	private static ILoginService loginService;
	@BeforeClass
	public static void setUp() {
		ApplicationContext ac =  new FileSystemXmlApplicationContext("classpath:service-context.xml");
		loginService = (ILoginService) ac.getBean("loginService");
	}
	
	@Test
	public void testLoginSuccess() {
		int status = loginService.Login("adminx", "xxxxx");
		assertThat(status, greaterThan(0));
	}
	
	@Test
	public void testUsernameError() {
		int status = loginService.Login("admin", "xxxxx");
		assertThat(status, equalTo(-1));
	}
	
	@Test
	public void testNullArgs() {
		int status = loginService.Login(null, null);
		assertThat(status, equalTo(-1));
	}
	
	@Test
	public void testPasswordError() {
		int status = loginService.Login("adminx", "");
		assertThat(status, equalTo(-2));
	}
}
