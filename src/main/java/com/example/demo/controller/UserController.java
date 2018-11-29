package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.InvalidRequestbodyException;
import com.example.demo.exception.UserNotfoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping(value="/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() throws Exception {
		return userService.findAll();
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User craeteUser(@RequestBody User user) throws Exception {
		return userService.createUser(user);
	}
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public User getUser(@PathVariable("username") String username) throws Exception {
		return userService.findByUsername(username);
	}
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.PUT)
	public User replaceUser(@PathVariable("username") String username, @RequestBody User user) throws Exception {
		if(!username.equals(user.getUsername())) {
			throw new InvalidRequestbodyException();
		} else if(userService.findByUsername(username) == null) {
			throw new UserNotfoundException(username);
		}
		return userService.replaceUser(username, user);
	}
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("username") String username) throws Exception {
		userService.deleteUser(username);
	}

}
