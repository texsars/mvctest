package com.fhp.mvctest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fhp.mvctest.dao.IUserDao;
import com.fhp.mvctest.entity.User;

@Service
public class UserService implements IUSerService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private IUserDao userDao;
	@Override
	public void Oper1() {
		logger.debug("Oper1 complete!");
		System.out.println("Oper1 complete!");
	}

	@Override
	public void Add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> Query(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Exists(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
}
