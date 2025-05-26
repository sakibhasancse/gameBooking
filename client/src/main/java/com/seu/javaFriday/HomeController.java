package com.seu.javaFriday;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
        @GetMapping("/")
        public String getHome(HttpSession session) {
            Boolean isLogin = (Boolean) session.getAttribute("login");
            if (isLogin != null && isLogin) {
                return "home";
            }
            return "redirect:/user/login";
        }
}
