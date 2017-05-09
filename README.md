# SpringBootRestfulApi
 springboot restful api 서비스 예제 <br/>

<h3>1. SPEC</h3>
<pre>
Spring Boot 1.4.3.RELEASE
Spring 4.3.5.RELEASE [transitively]
Maven 3.1
JDK 1.8
Eclipse MARS.1
</pre>

<pre>
- pom.xml 의존성 설정
<code>
&lt;modelVersion&gt;4.0.0&lt;/modelVersion&gt;
 
&lt;groupId&gt;com.jepark.springboot&lt;/groupId&gt;
&lt;artifactId&gt;SpringBootRestfulApi&lt;/artifactId&gt;
&lt;version&gt;1.0.0&lt;/version&gt;
&lt;packaging&gt;jar&lt;/packaging&gt;

&lt;name&gt;SpringBootRestfulApi&lt;/name&gt;
&lt;description&gt;SpringBootRestfulApi&lt;/description&gt;

&lt;parent&gt;
	&lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
	&lt;artifactId&gt;spring-boot-starter-parent&lt;/artifactId&gt;
	&lt;version&gt;1.4.3.RELEASE&lt;/version&gt;
&lt;/parent&gt;

&lt;properties&gt;
	&lt;java.version&gt;1.8&lt;/java.version&gt;
&lt;/properties&gt;

&lt;dependencies&gt;
	&lt;!-- Add typical dependencies for a web application --&gt;
	&lt;!-- Adds Tomcat and Spring MVC, along others --&gt;
	&lt;dependency&gt;
		&lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
		&lt;artifactId>spring-boot-starter-web&lt;/artifactId&gt;
	&lt;/dependency&gt;
	&lt;dependency&gt;
		&lt;!-- get, headers add : Accept text/xml than present xml type. --&gt;
		&lt;groupId&gt;com.fasterxml.jackson.dataformat&lt;/groupId&gt;
		&lt;artifactId&gt;jackson-dataformat-xml&lt;/artifactId&gt;
	&lt;/dependency&gt;
	&lt;dependency&gt;
		&lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
		&lt;artifactId>spring-boot-starter-test&lt;/artifactId&gt;
		&lt;scope>test&lt;/scope&gt;
	&lt;/dependency&gt;
&lt;/dependencies&gt;

&lt;build&gt;
	&lt;plugins&gt;
		&lt;plugin&gt;
			&lt;groupId>org.springframework.boot&lt;/groupId&gt;
			&lt;artifactId>spring-boot-maven-plugin&lt;/artifactId&gt;
		&lt;/plugin&gt;
	&lt;/plugins&gt;
&lt;/build&gt;
</code>
</pre>

<pre>
- application.yml 설정
<code>
server:
  port: 8080
  contextPath: /SpringBootRestfulApi
</code>
</pre>

<h3>2. DESIGN</h3>
<pre>
com.jepark.springboot
com.jepark.springboot.controller
com.jepark.springboot.model
com.jepark.springboot.service
com.jepark.springboot.util

@SpringBootApplication(scanBasePackages={"com.jepark.springboot"})
public class SpringBootRestApiExam1Application

This is what our REST API does:

GET request to /api/user/ returns a list of users
GET request to /api/user/1 returns the user with ID 1
POST request to /api/user/ with a user object as JSON creates a new user
PUT request to /api/user/3 with a user object as JSON updates the user with ID 3
DELETE request to /api/user/4 deletes the user with ID 4
DELETE request to /api/user/ deletes all the users
</pre>

<h3>3. DATA</h3>
<pre>
[
  {
    "id": 1,
    "name": "Sam",
    "age": 30,
    "salary": 70000
  },
  {
    "id": 2,
    "name": "Tom",
    "age": 40,
    "salary": 50000
  },
  {
    "id": 3,
    "name": "Jereme",
    "age": 45,
    "salary": 30000
  },
  {
    "id": 4,
    "name": "Silvia",
    "age": 50,
    "salary": 40000
  }
]
</pre>

<h3>4. TEST (크롬 플러그인 --> Postman 설치, Java code)</h3>
<pre>
Spring’s RestTemplate

HTTP Methods and corresponding RestTemplate methods:

HTTP GET : getForObject, getForEntity
HTTP PUT : put(String url, Object request, String…​urlVariables)
HTTP DELETE : delete
HTTP POST : postForLocation(String url, Object request, String…​ urlVariables), postForObject(String url, 
		Object request, Class responseType, String…​ uriVariables)
HTTP HEAD : headForHeaders(String url, String…​ urlVariables)
HTTP OPTIONS : optionsForAllow(String url, String…​ urlVariables)
HTTP PATCH and others : exchange execute

* RestTemplate
getForObject > getUserList()
getForObject > getUserInfo()
postForLocation > createUser()
delete > deleteUser()
delete > deleteUserAll()
</pre>

<h3>5. STATUS (크롬 플러그인 --> JSON Viewer 설치, spring-boot-actuator 의존성 추가)</h3>
<pre>
/beans 
/autoconfig
/env
</pre>

<h5>※ 참고 : http://websystique.com/spring-boot/spring-boot-rest-api-example/</h5>
