package com.fhp.mvctest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fhp.mvctest.entity.User;

public interface IUserDao {
	void Add(@Param("user") User user);
	void Delete(@Param("id") int id);
	void Update(@Param("user") User user);
	List<User> Query(@Param("user") User user, 
							@Param("start") int start,
							@Param("num") int num,
							@Param("order_column") String order_column,
							@Param("order_schema") String order_schema);
	int getNum(@Param("user") User user);
	User getById(@Param("id") int id);
	User getByUsername(@Param("username") String username);
}
