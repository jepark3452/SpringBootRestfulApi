package com.jepark.springboot.service;

import java.util.List;

import com.jepark.springboot.model.User;

public interface UserService {
	
	/**
	 * GET --> getUserList
	 * @return
	 */
	List<User> getUserList();
	
	/**
	 * GET --> getUserInfoById
	 * @param id
	 * @return
	 */
	User getUserInfoById(long id);
	
	/**
	 * POST --> isExistUser
	 * @param user
	 * @return
	 */
	boolean isExistUser(User user);
	
	/**
	 * POST --> saveUserInfo
	 * @param user
	 */
	void saveUserInfo(User user);
	
	/**
	 * PUT --> updateUserInfo
	 * @param userInfo
	 */
	void updateUserInfo(User userInfo);
	
	/**
	 * DELETE --> deleteUserInfo
	 * @param id
	 */
	void deleteUserInfo(long id);
	
	/**
	 * DELETE --> deleteUserInfoAll
	 */
	void deleteUserInfoAll();

}
