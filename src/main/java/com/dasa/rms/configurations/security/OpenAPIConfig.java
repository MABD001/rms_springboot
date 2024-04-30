package com.dasa.rms.configurations.security;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenAPIConfig {
    @Value("http://localhost:8089")
    private String devUrl;

    @Value("https://localhost:8080")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("muhammadabdullah73@gmail.com");
        contact.setName("Muhammad Abdullah");


        Info info = new Info()
                .title("DASA Restaurant Management System")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage this application.");


        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
