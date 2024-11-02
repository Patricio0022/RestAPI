package org.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 17 and Spring Boot 3")
                        .version("v1")
                        .description("This API allows users to manage resources efficiently.")
                        .termsOfService("https://github.com/Patricio0022/RestAPI")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("")
                        )
                );
    }
}
