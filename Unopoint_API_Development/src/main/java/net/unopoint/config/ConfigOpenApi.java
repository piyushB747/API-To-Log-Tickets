package net.unopoint.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title = " API BY PIYUSH CONSUMING BY ANDROID APPLICATION  ",
				description = "  ",
				version = "v1.1",
				contact = @Contact(
						name = "Singh Piyush",
						email = "singhrajpiyush@gmail.com",
						url = ""
				),
				license = @License(
						name = "API BY PIYUSH",
						url = ""
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot API Integration With ANDORID FLUTTER",
				url = "PIYUSH"
		)
)
@Configuration
public class ConfigOpenApi {

	
}
