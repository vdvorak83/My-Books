package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.services.AuthenticationService;
import ru.itis.services.AuthorsService;
import ru.itis.services.implementations.AuthorsServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthorsService authorsService;

    @GetMapping
    public String getAuthorsPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                 @RequestParam Optional<String> error) {
        if (authentication != null) {
            model.addAttribute(authenticationService.getUserByAuthentication(authentication));
            model.addAttribute("authorsList", authorsService.getAllAuthors());

            return "authors";
        }

        model.addAttribute("error", error);

        return "redirect:/login";
    }

    @GetMapping("/{author-id}")
    public String getAuthorPersonalPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                        @PathVariable("author-id") Integer id) {
        if (authentication != null) {
            model.addAttribute(authenticationService.getUserByAuthentication(authentication));

            return "author-page";
        }

        return "redirect:/login";
    }
}
