package com.kh.spring.mountain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.kh.spring.board.BoardControllerTest;
import com.kh.spring.member.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class MountainControllerTest {


	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("산 리스트 api 가져오기")
	public void boardDetail() throws Exception{
		mockMvc.perform(get("/board/board-detail")
				.param("bdIdx", "1"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	
	
	
}
