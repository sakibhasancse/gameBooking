package com.seu.javaFriday;

import com.seu.javaFriday.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginHandler {
    private User user;

    private Response response;

    public Response handleLogin() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:9999/user/login";

        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);

        ResponseEntity<Response> responseEntity = restTemplate.exchange(url, HttpMethod.POST, userHttpEntity, Response.class);
        System.out.println("responseEntity --> " + responseEntity);
        response = responseEntity.getBody();

        return response;
    }
}
