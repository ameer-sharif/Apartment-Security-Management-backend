package com.cg.aps;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.aps.entities.FlatEntity;
import com.cg.aps.service.FlatRentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlatControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getFlatEntity_thenStatus200() throws Exception {
		mvc.perform(get("http://localhost:9997/flats").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].userId", is(7)));
	}

	@Test
	public void getFlatEntity_whennoFlats_thenStatus400() throws Exception {
		mvc.perform(get("http://localhost:9997/abc")).andExpect(status().isNotFound());

	}

	
	@Test
	public void test_getFlatById_success() throws Exception {
		mvc.perform(get("http://localhost:9997/flats/id/{userId}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.userId", is(1)));
	}
	
	@Test
	public void test_getFlatById_fail_404_not_found() throws Exception {
		mvc.perform(get("http://localhost:9997/flats/id/{userId}", 10)).andExpect(status().isNotFound());
	}

	
	
	
//	@Test
//	public void test_get_insertFlat_test() throws Exception {
//		FlatEntity flat = new FlatEntity(16,"O16","F16","16","A16");
//	
//		mvc.perform(post("http://localhost:9997/flats")
//				.content(asJsonString(flat)))
//				.andExpect(jsonPath("userId", is(16)))
//				.andExpect(jsonPath("$.ownerName", is("O16")))
//				.andExpect(jsonPath("$.flatNo", is("F16")))
//				.andExpect(jsonPath("$.floorNo", is("16")))
//				.andExpect(jsonPath("$.flatType", is("A16")));
//	}
	
//	@Test
//	public void test_createOpportunity_thenstatus201() throws Exception {
//		
//		
//
//		mvc.perform(post("http://localhost:9997/flats")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(asJsonString(flat)))
//				.andExpect(status().isCreated());
//
//	}
	
	@Test
	public void test_delete_oppo_success() throws Exception {
		int oppId = 1;
		mvc.perform(delete("/opportunity/{opportunityId}", oppId))
		.andExpect(status().is2xxSuccessful());

	}
	

//	@Test
//	public void test_delete_flat_success() throws Exception {
//		int userId = 6;
//		mvc.perform(delete("/flats/{userId}", userId))
//		.andExpect(status().is2xxSuccessful())
//		.andExpect(header().string("location", Matchers.equalToIgnoringCase("http://localhost:9997/flats/6")));
//
//	}

	@Test
	public void test_donotdelete_course_notfound() throws Exception {
		mvc.perform(delete("/flats/{userId}", 11)).andExpect(status().isNotFound());
	}

	
	
	
//	@Test
//	public void test_update_flat_success() throws Exception {
//		FlatEntity flat = new FlatEntity();
//
//		mvc.perform(put("/flats/{userId}",1).contentType(MediaType.APPLICATION_JSON)
//				.content(asJsonString(flat)))
//				.andExpect(status().isCreated())
//				.andExpect(header().string("location", containsString("http://localhost:9997/flats/1")));
//	}

	@Test
	public void test_donotupdate_flat_notfound() throws Exception {
		mvc.perform(delete("/flats/{userId}", 11)).andExpect(status().isNotFound());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}



//1. For success List<Employee> , Employee  , URL ()
//2. throw exceptions, new ResponseEntity<>(employeeList, HttpStatus.NOT_FOUND)



