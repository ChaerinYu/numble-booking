package com.numble.booking.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import lombok.RequiredArgsConstructor;

import java.util.List;

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

    /**
     * Pageable resolver setting
     * Swagger config 클래스에 Pageable 을 파라미터로 받을 수 있게 설정
     */
    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        // page 객체 설정
        final PageableHandlerMethodArgumentResolver page = new PageableHandlerMethodArgumentResolver();
        page.setMaxPageSize(1000);				// 최대 1000개 이상은 조회가 안되도록 제한 한다.
        page.setOneIndexedParameters(true);		// 1페이지가 첫번째 페이지가 되도록 처리 한다. page+1 을 자동으로 처리 함

        argumentResolvers.add(page);
        super.addArgumentResolvers(argumentResolvers);
    }

}
