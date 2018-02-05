package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.models.Author;
import ru.itis.models.Book;
import ru.itis.repositories.AuthorsRepository;
import ru.itis.repositories.BooksRepository;
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
    private AuthorsRepository authorsRepository;

    @Autowired
    private BooksService booksService;

    @Autowired
    private BooksRepository booksRepository;

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

    @PostMapping("/users/enable/{user-id}")
    public String enableUser(@PathVariable("user-id") Integer id) {
        adminService.setUserStatusEnabled(id);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/ban/{user-id}")
    public String banUser(@PathVariable("user-id") Integer id) {
        adminService.setUserStatusBanned(id);

        return "redirect:/admin/users";
    }

    @GetMapping("/books/add")
    public String getAddBookForm(ModelMap model, Authentication authentication) {
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("authors", authorsService.getAllAuthors());
        model.addAttribute("book", new Book());

        return "edit/add-book";
    }

    @PostMapping("/books/add")
    public String postAddBookForm(@ModelAttribute("book") Book book) {
        booksRepository.save(book);

        return "redirect:/admin/books";
    }

    @GetMapping("/authors/add")
    public String getAddAuthorForm(ModelMap model, Authentication authentication) {
        model.addAttribute(authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("authors", authorsService.getAllAuthors());
        model.addAttribute("author", new Author());

        return "edit/add-author";
    }

    @PostMapping("/authors/add")
    public String postAddAuthorForm(@ModelAttribute("author") Author author) {
        authorsRepository.save(author);

        return "redirect:/admin/authors";
    }
}
