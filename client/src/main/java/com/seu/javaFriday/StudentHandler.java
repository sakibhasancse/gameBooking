package com.seu.javaFriday;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StudentHandler {
    public Response getAllStudents() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9999/user/students";

        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(url, Response.class);
        return responseEntity.getBody();
    }
    public Response createStudent(StudentCreateRequest student) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9999/user/student-create";

        HttpEntity<StudentCreateRequest> entity = new HttpEntity<>(student);
        ResponseEntity<Response> responseEntity =
                restTemplate.exchange(url, HttpMethod.POST, entity, Response.class);

        return responseEntity.getBody();
    }
}
