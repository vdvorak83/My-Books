package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.services.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthorsService authorsService;

    @Autowired
    private BooksService booksService;

    @GetMapping("/users")
    public String getUsersAdminPage(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("adminService", adminService);
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));

        return "admin-users";
    }

    @GetMapping("/password/temp/{user-id}")
    public String getNewPasswordOfUser(@ModelAttribute("model") ModelMap model, Authentication authentication,
                                       @PathVariable("user-id") Integer id) {
        model.addAttribute("user", authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("tempPassword", adminService.createTempPassword(id));

        return "temp-password";
    }

    @GetMapping("/books")
    public String getBooksAdminPage(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        model.addAttribute("books", booksService.getAllBooks());
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));

        return "admin-books";
    }

    @GetMapping("/authors")
    public String getAuthorsAdminPage(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        model.addAttribute("authors", authorsService.getAllAuthors());
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("booksService", booksService);

        return "admin-authors";
    }

    @PostMapping("/users/delete/{user-id}")
    public String deleteUser(@PathVariable("user-id") Integer id) {
        adminService.setUserStatusDeleted(id);

        return "redirect:/admin/users";
    }

    @PostMapping("users/enable/{user-id}")
    public String enableUser(@PathVariable("user-id") Integer id) {
        adminService.setUserStatusEnabled(id);

        return "redirect:/admin/users";
    }

    @PostMapping("users/ban/{user-id}")
    public String banUser(@PathVariable("user-id") Integer id) {
        adminService.setUserStatusBanned(id);

        return "redirect:/admin/users";
    }
}
