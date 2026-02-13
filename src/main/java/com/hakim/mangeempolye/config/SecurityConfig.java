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
            .cors().and()  // Asegúrate de que CORS esté habilitado
            .csrf().disable()  // Desactiva CSRF temporalmente para pruebas
            .authorizeRequests()
            .antMatchers("/employee/api/v1/add").permitAll() 
             .antMatchers("/employee/api/v1/find ").permitAll() 
             .antMatchers("/actuator/health").permitAll()
            .anyRequest().authenticated();
 // Permite acceso sin autenticación
         //   .antMatchers("/employee/api/v1/all").permitAll() 
           // Permite acceso sin autenticación
          //  .anyRequest().authenticated();  // Requiere autenticación para otros endpoints
    }
}
