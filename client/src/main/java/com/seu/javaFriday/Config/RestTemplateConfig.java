package com.seu.javaFriday.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        System.out.println("RestTemplate======");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new JwtAuthInterceptor());
        return restTemplate;
    }

    @Bean
    public CommandLineRunner runner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Beans:");
            for (String name : ctx.getBeanDefinitionNames()) {
                System.out.println(name);
            }
        };
    }


    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
    private static class JwtAuthInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(
                HttpRequest request,
                byte[] body,
                ClientHttpRequestExecution execution) throws IOException {
            System.out.println("JwtAuthInterceptor =====> " + LocalDateTime.now());
            String token = getCurrentJwtToken();
            if (token != null && !token.isEmpty()) {
                request.getHeaders().setBearerAuth(token);
                System.out.println("Added JWT token to request: " + request.getURI());
            } else {
                System.out.println("No JWT token found in request: " + request.getURI());
            }

            return execution.execute(request, body);
        }

        private String getCurrentJwtToken() {
            try {

                ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attrs != null) {
                    HttpServletRequest request = attrs.getRequest();

                    // First try Authorization header
                    String authHeader = request.getHeader("Authorization");
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        return authHeader.substring(7);
                    }

                    // Then try cookie
                    if (request.getCookies() != null) {
                        for (Cookie cookie : request.getCookies()) {
                            if ("jwt_token".equals(cookie.getName())) {
                                return cookie.getValue();
                            }
                        }
                    }
                }

                return null;
            } catch (Exception e) {
                System.out.println("Error getting JWT token: " + e.getMessage());
            }

            return null;
        }
    }
}