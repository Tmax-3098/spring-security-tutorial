package com.sketch.springSecurity;

import com.sketch.springSecurity.entities.UserEntity;
import com.sketch.springSecurity.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		UserEntity user = new UserEntity(4L, "max@redbull.com","max");
		String token = jwtService.generateToken(user);
		System.out.println(token);
		System.out.println(jwtService.getUserIdFromToken(token));
	}

}
