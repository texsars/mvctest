package com.fhp.mvctest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUSerService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public void Oper1() {
		logger.debug("Oper1 complete!");
		System.out.println("Oper1 complete!");
	}
}
