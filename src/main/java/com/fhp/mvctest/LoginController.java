package com.fhp.mvctest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fhp.mvctest.service.ILoginService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private ILoginService loginService;
	
	@RequestMapping(value="/confirm.action")
	public String Login(String name, String password, HttpServletRequest request) {
		logger.info("用户尝试登录。用户名：" + name);
		int status = loginService.Login(name, password);
		if(status == 1) {
			logger.info("用户登录成功。用户名：" + name);
			HttpSession session = request.getSession();
			session.setAttribute("user", "admin");
			return "redirect:/user/index.action";
		} else {
			logger.info("用户登录失败。用户名：" + name);
			return "redirect:/login.jsp";
		}
	}
	public ILoginService getLoginService() {
		return loginService;
	}
	
	@Resource
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
}
