package com.seu.javaFriday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StudentRepo studentRepo;

    public List<User> readAll() {
        return userRepo.findAll();
    }

    public Response handleLogin(LoginRequest request) {
        User user = userRepo.findByUsername(request.getUsername());

        Response response = new Response();

        if(user != null && user.getPassword().equals(request.getPassword())) {
            Student studentDetails = studentRepo.findByUser(user);

            if (studentDetails != null) {
                response.setData(studentDetails);
                response.setCode("0");
                response.setMsg("SUCCESS");
            } else {
                response.setCode("1");
                response.setMsg("Student not found");
            }
        } else {
            response.setCode("1");
            response.setMsg("Unauthorized access");
        }

        return response;
    }

    public Response handleRegister(LoginRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepo.save(user);
        Response response = new Response();
            response.setCode("0");
            response.setMsg("SUCCESS");

        return response;
    }
}
