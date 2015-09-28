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
	public void Add(User user) throws UserExistsException {
		if(Exists(user.getUsername())) {
			throw new UserExistsException("This username has been used.");
		}
		userDao.Add(user);
	}

	@Override
	public void Delete(int id) {
		userDao.Delete(id);
	}

	@Override
	public void Update(User user) {
		userDao.Update(user);
	}

	@Override
	public List<User> Query(User user, 
			int start, int num, 
			String order_column, String order_schema) {
		if(null == user) {
			user = new User();
			user.setUsername("%");
			user.setNickname("%");
		}
		if(user.getUsername() == null) user.setUsername("%");
		else user.setUsername(user.getUsername() + "%");
		if(user.getNickname() == null) user.setNickname("%");
		else user.setNickname(user.getNickname() + "%");
		logger.debug("User:" + user.getUsername() + "|" + user.getNickname());
		List<User> res = userDao.Query(user, start, num, order_column, order_schema);
		return res; 
	}

	@Override
	public User getById(int id) {
		User res = userDao.getById(id);
		return res;
	}

	@Override
	public User getByUsername(String username) {
		User res = userDao.getByUsername(username);
		return res;
	}

	@Override
	public boolean Exists(String username) {
		User res = userDao.getByUsername(username);
		if(null == res) return false;
		else return true;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int getNum(User user) {
		int res = getNum(user);
		return res;
	}
}
