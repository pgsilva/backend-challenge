package com.itidigital.challenge.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class SenhaService {

	/**
	 * Considere uma senha sendo válida quando a mesma possuir as seguintes
	 * definições:
	 * 
	 * Nove ou mais caracteres Ao menos 1 dígito Ao menos 1 letra minúscula Ao menos
	 * 1 letra maiúscula Ao menos 1 caractere especial Considere como especial os
	 * seguintes caracteres: !@#$%^&*()-+ Não possuir caracteres repetidos dentro do
	 * conjunto
	 * 
	 * @param senha
	 * @return bool
	 */
	public Boolean validarSenha(String senha) throws Exception {

		Map<String, Long> x = senha.chars().mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
		Boolean naoRepete = x.values().stream().allMatch(i -> i.equals((long) 1));

		return senha.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-+])(?=\\S+$).{9,}$") && naoRepete;

	}

}
