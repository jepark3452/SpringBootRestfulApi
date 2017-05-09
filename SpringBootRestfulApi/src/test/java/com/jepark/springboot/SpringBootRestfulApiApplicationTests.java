package com.jepark.springboot;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.jepark.springboot.model.User;

public class SpringBootRestfulApiApplicationTests {
	
	private final static String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestfulApi/api";
	
	/*	GET	*/
	@SuppressWarnings("unchecked")
	public static void getUserList() {
		System.out.println("\nGET TEST --> getUserList API");
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> userMapList =  restTemplate.getForObject(REST_SERVICE_URI + "/user/", List.class);
		
		if(userMapList == null) {
			System.out.println("USER LIST is empty.");
		} else {
			for(LinkedHashMap<String, Object> userMap : userMapList) {
				System.out.println("USER LIST:: id: " + userMap.get("id") + ", name: " +userMap.get("name") + ", age: " + userMap.get("age") + ", salary: " + userMap.get("salary"));
			}
		}
	}
	
	/*	GET	*/
	public static void getUserInfo() {
		System.out.println("\nGET TEST --> getUserList API");
		
		RestTemplate restTemplate = new RestTemplate();
		User userInfo = restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
		System.out.println(userInfo);
	}
	
	/*	POST	*/
	public static void saveUserInfo() {
		System.out.println("\nPOST TEST --> saveUserInfo API");
		
		RestTemplate restTemplate = new RestTemplate();
		User userInfo = new User(0, "Suzy", 22, 90000);
		URI locate =  restTemplate.postForLocation(REST_SERVICE_URI + "/user/", userInfo);
		System.out.println("locate: " + locate.toASCIIString());
	}
	
	/*	PUT	*/
	public static void updateUserInfo() {
		System.out.println("\nPUT TEST --> updateUserInfo API");
		
		RestTemplate restTemplate = new RestTemplate();
		User userInfo = new User(3, "Jerome", 99, 10000);
		restTemplate.put(REST_SERVICE_URI + "/user/3", userInfo);
		System.out.println(userInfo);
	}
	
	/*	DELETE	*/
	public static void deleteUserInfo() {
		System.out.println("\nDELETE TEST --> deleteUserInfo API");
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/4");
	}
	
	/*	DELETE	*/
	public static void deleteUserInfoAll() {
		System.out.println("\nDELETE TEST --> deleteUserInfoAll API");
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/");
	}
	
	public static void main(String[] args) {
		getUserList();
		getUserInfo();
		saveUserInfo();
		getUserList();
		updateUserInfo();
		getUserList();
		deleteUserInfo();
		getUserList();
		deleteUserInfoAll();
		getUserList();
	}
}
