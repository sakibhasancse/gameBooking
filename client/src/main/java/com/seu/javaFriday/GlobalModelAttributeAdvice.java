package com.seu.javaFriday;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributeAdvice {

    @ModelAttribute
    public void addUserAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current User====>" + auth.getName());
        System.out.println("Current getAuthorities====>" + auth.getAuthorities());
        System.out.println("GlobalModelAttributeAdvice Authentication: " + auth);
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("username", auth.getName());
            model.addAttribute("isAdmin", auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else {
            model.addAttribute("isLoggedIn", false);
        }
    }
}
