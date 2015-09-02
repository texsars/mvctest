package com.fhp.mvctest.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

	@Override
	public int Login(String name, String password) {
		if(name.equals("admin") && password.equals("12345")) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
