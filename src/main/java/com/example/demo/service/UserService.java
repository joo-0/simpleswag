package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User replaceUser(String username, User user) {
		return userRepository.save(user);
	}
	
	public void deleteUser(String username) {
		User user = findByUsername(username);
		userRepository.delete(user);
	}
}
