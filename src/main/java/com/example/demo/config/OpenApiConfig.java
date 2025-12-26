package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Conflict of Interest Detection API")
                        .description("Backend REST APIs")
                        .version("1.0"))
                .servers(List.of(
                        new Server()
                                .url("https://9098.32procr.amypo.ai")
                                .descript       ion("Production Server")
                ));
    }
}
