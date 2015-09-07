package com.fhp.mvctest.service;

import com.fhp.mvctest.dao.IUserDao;
import com.fhp.mvctest.entity.User;

public class LoginService implements ILoginService {

	private IUserDao userDao;
	
	@Override
	public int Login(String name, String password) {
		int res = 0;
		User user = userDao.getByUsername(name);
		if(null == user) res = -1;
		else if(!user.getPassword().equals(password)) {
			res = -1;
		} else {
			res = user.getId();
		}
		return res;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
}
