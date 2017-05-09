package com.jepark.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jepark.springboot.model.User;
import com.jepark.springboot.service.UserService;
import com.jepark.springboot.util.CustomErrorType;

@RequestMapping("/api")
@RestController
public class RestfulApiController {

	private static Logger logger = LoggerFactory.getLogger(RestfulApiController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUserList() {
		
		List<User> userList = userService.getUserList();
		if(userList.isEmpty()) {
			logger.info("USER LIST is empty.");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserInfo(@PathVariable("id") long id) {
		
		User userInfo = userService.getUserInfoById(id);
		if(userInfo == null) {
			logger.error("USER ID {} is not exist.", id);
			return new ResponseEntity(new CustomErrorType("USER ID " + id + " is not exist."), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(userInfo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> saveUserInfo(@RequestBody User user, UriComponentsBuilder uc) {
		
		if(userService.isExistUser(user)) {
			logger.error("INSERT ERROR: USER NAME {} is already exist.", user.getName());
			return new ResponseEntity(new CustomErrorType("INSERT ERROR: USER NAME " + user.getName() + " is already exist."), HttpStatus.CONFLICT);
		}
		
		userService.saveUserInfo(user);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(uc.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		
		return new ResponseEntity(header, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserInfo(@PathVariable("id") long id, @RequestBody User user) {
		
		User userInfo = userService.getUserInfoById(id);
		if(userInfo == null) {
			logger.error("UPDATE ERROR : USER ID {} is not exist.", id);
			return new ResponseEntity(new CustomErrorType("UPDATE ERROR : USER ID " + id + " is not exist."), HttpStatus.NOT_FOUND);
		}
		
		userInfo.setAge(user.getAge());
		userInfo.setName(user.getName());
		userInfo.setSalary(user.getSalary());
		userService.updateUserInfo(userInfo);
		
		return new ResponseEntity<User>(userInfo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserInfo(@PathVariable("id") long id) {
		
		User userInfo = userService.getUserInfoById(id);
		if(userInfo == null) {
			logger.error("DELETE ERROR : USER ID {} is not exist.", id);
			return new ResponseEntity(new CustomErrorType("DELETE ERROR : USER ID " + id + " is not exist."), HttpStatus.NOT_FOUND);
		}
		
		userService.deleteUserInfo(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserInfoAll() {
		
		logger.info("deleteUserInfoAll call.");
		userService.deleteUserInfoAll();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
