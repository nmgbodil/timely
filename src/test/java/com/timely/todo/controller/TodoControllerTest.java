package com.timely.todo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timely.todo.dto.TodoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createsAndListsTodos() throws Exception {
		String payload = """
				{"title":"Write tests","description":"Add controller test","completed":false}
				""";

		mockMvc.perform(post("/api/todos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(payload))
				.andExpect(status().isCreated());

		String body = mockMvc.perform(get("/api/todos"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		TodoResponse[] todos = objectMapper.readValue(body, TodoResponse[].class);
		assertThat(todos).isNotEmpty();
		assertThat(todos[0].title()).isEqualTo("Write tests");
	}
}
