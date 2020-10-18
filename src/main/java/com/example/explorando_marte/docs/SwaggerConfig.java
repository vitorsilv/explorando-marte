package com.example.explorando_marte.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.explorando_marte.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Explorando Marte - Docs")
                .description("REST API criada e documentada por Vitor Silva!\n Tentei utilizar os padroes REST e o que aprendi em minhas aulas de Java, como não pratico programação orientada a objetos há muito não se se está da melhor forma, agradeço qualquer tipo de feedback.")
                .version("1.0.0")
                .contact(new Contact("Vitor Silva", "https://www.linkedin.com/in/vitorsilv/", "vitor.procont@gmail.com"))
                .build();
    }
}