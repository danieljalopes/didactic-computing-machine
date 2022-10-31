package com.example.flyway_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.flyway_demo.model.User;
import com.example.flyway_demo.repository.UserRepository;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class FlywayDemoApplicationTests {
	
	@Autowired
	UserRepository  userRepository;
	
	@Test
	void userExistsTest() {
		List<User> users = userRepository.findByFirstName("Mickael");
		
		String expected = "Mickael";
		String result = ((User) users.get(0)).getFirstName();
		
		assertEquals( expected, result, "User's first name expected: " + expected + " - but result: " + result);
	}
	
	@Test
	void MichaelNightFirstMessageTest() {
		
		User michael = userRepository.findByFirstName("Mickael").get(0);
		
		String expected = "K.I.T.T., come get me!";
		String result = michael.getMessages().get(0).getText();
		
		assertEquals( expected, result, "Michael's first message expected: " + expected + " - but result: " + result);
		
	}

}
