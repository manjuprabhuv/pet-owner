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
public class PetAPITest {

	@Autowired
	private MockMvc mockMvc;

	/*private MockRestServiceServer mockRestServiceServer;

	@Autowired
	private RestOperations restOperations;

	
*/
	@Autowired
	private ObjectMapper mapper;
	@Test
	public void getPets() throws Exception {

		this.mockMvc
				.perform(get("/pets").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)))
				.andExpect(jsonPath("$[0].name", is("Benji")))
				.andExpect(jsonPath("$[0].birthday", is("2017-10-11")))
				.andExpect(jsonPath("$[0].ownerId", is(1)));

	}
	@Test
	public void getPetsByPetId() throws Exception {

		this.mockMvc
				.perform(get("/pets/2").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Bandit")))
				.andExpect(jsonPath("$.birthday", is("2016-01-01")))
				.andExpect(jsonPath("$.ownerId", is(2)));

	}
	
	@Test
	public void getPetsByPetId_InvalidPetId() throws Exception {

		this.mockMvc
				.perform(get("/pets/200").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());

	}
	
	@Test
	public void addPet() throws Exception {
		
		Pet pet = new Pet();
		pet.setName("BowBow");
		pet.setOwnerId(2L);
		pet.setBirthday(LocalDate.now());

		this.mockMvc
				.perform(post("/pets").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(pet)))
				.andExpect(status().isCreated());
				

	}
	@Test
	public void addPet_InvalidRequest() throws Exception {
		
		Pet pet = new Pet();
		pet.setName("");
		pet.setOwnerId(2L);
		pet.setBirthday(LocalDate.now());

		this.mockMvc
				.perform(post("/pets").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(pet)))
				.andExpect(status().is4xxClientError());
				

	}
	
	@Test
	public void addPet_InvalidDate1() throws Exception {
		
		Pet pet = new Pet();
		pet.setName("Benji");
		pet.setOwnerId(2L);
		pet.setBirthday(LocalDate.of(2019, 01, 01));

		this.mockMvc
				.perform(post("/pets").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(pet)))
				.andExpect(status().is4xxClientError());
				

	}
	@Test
	public void addPet_InvalidDate2() throws Exception {
		
		Pet pet = new Pet();
		pet.setName("Benji");
		pet.setOwnerId(2L);
		pet.setBirthday(LocalDate.of(1900, 01, 01));

		this.mockMvc
				.perform(post("/pets").header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(pet)))
				.andExpect(status().is4xxClientError());
				

	}

}
