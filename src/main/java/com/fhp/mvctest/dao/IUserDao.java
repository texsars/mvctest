package com.fhp.mvctest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fhp.mvctest.entity.User;

public interface IUserDao {
	public void Add();
	public void Delete(int id);
	public void Update(User user);
	public List<User> Query(@Param("user") User user, 
							@Param("start") int start,
							@Param("num") int num,
							@Param("order_column") String order_column,
							@Param("order_schema") String order_schema);
	public int getNum(User user);
	public User getById(int id);
	public User getByUsername(String username);
}
