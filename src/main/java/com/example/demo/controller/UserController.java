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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
//@Api(value="")
@RequestMapping(value="/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value="retrive all users")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() throws Exception {
		return userService.findAll();
	}
	
	@ApiOperation(value="create user")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "user", value = "user to be created", required = true, dataType = "com.example.demo.model", paramType = "body")
	})
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User craeteUser(@RequestBody User user) throws Exception {
		return userService.createUser(user);
	}
	
	@ApiOperation(value="retrieve user")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "string", paramType = "path")
	})
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public User getUser(@PathVariable("username") String username) throws Exception {
		User user = userService.findByUsername(username);
		if(user == null){
			throw new UserNotfoundException(username);
		}
		return user;
	}
	
	@ApiOperation(value="update user")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "string", paramType = "path"),
		@ApiImplicitParam(name = "user", value = "user to be updated", required = true, dataType = "com.example.demo.model", paramType = "body")
	})
	@RequestMapping(value = "/user/{username}", method = RequestMethod.PUT)
	public User replaceUser(@PathVariable("username") String username, @RequestBody User user) throws Exception {
		if(!username.equals(user.getUsername())) {
			throw new InvalidRequestbodyException();
		} else if(userService.findByUsername(username) == null) {
			throw new UserNotfoundException(username);
		}
		return userService.replaceUser(username, user);
	}
	
	@ApiOperation(value="delete user")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "string", paramType = "path")
	})
	@RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("username") String username) throws Exception {
		User user = userService.findByUsername(username);
		if(user == null) {
			throw new UserNotfoundException(username);
		}
		userService.deleteUser(user);
	}

}
