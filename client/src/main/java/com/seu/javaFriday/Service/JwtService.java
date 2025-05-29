package com.seu.javaFriday.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

//    @Value("${jwt.secret}")
//    private String jwtSecret;
    public String extractUsername(String token) {
        return (String) decodePayload(token).get("sub");
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        return (List<String>) decodePayload(token).get("roles");
    }

    public Map<String, Object> decodePayload(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid JWT token format");
            }

            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            return objectMapper.readValue(payloadJson, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode JWT token", e);
        }
    }
//    public String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    public List<String> extractRoles(String token) {
//        Claims claims = extractAllClaims(token);
//        return claims.get("roles", List.class);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser()
////                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
}
