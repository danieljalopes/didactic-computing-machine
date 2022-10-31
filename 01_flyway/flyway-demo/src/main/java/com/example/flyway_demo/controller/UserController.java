package com.example.flyway_demo.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.flyway_demo.model.User;
import com.example.flyway_demo.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "/api/users/")
	public @ResponseBody List<User> allUsers() {
		return userRepository.findAll();
	}

	@GetMapping(value = "/api/users/{userId}/messages")
	public @ResponseBody List<String> messagesOfUser(@PathVariable("userId") Integer userId) {

		User u = userRepository.findById(userId).get();

		List<String> messages = new LinkedList<>();

		u.getMessages().stream().forEach(m -> messages.add(m.getText()));

		return messages;
	}

}
