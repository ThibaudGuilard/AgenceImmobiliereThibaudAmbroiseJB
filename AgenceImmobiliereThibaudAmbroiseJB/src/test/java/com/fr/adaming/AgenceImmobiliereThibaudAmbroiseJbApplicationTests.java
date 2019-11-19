package com.fr.adaming;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AgenceImmobiliereThibaudAmbroiseJbApplicationTests {
	
	@Autowired
	protected MockMvc mvc;

	@Autowired
	protected ObjectMapper mapper;
	
	@Rule
	protected ExpectedException exception = ExpectedException.none();

	
}
