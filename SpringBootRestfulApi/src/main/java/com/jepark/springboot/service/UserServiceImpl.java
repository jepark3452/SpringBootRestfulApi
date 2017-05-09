package com.jepark.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.jepark.springboot.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static List<User> users; 
	
	private static AtomicLong counter = new AtomicLong();
	
	static {
		
		users = dummyData();
	}

	private static List<User> dummyData() {

		users = new ArrayList<User>();
		
		users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000));
		users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000));
		users.add(new User(counter.incrementAndGet(), "Jereme", 45, 30000));
		users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
		
		return users;
	}

	@Override
	public List<User> getUserList() {
		return users;
	}

	@Override
	public User getUserInfoById(long id) {
		
		for(User userInfo : users) {
			if(userInfo.getId() == id) {
				return userInfo;
			}
		}
		
		return null;
	}

	@Override
	public boolean isExistUser(User user) {
		
		return getUserInfoByName(user.getName()) != null ? true : false;
	}

	private User getUserInfoByName(String name) {

		for(User userInfo : users) {
			if(userInfo.getName().equalsIgnoreCase(name)){
				return userInfo;
			}
		}
		
		return null;
	}

	@Override
	public void saveUserInfo(User user) {
		
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	@Override
	public void updateUserInfo(User userInfo) {

		int index = users.indexOf(userInfo);
		users.set(index, userInfo);
	}

	@Override
	public void deleteUserInfo(long id) {
		
		int index = getUserIndexById(id);
		users.remove(index);
	}

	private int getUserIndexById(long id) {

		int index = -1;
		
		for(User userInfo : users) {
			if(userInfo.getId() == id) {
				index = users.indexOf(userInfo);
			}
		}
		
		return index;
	}

	@Override
	public void deleteUserInfoAll() {
		
		users.clear();
	}
}
