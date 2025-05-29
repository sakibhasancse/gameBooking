package com.seu.javaFriday.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
@Component
public class TokenService {

    private static final String TOKEN_COOKIE_NAME = "jwt_token";

    @Autowired
    private JwtService jwtService;

    public void storeToken(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // 1 day
        response.addCookie(cookie);
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String jwt = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
        } else {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }
        }
        System.out.println("JWT: =====> " + jwt);
        return jwt;
    }

    public void clearToken(HttpServletResponse response) {
        Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, null);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire the cookie
        response.addCookie(cookie);
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        String token = getToken(request);
        return token != null && jwtService.decodePayload(token) != null;
    }

    public boolean isUser(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) return false;

        List<String> roles = jwtService.extractRoles(token);
        return roles != null && roles.contains("ROLE_USER");
    }

    public boolean isAdmin(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) return false;

        List<String> roles = jwtService.extractRoles(token);
        return roles != null && roles.contains("ROLE_ADMIN");
    }

    public String getUsername(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) return null;

        return jwtService.extractUsername(token);
    }

    public String extractClaim(HttpServletRequest request, String claimKey) {
        String token = getToken(request);
        if (token == null) return null;

        Map<String, Object> payload = jwtService.decodePayload(token);
        if (payload != null && payload.containsKey(claimKey)) {
            return payload.get(claimKey).toString();
        }
        return null;
    }
}