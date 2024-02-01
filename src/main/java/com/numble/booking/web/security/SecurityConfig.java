package com.numble.booking.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.user.service.UserLoginService;
import com.numble.booking.web.security.filter.JwtAuthorizationFilter;
import com.numble.booking.web.security.handler.CustomSavedRequestAwareAuthenticationSuccessHandler;
import com.numble.booking.web.security.handler.CustomSimpleUrlAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.numble.booking.web.security.filter.JwtAuthenticationFilter;
import com.numble.booking.web.security.service.CustomUserDetailsService;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * <pre>
 * Class Name : SecurityConfig
 * Description :
 *
 * Modification Information
 * Modify Date      Modifier    Comment
 * -------------------------------------------------------------
 * 2023-06-22 	    chaerin 	New
 * </pre>
 *
 * @author chaerin
 * @since 2023-06-22
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final UserLoginService userLoginService;
    private final ObjectMapper objectMapper;

    public SecurityConfig(@Lazy CustomUserDetailsService customUserDetailsService, @Lazy UserLoginService userLoginService,
                          ObjectMapper objectMapper) {
        this.customUserDetailsService = customUserDetailsService;
        this.userLoginService = userLoginService;
        this.objectMapper = objectMapper;
    }

    // spring security 필터 체인
    @Bean
    SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        String[] ignorePaths = {
                "/swagger-ui.html", "/swagger-ui/**", "/v2/api-docs", "/webjars/**", "/swagger-resources/**", // swagger
                "/login", "/error"
        };
        http.csrf().disable() // stateless
                .headers().frameOptions().disable().and() // X-Frame-Options 헤더 비활성화
                .formLogin().disable() // form login 사용 x
                .httpBasic().disable()
                .apply(new CustomDsl())
                .and()
                // JWT Token 기반의 login 처리로 세션은 비 활성화 처리 한다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(loginUrlAuthenticationEntryPoint())
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers(ignorePaths).permitAll()
                        .antMatchers(HttpMethod.POST, "/users/sign-up").permitAll() // 회원 가입
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .addFilter(jwtAuthenticationFilter(authenticationManager));
        }
    }

    @Bean
    public CustomDaoAuthenticationProvider customDaoAuthenticationProvider() {
        CustomDaoAuthenticationProvider authenticationProvider = new CustomDaoAuthenticationProvider(userLoginService);
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider());
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        jwtAuthenticationFilter.setAuthenticationSuccessHandler(
                customSavedRequestAwareAuthenticationSuccessHandler()
        );

        jwtAuthenticationFilter.setAuthenticationFailureHandler(
                customSimpleUrlAuthenticationFailureHandler()
        );
        jwtAuthenticationFilter.afterPropertiesSet();
        return jwtAuthenticationFilter;
    }

    public Filter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtTokenProvider(), objectMapper);
    }

    @Bean
    public CustomSavedRequestAwareAuthenticationSuccessHandler customSavedRequestAwareAuthenticationSuccessHandler() {
        return  new CustomSavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    public CustomSimpleUrlAuthenticationFailureHandler customSimpleUrlAuthenticationFailureHandler() {
        CustomSimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new CustomSimpleUrlAuthenticationFailureHandler();
        authenticationFailureHandler.setDefaultFailureUrl("/?error=e");

        return authenticationFailureHandler;
    }

    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint("/", objectMapper);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(objectMapper);
    }
}
