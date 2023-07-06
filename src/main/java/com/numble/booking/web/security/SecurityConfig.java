package com.numble.booking.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
public class SecurityConfig{

    private final CustomUserDetailService customUserDetailsService;

    public SecurityConfig(@Lazy CustomUserDetailService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 스프링 시큐리티 규칙
     * 보안 구성의 추가적인 설정을 수행
     * 보안 구성의 세부사항을 구성하고, 필터 체인, 인증 및 권한 설정 등을 정의
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()                             // CSRF 비활성화
                .headers().frameOptions().disable().and() // X-Frame-Options 비활성화
                .formLogin().disable()                    // 폼 로그인 비활성화
                .authorizeRequests()
                .antMatchers("/").permitAll()
//                .antMatchers("/public").permitAll()
//                .antMatchers("/private").authenticated()
//                .anyRequest().authenticated()
                .anyRequest().permitAll();
        return http.build();
    }

    /**
     * 사용자 인증하는 방법이 담긴 객체
     */
    @Bean
    public DaoAuthenticationProvider userDetailServices() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    /**
     * 스프링 시큐리티 룰을 무시하게 하는 url 규칙 (여기 등록하면 규칙 적용하지 않음)
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
//                "/resources/**",
//                "/css/**",
//                "/favicon/**",
//                "/img/**",
                "/swagger*/**"
        );
    }
}
