package com.task2.blogapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Prakriti",
                        email = "prakritid41@gmail.com"
                ),
                description = "OpenApi documentation for BlogApp with spring security",
                title = "OpenApi specification - Prakriti",
                version = "1.0",
                license = @License(
                        name = "License Name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of Service"
        ),


        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:8081"


                )

        },

        security = {
                @SecurityRequirement(
                        name = "bearerAuth")

        }


)

@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth Description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER

)

public class OpenApiConfig {

}
