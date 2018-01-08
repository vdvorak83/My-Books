package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.services.AuthenticationService;
import ru.itis.services.BooksService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BooksService booksService;

    @GetMapping
    public String getBooksPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                               @RequestParam Optional<String> error) {
        if (authentication != null) {
            model.addAttribute(authenticationService.getUserByAuthentication(authentication));
            model.addAttribute("booksList", booksService.getAllBooks());

            return "books";
        }

        model.addAttribute("error", error);

        return "redirect:/login";
    }

    @GetMapping("/{book-id}")
    public String getAuthorPersonalPage(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                        @PathVariable("book-id") Integer id) {
        if (authentication != null) {
            model.addAttribute(authenticationService.getUserByAuthentication(authentication));

            return "book-page";
        }

        return "redirect:/login";
    }
}