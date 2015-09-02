package com.fhp.mvctest;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhp.mvctest.service.IUSerService;

/**
 * Handles requests for the application home page.
 */
@RequestMapping(value = "/")
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private IUSerService userService;
	
	@Resource
	public void setUserService(IUSerService userService) {
		this.userService = userService;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@AccessRequired
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);		
		model.addAttribute("serverTime", formattedDate);
		
		userService.Oper1();
		return "login";
	}

	@AccessRequired
	@RequestMapping(value = "/jsontest.action", produces="application/json;charset=UTF-8")
	public @ResponseBody Object JsonTest(Locale locale, Model model) {
		logger.info("JsonTest start! The client locale is {}.", locale);
		userService.Oper1();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("num", 1);
		return dataMap;
	}
}
