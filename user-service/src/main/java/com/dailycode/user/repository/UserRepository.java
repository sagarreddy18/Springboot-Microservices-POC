package com.dailycode.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycode.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

	User findByUserId(Long userId);

}
