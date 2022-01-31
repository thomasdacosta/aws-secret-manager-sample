# AWS Secret Manager com Spring Cloud AWS

Usando o AWS Secret Manager em uma aplicação Spring Boot com Spring Cloud AWS.

### LocalStack

Para habilitar o LocalStack, usar o seguinte profile:

```
-Dspring.profiles.active=localstack
```

Para execução com o LocalStack é necessário mudar o endpoint do Secret Manager no arquivo **bootstrap.yml** para o endereço do LocalStack:

```
aws:
  secretsmanager:
    endpoint: http://localhost:4566
```

Além disso a região padrão deve ser sempre **us-east-1** para testar com LocalStack.

Crie um profile separado com o AWS Cli:

```
aws configure --profile localstack

AWS Access Key ID [None]: test
AWS Secret Access Key [None]: test
Default region name [None]: us-east-1
Default output format [None]:
```

Na criação das chaves do Secret Manager incluir o paramêtro **--profile**

```
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/aws-secret-manager-sample_localstack --description "Chaves para a aplicação do Spring Boot" --secret-string "{\"user\":\"Bruce Wayne\",\"password\":\"Eu sou o Batman\"}"
```

Comandos para criação das chaves usando o AWS Cli:

```
aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/aws-secret-manager-sample --description "Chaves para a aplicação do Spring Boot" --secret-string "{\"user\":\"Bruce Wayne\",\"password\":\"Eu sou o Batman\"}"

aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/application_localstack --description "Chaves para a aplicação do Spring Boot" --secret-string "{\"user\":\"Bruce Wayne\",\"password\":\"Eu sou o Batman\"}"

aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/application --description "Chaves para a aplicação do Spring Boot" --secret-string "{\"user\":\"Bruce Wayne\",\"password\":\"Eu sou o Batman\"}"

```

### Documentação

[3.4. Integrating your Spring Cloud application with the AWS Secrets Manager](https://docs.awspring.io/spring-cloud-aws/docs/2.3.0/reference/html/index.html#integrating-your-spring-cloud-application-with-the-aws-secrets-manager)


---

Thomás da Costa - [https://thomasdacosta.com.br](https://thomasdacosta.com.br)




