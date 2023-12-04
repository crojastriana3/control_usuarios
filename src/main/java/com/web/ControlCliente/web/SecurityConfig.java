package com.web.ControlCliente.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration//clase de cong spring
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());//usamos la clase userDetailService
    }

    @Bean //Restricción de path, autorización de rutas
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http.authorizeRequests()
                .requestMatchers("/addPerson/**", "/savePerson/**", "/deleted/**", "/updated/**")//, "updated/{id}/**"
                .hasRole("ADMIN")
                .requestMatchers("/")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()

                )
                .exceptionHandling(form -> form
                        .accessDeniedPage("/errors/403"));
                /*.exceptionHandling(form -> form
                        .accessDeniedHandler((request, response, accessDeniedException) -> );*/

        return http.build();
    }
}
