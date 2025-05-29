package com.seu.javaFriday.Service;

import com.seu.javaFriday.Security.JwtAuthFilter;
import com.seu.javaFriday.Service.TokenService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtFilter(JwtAuthFilter jwtAuthFilter) {
        FilterRegistrationBean<JwtAuthFilter> registrationBean = new FilterRegistrationBean<>();

        // Use the Spring-managed JwtAuthFilter bean instead of creating a new instance
        registrationBean.setFilter(jwtAuthFilter);

        // Set URL patterns for the filter
        registrationBean.addUrlPatterns("/user/*", "/admin/*");

        // Set order (lower number = higher priority)
        registrationBean.setOrder(1);

        return registrationBean;
    }
}