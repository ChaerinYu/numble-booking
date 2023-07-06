package com.numble.booking.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <pre>
 * Class Name : SwaggerConfig
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-07-06 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-07-06
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo())
                .useDefaultResponseMessages(false)
                .groupName("All")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("공연 예약 시스템")
                .description("예약 시스템입니다.")
                .contact(new Contact(
                        "Chaerin Yu"
                        , "https://github.com/ChaerinYu"
                        , "chaeri.du.ub@gmail.com"))
                .version("1.0.0")
                .build();
    }
}
