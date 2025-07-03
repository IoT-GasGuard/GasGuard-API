package com.gg.gasguardapi.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfiguration {

    @Value("${swagger.server-url}")
    private String swaggerServerUrl;

    @Bean
    public OpenAPI ggApiOpenApi() {

        // General configuration
        var openApi = new OpenAPI();

        Server server = new Server();
        server.setUrl(swaggerServerUrl);
        openApi.addServersItem(server)
                .info(new Info()
                        .title("GasGuard API REST")
                        .description("GasGuard API documentation.")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
                /*.externalDocs(new ExternalDocumentation()
                        .description("GasGuard Api wiki Documentation")
                        .url("https://gasguard-api.wiki.github.io/docs"));*/

        // Add security scheme

        final String securitySchemeName = "bearerAuth";

        openApi.addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));

        // Return OpenAPI configuration object with all the settings

        return openApi;
    }
}
