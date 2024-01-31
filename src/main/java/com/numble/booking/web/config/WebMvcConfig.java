package com.numble.booking.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * Class Name : WebMvcConfig
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2024-01-31 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2024-01-31
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer
                .favorParameter(false) // 쿼리 문자열 파라미터를 통한 콘텐츠 협상을 비활성화. Accept 헤더를 통해 콘텐츠 협상을 진행
                .ignoreAcceptHeader(true) // Accept 헤더 무시하고 서버에서 응답 형식 결정
                .defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // 스웨거 리소스 연결 추가
        registry
                .addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
