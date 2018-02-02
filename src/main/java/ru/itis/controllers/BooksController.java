package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.services.AuthenticationService;
import ru.itis.services.BooksService;
import ru.itis.services.UsersBooksService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private UsersBooksService usersBooksService;

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
            model.addAttribute("book", booksService.getBookById(id));
            model.addAttribute("usersBooks", usersBooksService.queryByUserAndBook(
                    authenticationService.getUserByAuthentication(authentication),
                    booksService.getBookById(id)));

            return "book-page";
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/{book-id}", params = "status")
    public String setBookStatus(@ModelAttribute("model") ModelMap model, @RequestParam(value = "status") String status,
                                Authentication authentication, @PathVariable("book-id") Integer id) {
        switch (status) {
            case "Currently Reading":
                usersBooksService.setBookAsCurrentlyReading(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
                break;
            case "Want to Read":
                usersBooksService.setBookAsGoingToRead(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
                break;
            case "Stopped Reading":
                usersBooksService.setBookAsStoppedReading(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
                break;
            case "Finished Reading":
                usersBooksService.setBookAsRead(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
                break;
        }

        return "redirect:/books/" + id;
    }

    @PostMapping("/{book-id}/rate")
    public String rateBook(@PathVariable("book-id") Integer id, @RequestParam("book-rating") Integer rating,
                           Authentication authentication) {
        usersBooksService.rateBook(authenticationService.getUserByAuthentication(authentication),
                booksService.getBookById(id), rating);

        return "redirect:/books/" + id;
    }

    /*@PostMapping("/{book-id}")
    public String setBookAsCurrentlyReading(@RequestParam("reading") String reading,
                                            @PathVariable("book-id") Integer id, Authentication authentication) {
        usersBooksService.setBookAsCurrentlyReading(
                authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));

        return "redirect:/books/" + id;
    }*/

/*
    @RequestMapping(value = "/{book-id}", params = "to-read", method = RequestMethod.POST)
    public void setBookAsGoingToRead(@PathVariable("book-id") Integer id, Authentication authentication) {
        usersBooksService.setBookAsGoingToRead(
                authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
    }

    @RequestMapping(value = "/{book-id}", params = "reading")
    public void setBookAsCurrentlyReading(@PathVariable("book-id") Integer id, Authentication authentication) {
        usersBooksService.setBookAsCurrentlyReading(
                authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
    }*/

    /*@PostMapping("/{book-id}")
    public String setBookStatus(@RequestParam("status") String status,
                                @PathVariable("book-id") Integer id, Authentication authentication) {
        System.out.println(status);
        switch (status) {
            case "Currently Reading":
                usersBooksService.setBookAsCurrentlyReading(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
            case "Want to Read":
                usersBooksService.setBookAsGoingToRead(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
            case "Stopped Reading":
                usersBooksService.setBookAsStoppedReading(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
            case "Finished Reading":
                usersBooksService.setBookAsRead(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
        }

        return "redirect:/books/" + id;
    }*/

    /*@PostMapping("/{book-id}")
    public String setBookAsGoingToRead(@RequestParam("to-read") String toRead,
                                       @PathVariable("book-id") Integer id, Authentication authentication) {
        usersBooksService.setBookAsGoingToRead(
                authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));

        return "redirect:/books/" + id;
    }*/

    /*@PostMapping("/{book-id}")
    public void postBookStatus(@RequestParam(value = "READING") String reading,
                               @RequestParam(value = "TO_READ") String toRead,
                               @RequestParam(value = "STOPPED_READING") String stoppedReading,
                               @RequestParam(value = "READ") String read,
                               @PathVariable("book-id") Integer id,
                               Authentication authentication) {
        switch (bookStatus) {
            case "READING":
                usersBooksService.setBookAsCurrentlyReading(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
            case "TO_READ":
                usersBooksService.setBookAsGoingToRead(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
            case "STOPPED_READING":
                usersBooksService.setBookAsStoppedReading(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
            case "READ":
                usersBooksService.setBookAsRead(
                        authenticationService.getUserByAuthentication(authentication), booksService.getBookById(id));
        }
    }*/
}