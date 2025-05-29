package com.seu.javaFriday.Controller;

import com.seu.javaFriday.Service.ApiService;
import com.seu.javaFriday.Service.TokenService;
import com.seu.javaFriday.dto.LoginRequest;
import com.seu.javaFriday.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest,
                        HttpServletResponse response,
                        Model model) {

        try {
            UserDto user = apiService.login(loginRequest);

            if (user != null) {
                tokenService.storeToken(response, user.getToken());
                System.out.println("Logged in: " + user.getRole());
                if ("ADMIN".equals(user.getRole())) {
                    return "redirect:/admin/dashboard";
                } else {
                    return "redirect:/user/dashboard";
                }
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "auth/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Login failed. Please try again.");
            return "auth/login";
        }
    }


    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto,
                           RedirectAttributes redirectAttributes) {
        System.out.println("register ======");
        System.out.println(userDto);
        UserDto registeredUser = apiService.register(userDto);
        System.out.println(registeredUser);

        if (registeredUser != null) {
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/auth/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Registration failed! Username or email already exists.");
            return "redirect:/auth/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        tokenService.clearToken(response);
        return "redirect:/auth/login";
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto userDto = apiService.getUserByUsername(username);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto);
    }
}

