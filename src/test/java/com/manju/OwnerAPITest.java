package com.manju;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manju.petowner.swagger.model.Pet;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class OwnerAPITest {

	@Autowired
	private MockMvc mockMvc;

	/*private MockRestServiceServer mockRestServiceServer;

	@Autowired
	private RestOperations restOperations;

	
*/
	@Autowired
	private ObjectMapper mapper;
	@Test
	public void getowners() throws Exception {

		this.mockMvc
				.perform(get("/owners").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].firstName", is("John")))
				.andExpect(jsonPath("$[0].lastName", is("Doe")))
				.andExpect(jsonPath("$[0].city", is("Sydney")))
				.andExpect(jsonPath("$[0].id", is(1)));

	}
	@Test
	public void getownersByPetId() throws Exception {

		this.mockMvc
				.perform(get("/owners/2").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is("Jane")))
				.andExpect(jsonPath("$.lastName", is("Doe")))
				.andExpect(jsonPath("$.city", is("Perth")))
				.andExpect(jsonPath("$.id", is(2)));

	}
	
	@Test
	public void getownersByPetId_InvalidPetId() throws Exception {

		this.mockMvc
				.perform(get("/owners/200").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}
	
	
}
