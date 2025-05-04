package cz.cpost.flight.viewer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration class for the FlightViewer application.
 * This class configures the OpenAPI documentation for the REST API.
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("FlightViewer API")
                        .version("1.0")
                        .description("API documentation in JSON format"));
    }
}
