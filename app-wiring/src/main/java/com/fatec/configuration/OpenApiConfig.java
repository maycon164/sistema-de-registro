package com.fatec.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Maycon Huanca",
                        email =  "mayconfelipe164@gmail.com",
                        url = "https://github.com/maycon164"
                ),
                title = "Skill Matrix API",
                description = "OpenApi documentation for SkillMatrix Project",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENVIRONMENT",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "QA ENVIRONMENT",
                        url = "http//gcp."
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
