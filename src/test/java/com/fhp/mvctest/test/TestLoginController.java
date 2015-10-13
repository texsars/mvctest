package com.fhp.mvctest.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext; 

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Map;

import org.hamcrest.CoreMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "classpath*:service-context.xml"})
//@Transactional
//@TransactionConfiguration(defaultRollback=true)
@WebAppConfiguration
public class TestLoginController {
	
	@Autowired
	private WebApplicationContext ac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(this.ac).build();
	}
	
	@Test
	public void testLoginPage() throws Exception {
		mockMvc.perform(get("/login.action")).andExpect(forwardedUrl("/WEB-INF/views/login.jsp")).andDo(print());
	}
	
	@Test
	public void testParamJson() throws Exception {
		MvcResult result = mockMvc.perform(get("/paramtest.action?param=123f4ck"))
//				.andExpect(request().asyncStarted())
//				.andExpect(request().asyncResult(CoreMatchers.instanceOf(Map.class)))
				.andReturn();
		
		mockMvc.perform(asyncDispatch(result))
			.andExpect(status().isOk())
			.andDo(print())
//			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.param").value("123f4ck"));
	}
	
	@Test
	public void testJson() throws Exception {
		MvcResult result = mockMvc.perform(get("/jsontest.action"))
//				.andExpect(request().asyncStarted())
//				.andExpect(request().asyncResult(CoreMatchers.instanceOf(Map.class)))
				.andReturn();
		
		mockMvc.perform(asyncDispatch(result))
			.andExpect(status().isOk()).andDo(print())
//			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$.num").value(1));
	}
}
