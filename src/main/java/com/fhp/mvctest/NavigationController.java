package com.fhp.mvctest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {
	
	@AccessRequired
	@RequestMapping(value="/user/index.action")
	public String UserIndexNavigate() {
		return "/user/index";
	}
	
	@AccessRequired
	@RequestMapping(value="/user/{page}_nav.action")
	public String UserNavigate(@PathVariable("page") String page) {
		return "/user/" + page;
	}
}
