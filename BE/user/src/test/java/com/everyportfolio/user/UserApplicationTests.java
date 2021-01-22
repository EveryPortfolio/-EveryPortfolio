package com.everyportfolio.user;

import com.everyportfolio.user.DTO.LoginDTO;
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
import org.springframework.test.web.servlet.MvcResult;
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
	@Order(0)
	void contextLoads() {
	}

	/*@Test
	public void getProfile() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/profile").header("access-token", "{ \"id\": \"123\", \"auth\" : \"USER\" } "))
		.andExpect(status().isOk()).andDo(print());
	}*/

	/*@Test
	public void testCheckID() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/check-id").param("id", "123")).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.get("/check-id").param("id", "juyj7282@gmail.com")).andDo(print());
	}*/


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

	@Test
	public void testLogout() {
		LoginDTO loginDTO = new LoginDTO("juyj7282@gmail.com", "1q2w3e4r");

		try {
			MvcResult loginResult = mockMvc.perform(MockMvcRequestBuilders.post("/login").contentType("application/json").content((new Gson()).toJson(loginDTO))).
									andDo(print()).andReturn();

			String accessToken = loginResult.getResponse().getHeader("access-token");
			String refreshToken = loginResult.getResponse().getHeader("refresh-token");

			mockMvc.perform(MockMvcRequestBuilders.get("/profile").header("access-token", accessToken)).andDo(print());

			mockMvc.perform(MockMvcRequestBuilders.post("/refresh").header("refresh-token", refreshToken)).andDo(print());

			mockMvc.perform(MockMvcRequestBuilders.post("/logout").header("access-token", accessToken)).andDo(print());

			mockMvc.perform(MockMvcRequestBuilders.post("/refresh").header("refresh-token", refreshToken)).andDo(print());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
