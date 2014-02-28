package com.zimmer.db;

import com.zimmer.dbbeans.User;

public interface AbstractUserDao {
	public String sayHello() throws Exception;
	public String createUser(User user) throws Exception;
	public User getUserById(String userId) throws Exception;
	public User getUserByUsername(String userName) throws Exception;
}
