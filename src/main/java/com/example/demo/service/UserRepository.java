package com.example.demo.service;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	List<User> findByAge(String age);
}
