package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.services.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login(@ModelAttribute("model") ModelMap model, Authentication authentication,
                        @RequestParam Optional<String> error) {
        if (authentication != null) {
            return "redirect:/";
        }

        model.addAttribute("error", error);

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Authentication authentication) {
        if (authentication != null) {
            request.getSession().invalidate();
        }

        return "redirect:/login";
    }

    @GetMapping("/")
    public String root(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/user/profile";
        }

        return "redirect:/login";
    }

    @GetMapping("/user/profile")
    public String getProfilePage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));

        return "profile";
    }
}
