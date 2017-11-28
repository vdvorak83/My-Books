package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.services.AuthenticationService;

import java.util.Optional;

@Controller
public class BooksController {
    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/books")
    public String getBooksPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                               @RequestParam Optional<String> error) {
        if (authentication != null) {
            model.addAttribute(authenticationService.getUserByAuthentication(authentication));

            return "books";
        }

        model.addAttribute("error", error);

        return "redirect:/login";
    }


}
