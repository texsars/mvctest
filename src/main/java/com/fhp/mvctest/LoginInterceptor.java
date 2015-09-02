package com.fhp.mvctest;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response,
			Object handler) throws IOException {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Method method = handlerMethod.getMethod();
		Class clazz = method.getDeclaringClass();
		AccessRequired annotation_method = method.getAnnotation(AccessRequired.class);
		AccessRequired annotation_class = (AccessRequired) clazz.getAnnotation(AccessRequired.class);
		if(null != annotation_method || null != annotation_class) {
			logger.debug("已经过拦截器LoginInterceptor。");
			HttpSession session = request.getSession();
			if(null == session.getAttribute("user")) {
				response.sendRedirect(request.getContextPath() + "/login.action");
				logger.debug("用户未登录，已拦截LoginInterceptor。");
				return false;
			}
		}
		return true;
	}
}
