package com.natwest.primenumbers.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimeNumbersControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testgetAllPrimeNumbers() throws Exception {
		this.mockMvc.perform(get("/v1/api/primes/10")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"initial\":10,\"primes\":[2,3,5,7]}")));
	}
	
	@Test
	public void testgetAllPrimeNumbers_InValidInput() throws Exception {
		this.mockMvc.perform(get("/v1/api/primes/0")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Initial Must be Greater Than One")));
	}
	
	@Test
	public void testgetAllPrimeNumbers_BadRequest() throws Exception {
		this.mockMvc.perform(get("/v1/api/primes/a")).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("")));
	}

}
