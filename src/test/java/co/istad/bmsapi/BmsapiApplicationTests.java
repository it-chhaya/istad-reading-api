package co.istad.bmsapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.istad.bmsapi.data.repository.UserRepository;

@SpringBootTest
class BmsapiApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
		System.out.println(userRepository.selectByUsername("admin"));
	}

}
