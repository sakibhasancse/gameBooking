package com.seu.javaFriday;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(username, password);

        LoginHandler handler = new LoginHandler();
        handler.setUser(user);

        Response response = handler.handleLogin();
        if(response.getCode().equals("0")) {
            request.getSession().setAttribute("login", true);
            return "redirect:/user/students";
        }
        request.setAttribute("error", "Invalid login");
        return "login";
    }

    @GetMapping("/student-create")
    public String getStudentCreateFrom() {
        return "student-create";
    }

    @PostMapping("/student-create")
    public String handleStudentCreate(HttpServletRequest request) {
        StudentCreateRequest student = new StudentCreateRequest();
        student.setUsername(request.getParameter("username"));
        student.setName(request.getParameter("name"));
        student.setEmail(request.getParameter("email"));
        student.setPhone(request.getParameter("phone"));
        student.setGender(request.getParameter("gender"));
        student.setAddress(request.getParameter("address"));
        student.setDepartment(request.getParameter("department"));
        student.setRegistrationNumber(request.getParameter("registrationNumber"));
//        student.setDateOfBirth(request.getParameter("dateOfBirth"));

        StudentHandler handler = new StudentHandler();
        Response response = handler.createStudent(student);

        if (response != null && "0".equals(response.getCode())) {
            return "redirect:/user/students";
        }

        request.setAttribute("error", "Failed to create student.");
        return "student-create";
    }


    @GetMapping("/students")
    public String getAllStudents(HttpSession session, HttpServletRequest request) {
        System.out.println("getAllStudents");
        Boolean isLogin = (Boolean) session.getAttribute("login");
        if (isLogin == null || !isLogin) {
            return "redirect:/user/login";
        }

        StudentHandler handler = new StudentHandler();
        Response response = handler.getAllStudents();


            request.setAttribute("students", response.getData());
            return "students";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
