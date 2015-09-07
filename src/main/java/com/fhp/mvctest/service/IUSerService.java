package com.fhp.mvctest.service;

import java.util.List;

import com.fhp.mvctest.entity.User;

public interface IUSerService {
	public void Oper1();
	public void Add(User user) throws UserExistsException;
	public void Delete(int id);
	public void Update(User user);
	public List<User> Query(User user, 
			int start, int num, 
			String order_column, String order_schema);
	public User getById(int id);
	public User getByUsername(String username);
	public int getNum(User user);
	public boolean Exists(String username);
}
