package com.app.rang.spring.config;

import com.app.rang.spring.CustomLoginSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@Log4j2
public class CustomSecurityConfig {
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //페이지 권한 설정
        http.authorizeHttpRequests()
                .antMatchers("/board/**", "/mypage", "/comment/**").authenticated() // 해당 페이지 요청시 인증 요구
                .antMatchers("/").permitAll(); //누구나 다 들어옴

        // 로그인 페이지
        http.formLogin().loginPage("/login").successHandler(successHandler()); // successHandler 설정을 하지 않을 경우 이전 페이지로 돌아감

        // 403 에러시 처리(권한 문제)

        //로그아웃 페이지
        http.logout().logoutSuccessUrl("/");//.logoutUrl("/logout");

        //자동 로그인
        http.rememberMe().key("123456789").rememberMeParameter("remember-me").tokenValiditySeconds(60*60*24);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
