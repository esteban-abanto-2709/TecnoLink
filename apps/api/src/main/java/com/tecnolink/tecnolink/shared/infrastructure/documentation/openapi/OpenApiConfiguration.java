package com.tecnolink.tecnolink.shared.infrastructure.documentation.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI tecnolinkOpenApi() {
        return new OpenAPI().info(new Info()
                .title("TecnoLink API")
                .version("1.0.0")
                .description("API de catalogo, cotizaciones, compras, proveedores y usuarios con DDD y CQRS."));
    }
}
