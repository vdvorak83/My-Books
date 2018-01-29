package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.services.AuthenticationService;
import ru.itis.services.BooksService;
import ru.itis.services.UsersBooksService;

@Controller
@RequestMapping("/user")
public class ProfileController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private UsersBooksService usersBooksService;

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("usersBooks", usersBooksService.getUsersBooksByUser(
                authenticationService.getUserByAuthentication(authentication)));

        return "profile";
    }
}
