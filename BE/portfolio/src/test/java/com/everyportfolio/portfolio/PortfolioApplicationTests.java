package com.everyportfolio.portfolio;

import com.everyportfolio.portfolio.model.PortfolioWithThumbnail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import javax.servlet.ServletOutputStream;
import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
class PortfolioApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

/*	@Test
	void checkCreatePortfolio() {
		String accessToken1 = "{ \"id\": \"juyj7282@gmail.com\", \"auth\" : \"USER\" }";
		String accessToken2 = "{ \"id\": \"king7282@gmail.com\", \"auth\" : \"USER\" }";

		HashMap<String, Object> params = new HashMap<>();
		params.put("content", "i dont know how to fill in this box");
		params.put("templateType", "1");

		for(int i = 0; i < 10; i++) {
			try {

				params.put("title", "hiii~" + Integer.toString(i * 2));
				mockMvc.perform(MockMvcRequestBuilders.post("/create").header("access-token", accessToken1).
																					contentType("application/json").
																					content((new Gson()).toJson(params))).andDo(print());

				params.put("title", "hiii~" + Integer.toString(i * 2 + 1));
				mockMvc.perform(MockMvcRequestBuilders.post("/create").header("access-token", accessToken2).contentType("application/json").content((new Gson()).toJson(params))).andDo(print());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}*/
	/*@Test
	void checkUpdateTitle() {
		String accessToken = "{ \"id\": \"juyj7282@gmail.com\", \"auth\" : \"USER\" }";
		HashMap<String, Object> params = new HashMap<>();

		params.put("tableId", "10");
		params.put("title", "changeTitle!!");

		try {
			mockMvc.perform(MockMvcRequestBuilders.put("/update/title").contentType("application/json").header("access-token", accessToken).content((new Gson()).toJson(params))).andDo(print());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void checkUpdateContent() {
		String accessToken = "{ \"id\": \"juyj7282@gmail.com\", \"auth\" : \"USER\" }";
		HashMap<String, Object> params = new HashMap<>();

		params.put("tableId", 10);
		params.put("content", "changeContent!!!!");
		params.put("templateType", 1);

		try {
			mockMvc.perform(MockMvcRequestBuilders.put("/update/content").contentType("application/json").header("access-token", accessToken).content((new Gson()).toJson(params))).andDo(print());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*@Test
	void checkDelete() {
		String accessToken = "{ \"id\": \"juyj7282@gmail.com\", \"auth\" : \"USER\" }";
		HashMap<String, Object> params = new HashMap<>();

		params.put("tableId", 10);

		try {
			mockMvc.perform(MockMvcRequestBuilders.delete("/delete").contentType("application/json").header("access-token", accessToken).content((new Gson()).toJson(params))).andDo(print());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*@Test
	void checkGetPortfolio() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/view").param("tableId","11")).andDo(print());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/

    /*@Test
	void checkSwitchLike() {
		String accessToken = "{ \"id\": \"juyj7282@gmail.com\", \"auth\" : \"USER\" }";
		HashMap<String, Object> content = new HashMap<>();
		content.put("tableId", 11);

		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/like").header("access-token", accessToken).contentType("application/json").content((new Gson()).toJson(content))).andDo(print());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*@Test
	void checkLikeCheck() {
		String accessToken = "{ \"id\": \"juyj7282@gmail.com\", \"auth\" : \"USER\" }";

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/like").header("access-token", accessToken).param("tableId", "11")).andDo(print());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*@Test
	void createLike() {
		HashMap<String, String> accessToken = new HashMap<>();
		HashMap<String, Integer> content = new HashMap<>();

		accessToken.put("auth", "USER");

		for(int i = 1; i <= 10; i++) {
			accessToken.put("id", "tmp" + i);

			for(int tableId = 20 + i; tableId < 30; tableId++) {
				try{
					content.put("tableId", tableId);
					mockMvc.perform(MockMvcRequestBuilders.post("/like").header("access-token", (new Gson()).toJson(accessToken)).contentType("application/json").content((new Gson()).toJson(content))).andDo(print());
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}*/

	@Test
	void checkSearchByLatest() {
		try {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/search").
												   param("maxTableId", "23").
												   param("type", "1").param("title", "hi"))
					.andDo(print()).andReturn();

			PortfolioListVO body = (new Gson()).fromJson(result.getResponse().getContentAsString(), PortfolioListVO.class);

			for(int i = 0; i < body.getPortfolioList().size(); i++) {
				System.out.println(body.getPortfolioList().get(i).toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@Test
	void checkSearchByLikeCount() {
		try {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/search").
					param("maxTableId", "31").param("maxLikeCount", "0").param("title", "hi").param("userId", "juyj7282@gmail.com").
					param("type", "2"))
					.andDo(print()).andReturn();

			PortfolioListVO body = (new Gson()).fromJson(result.getResponse().getContentAsString(), PortfolioListVO.class);

			for(int i = 0; i < body.getPortfolioList().size(); i++) {
				System.out.println(body.getPortfolioList().get(i).toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	class PortfolioListVO {
		private ArrayList<PortfolioWithThumbnail> portfolioList;
		private String message;
		private String status;
	}
}
