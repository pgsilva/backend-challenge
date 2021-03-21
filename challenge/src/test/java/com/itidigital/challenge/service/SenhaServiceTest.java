package com.itidigital.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SenhaServiceTest {

	@InjectMocks
	private SenhaService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public final void testValidarSenhaVazia() throws Exception {
		// IsValid("") -- false
		String senha = " ";
		assertEquals(false, service.validarSenha(senha));
	}

	@Test
	public final void testValidarSenha1() throws Exception {
		String senha = "aa";
		assertEquals(false, service.validarSenha(senha));
	}

	@Test
	public final void testValidarSenha2() throws Exception {
		String senha = "ab";
		assertEquals(false, service.validarSenha(senha));
	}

	@Test
	public final void testValidarSenha3() throws Exception {
		String senha = "AAAbbbCc";
		assertEquals(false, service.validarSenha(senha));
	}

	@Test
	public final void testValidarSenha4() throws Exception {
		String senha = "AbTp9!foo";
		assertEquals(false, service.validarSenha(senha));
	}

	@Test
	public final void testValidarSenha5() throws Exception {
		String senha = "AbTp9!foA";
		assertEquals(false, service.validarSenha(senha));
	}

	@Test
	public final void testValidarSenha6() throws Exception {
		String senha = "AbTp9 fok";
		assertEquals(false, service.validarSenha(senha));
	}

	@Test
	public final void testValidarSenha7() throws Exception {
		String senha = "AbTp9!fok";
		assertEquals(true, service.validarSenha(senha));
	}

}
