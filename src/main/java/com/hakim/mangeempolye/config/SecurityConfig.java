package com.hakim.mangeempolye.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
            // API endpoints
            .antMatchers("/employee/api/v1/add").permitAll()
            .antMatchers("/employee/api/v1/all").permitAll()
            .antMatchers("/employee/api/v1/find/**").permitAll()
            // Actuator endpoints
            .antMatchers("/actuator/health").permitAll()
            .antMatchers("/actuator/prometheus").permitAll() // ← importante para Prometheus
            // cualquier otro endpoint requiere autenticación si quieres
            .anyRequest().authenticated();
    }
}