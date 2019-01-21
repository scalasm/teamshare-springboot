package it.linksmt.teamshare;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
	@Bean
	public Docket api() {
		return new Docket( DocumentationType.SWAGGER_2 )
				.apiInfo( apiInfo() )
				.select().apis( RequestHandlerSelectors.any() )
				.paths( PathSelectors.any() )
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title( "Team Share API" )
			.description( "RESTful API for Team Share " )
			.version( "1.0" )
			.contact( 
					new Contact( "The A Team", "https://www.linksmt.it/a-team", "a-team@linksmt.it" ) )
			.build();
	}	
	
	@Override
	public void addResourceHandlers( ResourceHandlerRegistry registry ) {
		registry
			.addResourceHandler( "swagger-ui.html" )
			.addResourceLocations( "classpath:/META-INF/resources/" );

		registry
			.addResourceHandler( "/webjars/**" )
			.addResourceLocations( "classpath:/META-INF/resources/webjars/" );
	}
}