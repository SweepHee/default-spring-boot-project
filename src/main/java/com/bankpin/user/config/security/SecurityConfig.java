package com.bankpin.user.config.security;

import com.bankpin.user.config.auth.handler.AuthFailureHandler;
import com.bankpin.user.config.auth.handler.AuthLogoutSuccessHandler;
import com.bankpin.user.config.auth.handler.AuthSuccessHandler;
import com.bankpin.user.config.auth.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Autowired
    private UserAuthService userService;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private AuthLogoutSuccessHandler authLogoutSuccessHandler;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/index", "/auth/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .usernameParameter("id")
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login/process")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(authLogoutSuccessHandler)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        ;

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
