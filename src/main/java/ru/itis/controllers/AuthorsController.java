package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.services.AuthenticationService;
import ru.itis.services.AuthorsService;

import java.util.Optional;

@Controller
public class AuthorsController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthorsService authorsService;

    @GetMapping("/authors")
    public String getBooksPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                               @RequestParam Optional<String> error) {
        if (authentication != null) {
            model.addAttribute(authenticationService.getUserByAuthentication(authentication));
            model.addAttribute("authorsList", authorsService.getAllAuthors());

            return "authors";
        }

        model.addAttribute("error", error);

        return "redirect:/login";
    }
}
