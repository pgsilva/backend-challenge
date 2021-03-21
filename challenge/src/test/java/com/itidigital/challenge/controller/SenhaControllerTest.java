package com.itidigital.challenge.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.itidigital.challenge.service.SenhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SenhaControllerTest {

	@InjectMocks
	private SenhaController controller;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private SenhaService service;

	@Mock
	private ResponseEntity<?> responseEntity;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();

	}

	@Test
	public final void testGetValidarSenhaTrue() throws Exception {

		Mockito.when(service.validarSenha(Mockito.anyString())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/senha/validacao/AbTp9!fok").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string("true")).andReturn();
	}

	@Test
	public final void testGetValidarSenhaFalse() throws Exception {
		Mockito.when(service.validarSenha(Mockito.anyString())).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.get("/senha/validacao/AbTp9!foA").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string("false")).andReturn();
	}

	@Test
	public void testGetValidarSenhaException() throws Exception {
		when(service.validarSenha(Mockito.anyString())).thenThrow(Exception.class);
		
		responseEntity = (ResponseEntity<?>) controller.getValidarSenha(Mockito.anyString());
		assertTrue(responseEntity.getBody() == null);
		assertTrue(responseEntity.getStatusCodeValue() == 400);
	}
}
