package com.dailycode.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dailycode.user.VO.ResponseTemplateVO;
import com.dailycode.user.entity.User;
import com.dailycode.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		log.info("User Controller save user method");
		return userService.saveUser(user);
	}
	
	@GetMapping("/{id}")
	@CircuitBreaker(name="user-service", fallbackMethod = "fallbackForGetUserWithDepartment")
	public ResponseTemplateVO getUserwithDepartment(@PathVariable("id") Long userId) {
	    log.info("User Controller getUserwithDepartment method");
	    return userService.getUserwithDepartment(userId);
	}

	public ResponseTemplateVO fallbackForGetUserWithDepartment(Long userId, RuntimeException ex) {
	    log.warn("Fallback method executed. Service is down!", ex);
	  	ResponseTemplateVO response = new ResponseTemplateVO();
	    response.setUser(null); // or some default user object
	    response.setDepartment(null); // or some default department object
	    response.setMessage("Fallback method executed.Department Service is down!");
	    return response;
	}


	
}
