package com.numble.booking.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.numble.booking.web.security.filter.JwtAuthenticationFilter;
import com.numble.booking.web.security.service.CustomUserDetailsService;

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

    public SecurityConfig(@Lazy CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
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
                .authorizeRequests(authorize -> authorize
                        .antMatchers(ignorePaths).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http.addFilter(jwtAuthenticationFilter(authenticationManager));
        }
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider());
        // TODO
        return jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }
}
