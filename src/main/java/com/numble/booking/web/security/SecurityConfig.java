package com.numble.booking.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.numble.booking.user.service.UserLoginService;

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

    private final CustomUserDetailService customUserDetailsService;
    private final UserLoginService userLoginService;
    private final ObjectMapper objectMapper;

    public SecurityConfig(@Lazy CustomUserDetailService customUserDetailsService,
                          @Lazy UserLoginService userLoginService,
                          ObjectMapper objectMapper) {
        this.customUserDetailsService = customUserDetailsService;
        this.userLoginService = userLoginService;
        this.objectMapper = objectMapper;
    }


    /**
     * 정적 자원(Resource)에 대해서 인증된 사용자가  정적 자원의 접근에 대해 ‘인가’에 대한 설정을 담당하는 메서드이다.
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

    /**
     * HTTP에 대해서 ‘인증’과 ‘인가’를 담당하는 메서드이며 필터를 통해 인증 방식과 인증 절차에 대해서 등록하며 설정을 담당하는 메서드이다.
     * 스프링 시큐리티 규칙
     * 보안 구성의 추가적인 설정을 수행
     * 보안 구성의 세부사항을 구성하고, 필터 체인, 인증 및 권한 설정 등을 정의
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()                             // CSRF 비활성화
                .headers().frameOptions().disable().and() // X-Frame-Options 비활성화
                // form 기반의 로그인에 대해 비 활성화하며 커스텀으로 구성한 필터를 사용한다.
                .formLogin().disable()                    // 폼 로그인 비활성화
                // 토큰을 활용하는 경우 모든 요청에 대해 '인가'에 대해서 사용.
                .authorizeHttpRequests((authz) -> authz.anyRequest().permitAll())
                // Spring Security Custom Filter Load - Form '인증'에 대해서 사용
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // Session 기반의 인증기반을 사용하지 않고 추후 JWT를 이용하여서 인증 예정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/public").permitAll()
//                .antMatchers("/private").authenticated()
//                .anyRequest().authenticated()
//                .anyRequest().permitAll();



        return http.build();
    }

    /**
     * 3. authenticate 의 인증 메서드를 제공하는 매니져로'Provider'의 인터페이스를 의미합니다.
     * - 과정: CustomAuthenticationFilter → AuthenticationManager(interface) → CustomAuthenticationProvider(implements)
     *
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider());
    }

    /**
     * 4. '인증' 제공자로 사용자의 이름과 비밀번호가 요구됩니다.
     * - 과정: CustomAuthenticationFilter → AuthenticationManager(interface) → CustomAuthenticationProvider(implements)
     *
     * @return CustomAuthenticationProvider
     */
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider((BCryptPasswordEncoder) passwordEncoder());
    }

    /**
     * 5. 비밀번호를 암호화하기 위한 BCrypt 인코딩을 통하여 비밀번호에 대한 암호화를 수행합니다.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 6. 커스텀을 수행한 '인증' 필터로 접근 URL, 데이터 전달방식(form) 등 인증 과정 및 인증 후 처리에 대한 설정을 구성하는 메서드입니다.
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManager(), userLoginService, objectMapper);
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());    // '인증' 성공 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler());    // '인증' 실패 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    /**
     * 7. Spring Security 기반의 사용자의 정보가 맞을 경우 수행이 되며 결과값을 리턴해주는 Handler
     *
     * @return CustomLoginSuccessHandler
     */
    @Bean
    public CustomAuthSuccessHandler customLoginSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    /**
     * 8. Spring Security 기반의 사용자의 정보가 맞지 않을 경우 수행이 되며 결과값을 리턴해주는 Handler
     *
     * @return CustomAuthFailureHandler
     */
    @Bean
    public CustomAuthFailureHandler customLoginFailureHandler() {
        return new CustomAuthFailureHandler();
    }

    /**
     * 사용자 인증하는 방법이 담긴 객체
     */
//    @Bean
//    public DaoAuthenticationProvider userDetailServices() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(customUserDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setHideUserNotFoundExceptions(false);
//        return authenticationProvider;
//    }

    /**
     * successHandler bean register
     */
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        CustomSavedRequestAwareAuthenticationSuccessHandler handler = new CustomSavedRequestAwareAuthenticationSuccessHandler();
//        handler.setDefaultTargetUrl("/index");
//        return handler;
//    }

    /**
     * failureHandler bean register
     */
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        CustomSimpleUrlAuthenticationFailureHandler handler = new CustomSimpleUrlAuthenticationFailureHandler();
//        handler.setDefaultFailureUrl("/?error=error");
//        return handler;
//    }

    /**
     * login시 걸리는 filter bean register
     */
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
//        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
//        jwtAuthenticationFilter.setUsernameParameter("username");
//        jwtAuthenticationFilter.setPasswordParameter("password");
//
//        jwtAuthenticationFilter.setAuthenticationSuccessHandler(
//                authenticationSuccessHandler()
//        );
//
//        jwtAuthenticationFilter.setAuthenticationFailureHandler(
//                authenticationFailureHandler()
//        );
//        jwtAuthenticationFilter.afterPropertiesSet();
//        return jwtAuthenticationFilter;
//    }


}
