package co.istad.bmsapi.config;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo("iSTAD Reading APIs",
                "APIs for iSTAD Reading App",
                "1.0",
                "Terms of service",
                new Contact("077-45-99-47", "www.istad.co", "service.istad@gmail.com"),
                "License of Usage",
                "https://www.istad.co/page/privacy-policy",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {

        SecurityReference securityReference = SecurityReference.builder()
                .reference("basicAuth")
                .scopes(new AuthorizationScope[0])
                .build();

        ArrayList<SecurityReference> references = new ArrayList<>(1);
        references.add(securityReference);

        ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
        securityContexts.add(SecurityContext.builder().securityReferences(references).build());

        ArrayList<SecurityScheme> auth = new ArrayList<>(1);
        auth.add(new BasicAuth("basicAuth"));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(auth)
                .securityContexts(securityContexts)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
