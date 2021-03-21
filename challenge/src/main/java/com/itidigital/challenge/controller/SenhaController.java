package com.itidigital.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itidigital.challenge.service.SenhaService;

@RestController
@RequestMapping("senha")
@CrossOrigin
public class SenhaController {

	@Autowired
	private SenhaService service;

	private static final Logger log = LoggerFactory.getLogger(SenhaController.class);

	@GetMapping(path = "/validacao/{senha}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getValidarSenha(@PathVariable(required = true) String senha) {
		try {
			return ResponseEntity.ok(service.validarSenha(senha));
		} catch (Exception e) {
			log.error("Erro.", e);
			return ResponseEntity.badRequest().body(null);
		}
	}

}
