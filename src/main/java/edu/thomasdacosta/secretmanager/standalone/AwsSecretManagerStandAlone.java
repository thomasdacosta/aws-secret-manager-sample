package edu.thomasdacosta.secretmanager.standalone;

import java.io.IOException;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;
import com.amazonaws.services.secretsmanager.model.InvalidRequestException;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AwsSecretManagerStandAlone {

	/**
	 * Código baseado na documentação da AWS:
	 * 	 * https://docs.aws.amazon.com/prescriptive-guidance/latest/patterns/manage-credentials-using-aws-secrets-manager.html
	 * 
	 * Comando do AWS Cli para o programa abaixo:
	 * aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name DCComics --description "Segredo do Batman" --secret-string "{\"BruceWayne\":\"Seria ele o Batman?\"}"
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String secretName = "DCComics";
		AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(
				"http://localhost:4566", null);

		AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
		clientBuilder.setEndpointConfiguration(config);

		AWSSecretsManager client = clientBuilder.build();

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode secretsJson = null;

		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
		GetSecretValueResult getSecretValueResponse = null;

		try {
			getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
		} catch (ResourceNotFoundException e) {
			System.err.println("The requested secret " + secretName + " was not found");
		} catch (InvalidRequestException e) {
			System.err.println("The request was invalid due to: " + e.getMessage());
		} catch (InvalidParameterException e) {
			System.err.println("The request had invalid params: " + e.getMessage());
		}

		if (getSecretValueResponse == null) {
			System.err.println("getSecretValueResponse is null");
			System.exit(0);
		}

		String secret = getSecretValueResponse.getSecretString();
		if (secret != null) {
			try {
				secretsJson = objectMapper.readTree(secret);
			} catch (IOException e) {
				System.err.println("Exception while retrieving secret values: " + e.getMessage());
			}
		} else {
			System.err.println("The Secret String returned is null");
			System.exit(0);
		}
		
		System.out.println("Secret Value: " + secretsJson.get("BruceWayne").textValue()); 

	}

}
