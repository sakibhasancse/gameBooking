package com.seu.javaFriday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public Response handleRegister(@RequestBody LoginRequest request) {
        Response response = userService.handleRegister(request);
        return response;
    }

    @PostMapping("/login")
    public Response handleLogin(@RequestBody LoginRequest request) {
        Response response = userService.handleLogin(request);
        return response;
    }

    @GetMapping("/course-list")
    public ResponseEntity<Response> CourseList() {
        Response response = new Response();
        List<Course> courseList = List.of(
                new Course("Java", "CSE444", 1),
                new Course("Spring Boot", "CSE555", 2)
        );

        response.setCode("OK");
        response.setMsg("List of users");
        response.setCourses(courseList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/student-create")
    public ResponseEntity<Response> createStudent(@RequestBody StudentCreateRequest request) {
        Response response = studentService.createStudent(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students")
    public ResponseEntity<Response> getAllStudents() {
        Response response = studentService.getAllStudents();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{registrationNumber}")
    public ResponseEntity<Response> getStudent(@PathVariable String registrationNumber) {
        Response response = studentService.getStudentDetails(registrationNumber);
        return ResponseEntity.ok(response);
    }
}