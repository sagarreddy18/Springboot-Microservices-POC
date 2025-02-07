package com.dailycode.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dailycode.user.VO.Department;
import com.dailycode.user.VO.ResponseTemplateVO;
import com.dailycode.user.entity.User;
import com.dailycode.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		log.info("User service save user method");
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserwithDepartment(Long userId) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = userRepository.findByUserId(userId);
		log.info("User service getUserwithDepartment method");

		Department department = restTemplate
				.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
		vo.setUser(user);
		vo.setDepartment(department);
		vo.setMessage("Suceessfully Fetched!");
		return vo;
	}
}
