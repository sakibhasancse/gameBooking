package com.seu.javaFriday.Controller;

import com.seu.javaFriday.Service.ApiService;
import com.seu.javaFriday.dto.LoginRequest;
import com.seu.javaFriday.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("=== LOGIN ENDPOINT HIT ===");
        System.out.println("Returning view: auth/login"); return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        UserDto user = apiService.login(loginRequest);

        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("isAdmin", "ADMIN".equals(user.getRole()));

            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/games";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto,
                           RedirectAttributes redirectAttributes) {
        UserDto registeredUser = apiService.register(userDto);

        if (registeredUser != null) {
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/auth/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Registration failed! Username or email already exists.");
            return "redirect:/auth/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}

