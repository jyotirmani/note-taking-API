package com.example.easynotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by JS
 */

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public WebSecurityConfig(){
        super(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf()
                .ignoringAntMatchers(AppConstants.API_BASE_URL + "**")
                .and()
                .authorizeRequests()
                // Home and Cors
                .antMatchers("/", "/csrf")
                .permitAll()
                // Swagger
                .antMatchers("/", "/swagger-ui.html**/**", "/v2/api-docs**", "/swagger-resources/**", "/webjars/**")
                .permitAll()
                // server
                .antMatcher("/server/**")
                .authorizeRequests().anyRequest().authenticated();
                // Notes API Create and Get by ID
                .antMatchers(HttpMethod.GET, AppConstants.Notes.PATH_V1 + "/*")
                .permitAll()
                .antMatchers(HttpMethod.POST, AppConstants.Notes.PATH_V1)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}