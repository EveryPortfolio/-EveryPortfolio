package com.everyportfolio.user;

import com.everyportfolio.user.DTO.UserDTO;
import com.everyportfolio.user.controller.UserController;
import com.everyportfolio.user.service.EmailService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private EmailService emailService;

	@Test
	void contextLoads() {
	}

	/*@Test
	public void getProfile() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/profile").header("access-token", "{ \"id\": \"123\", \"auth\" : \"USER\" } "))
		.andExpect(status().isOk()).andDo(print());
	}*/

	@Test
	public void testCheckID() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/check-id").param("id", "123")).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.get("/check-id").param("id", "juyj7282@gmail.com")).andDo(print());
	}


	/*@Test
	public void testSendMailToGmail() {
		try {
			emailService.setFrom("king7282@naver.com");
			emailService.setTo("juyj7282@gmail.com");
			emailService.setSubject("hi");
			emailService.setText("my name is jyj!!!!!!");
			emailService.send();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/

	/*@Test
	public void testCreateUserAndSendEmailForAuthentication() {
		UserDTO testUser = new UserDTO("juyj7282@gmail.com", "jyj", "123");
		String body = (new Gson()).toJson(testUser);
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/create").contentType("application/json").content(body)).andDo(print());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/

	/*@Test
	public void testAuthentication() {

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/email-authentication").param("params", "QhhqtWK/Q83x3OB9DRE8UlCLYIf5m9dawN7auEgQNnf7NDrPZUqsG8K4Rc0x95T5muO/Df/7PvKNB4bDyZaN7A==")).andDo(print());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/

}
