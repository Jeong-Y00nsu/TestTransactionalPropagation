package com.example.demo;

import com.example.demo.domain.UserRequest;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	private final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);


	@BeforeEach
	public void setup(){
		userService.deleteUser();
	}

	@AfterEach
	public void teardown(){
		userService.deleteUser();
	}

	@Test
	public void testAsyncRequired() throws Exception {

		UserRequest userRequest = new UserRequest("John0522", "JoinPassword", "John");
		String userJson = objectMapper.writeValueAsString(userRequest);

		mockMvc.perform(post("/demo/asyncRegR")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String initialData = getInitialData();
		log.info("reg 성공 후 DB: {}",initialData);

		mockMvc.perform(post("/demo/asyncDeregR")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String finalData = getFinalData();
		log.info("dereg 실패 후 DB 상황: {}",finalData);
		assertEquals(initialData, finalData, "Data should be same");
	}

	@Test
	public void testAsyncRequiresNew() throws Exception {

		UserRequest userRequest = new UserRequest("John0522", "JoinPassword", "John");
		String userJson = objectMapper.writeValueAsString(userRequest);

		mockMvc.perform(post("/demo/asyncRegRN")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String initialData = getInitialData();
		log.info("reg 성공 후 DB: {}",initialData);

		mockMvc.perform(post("/demo/asyncDeregRN")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String finalData = getFinalData();
		log.info("dereg 실패 후 DB 상황: {}",finalData);

		assertEquals(initialData, finalData, "Data should be same");
	}

	@Test
	public void testSyncRequired() throws Exception {

		UserRequest userRequest = new UserRequest("John0522", "JoinPassword", "John");
		String userJson = objectMapper.writeValueAsString(userRequest);

		mockMvc.perform(post("/demo/syncRegR")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String initialData = getInitialData();
		log.info("reg 성공 후 DB: {}",initialData);

		mockMvc.perform(post("/demo/syncDeregR")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String finalData = getFinalData();
		log.info("dereg 실패 후 DB 상황: {}",finalData);

		assertEquals(initialData, finalData, "Data should be same");
	}

	@Test
	public void testSyncRequiresNew() throws Exception {

		UserRequest userRequest = new UserRequest("John0522", "JoinPassword", "John");
		String userJson = objectMapper.writeValueAsString(userRequest);

		mockMvc.perform(post("/demo/syncRegRN")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String initialData = getInitialData();
		log.info("reg 성공 후 DB: {}",initialData);

		mockMvc.perform(post("/demo/syncDeregRN")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String finalData = getFinalData();
		log.info("dereg 실패 후 DB 상황: {}",finalData);

		assertEquals(initialData, finalData, "Data should be same");
	}

	@Test
	public void testRegAndDeregRollback() throws Exception {
		UserRequest userRequest = new UserRequest("John0522", "JoinPassword", "John");
		String userJson = objectMapper.writeValueAsString(userRequest);

		// 첫 번째 API 호출: 사용자 등록
		mockMvc.perform(post("/demo/regUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isOk());

		String initialData = getInitialData();
		log.info("등록 성공 후 DB 상황: {}", initialData);

		// 두 번째 API 호출: 사용자 탈퇴 (여기서 의도적으로 실패)
		mockMvc.perform(post("/demo/deregUser")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andExpect(status().isInternalServerError());

		String finalData = getFinalData();
		log.info("탈퇴 실패 후 DB 상황: {}", finalData);

		// 전체 트랜잭션이 롤백되어 DB에 아무 데이터도 없어야 합니다.
		assertEquals("[]", finalData, "Data should be empty due to rollback");
	}

	private String getInitialData() {
		return userService.selectAllUser().toString();
	}

	private String getFinalData() {
		return userService.selectAllUser().toString();
	}
}
