package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.models.Book;
import ru.itis.models.UsersBooks;
import ru.itis.services.AdminService;
import ru.itis.services.AuthenticationService;
import ru.itis.services.BooksService;
import ru.itis.services.UsersBooksService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BooksService booksService;

    @GetMapping
    public String redirectToMainAdminPage() {
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String getUsersAdminPage(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));

        return "admin";
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
}
