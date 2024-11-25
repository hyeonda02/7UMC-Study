package umc7.spring.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI UMCOpenAPI() {
        Info info = new Info()
                .title("UMC SpringBoot WorkBook API")
                .description("<h3>UMC SpringBoot WorkBook API 명세서</h3>")
                .version("1.0.0");

        String jwtSchemaName = "JWT TOKEN";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemaName);

        Components components = new Components()
                .addSecuritySchemes(jwtSchemaName, new SecurityScheme()
                        .name(jwtSchemaName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("barer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);


    }
}
