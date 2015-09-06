package com.fhp.mvctest.service;

import java.util.List;

import com.fhp.mvctest.entity.User;

public interface IUSerService {
	public void Oper1();
	public void Add();
	public void Delete(int id);
	public void Update(User user);
	public List<User> Query(User user);
	public User getById();
	public User getByUsername();
	public boolean Exists(User user);
}
