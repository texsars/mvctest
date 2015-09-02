package com.fhp.mvctest;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response,
			Object handler) throws IOException {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Method method = handlerMethod.getMethod();
		Class clazz = method.getDeclaringClass();
		AccessRequired annotation_method = method.getAnnotation(AccessRequired.class);
		AccessRequired annotation_class = (AccessRequired) clazz.getAnnotation(AccessRequired.class);
		if(null != annotation_method || null != annotation_class) {
			HttpSession session = request.getSession();
			if(null == session.getAttribute("user")) {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				return false;
			}
		}
		return true;
	}
}
