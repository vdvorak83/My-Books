package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.AuthenticationService;
import ru.itis.services.UsersBooksService;
import ru.itis.services.UsersService;

@Controller
@RequestMapping("/user")
public class ProfileController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersBooksService usersBooksService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication, @ModelAttribute("model") ModelMap model) {
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("usersBooks", usersBooksService.getUsersBooksByUser(
                authenticationService.getUserByAuthentication(authentication)));

        return "profile";
    }

    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam("new_password") String newPassword, Authentication authentication) {
        usersService.changeUserPassword(usersService.encodePassword(newPassword),
                authenticationService.getUserByAuthentication(authentication).getId());

        return "redirect:/user/profile";
    }

    @PostMapping("/profile/change-username")
    public String changeUsername(@RequestParam("new_username") String newUsername, Authentication authentication) {
        usersService.changeUsername(newUsername, authenticationService.getUserByAuthentication(authentication).getId());

        return "redirect:/user/profile";
    }
}
