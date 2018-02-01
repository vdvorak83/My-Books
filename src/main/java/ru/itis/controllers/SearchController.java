package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.hibernate.search.AuthorSearch;
import ru.itis.hibernate.search.BookSearch;
import ru.itis.services.AuthenticationService;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthorSearch authorSearch;

    @Autowired
    private BookSearch bookSearch;

    @RequestMapping("/search")
    public String search(@ModelAttribute("model") ModelMap model,
                         @RequestParam(value = "query", required = false) String query, Authentication authentication) {
        List bookSearchResults = null, authorSearchResult = null;

        try {
            bookSearchResults = bookSearch.search(query);
            authorSearchResult = authorSearch.search(query);
        } catch (Exception ignored) {

        }

        model.addAttribute(authenticationService.getUserByAuthentication(authentication));
        model.addAttribute("authorSearchResult", authorSearchResult);
        model.addAttribute("bookSearchResults", bookSearchResults);

        return "search";
    }
}
