package ru.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BooksController {
    @GetMapping("/books")
    public String getBooksPage(@ModelAttribute ("model") ModelMap model, Authentication authentication) {
        if (authentication != null) {
            return "/books";
        }

        return "redirect:/login";
    }
}
