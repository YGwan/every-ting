package com.everyTing.core.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${auth.jwt.token.access.key}")
    private String apiKey;

    @Value("${constraint.docs.name}")
    private String constraintDocs;

    @Value("${constraint.docs.url}")
    private String constraintDocsUrl;

    @Value("${error.docs.name}")
    private String errorDocs;

    @Value("${error.docs.url}")
    private String errorDocsUrl;

    @Bean
    public OpenAPI buildOpenApi() {
        return new OpenAPI()
            .info(new Info()
                .title("EVERY_TING")
                .version("0.0")
                .description("생성 AI를 이용한 이상형 매칭 과팅 & 소개팅 어플리케이션")
                .license(
                    new License().name(constraintDocs)
                                 .url(constraintDocsUrl)
                )
            )
            .externalDocs(new ExternalDocumentation()
                .description(errorDocs)
                .url(errorDocsUrl));
    }

    @Bean
    public GroupedOpenApi SecurityGroupOpenApi() {
        return GroupedOpenApi
            .builder()
            .group("Security Open Api")
            .addOpenApiCustomiser(buildSecurityOpenApi())
            .build();
    }

    public OpenApiCustomiser buildSecurityOpenApi() {
        SecurityScheme securityScheme = new SecurityScheme()
            .name(apiKey)
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .scheme("bearer");

        return OpenApi -> OpenApi
            .addSecurityItem(new SecurityRequirement().addList("jwt token"))
            .getComponents()
            .addSecuritySchemes("jwt token", securityScheme);
    }
}