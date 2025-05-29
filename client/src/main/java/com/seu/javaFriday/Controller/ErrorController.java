package com.seu.javaFriday.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


    @Controller
    @RequestMapping("/")
    public class ErrorController {

        @GetMapping("404")
        public String Error404() {
            return "404";
        }
    }
