package edu.thomasdacosta.secretmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.thomasdacosta.secretmanager.configuration.AwsSecretManagerConfiguration;

@RestController
@RequestMapping("/")
public class AwsSecretManagerController {
	
	@Autowired
	private AwsSecretManagerConfiguration configuration;
	
	@GetMapping(path = "secretManager")
	public String keysSecretManager() {
		return String.format("Como entrar na BatCaverna --> usuário: %s e senha: %s", configuration.getUser(), configuration.getPassword());
	}
	
}
