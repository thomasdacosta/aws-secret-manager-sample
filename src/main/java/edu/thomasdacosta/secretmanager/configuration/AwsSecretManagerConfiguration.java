package edu.thomasdacosta.secretmanager.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
public class AwsSecretManagerConfiguration {

	@Getter @Setter
	@Value("${user}")
	private String user;
	
	@Getter @Setter
	@Value("${password}")
	private String password;	

}
