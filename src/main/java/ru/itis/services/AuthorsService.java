package ru.itis.services;

import ru.itis.models.Author;

import java.util.List;

public interface AuthorsService {
    List<Author> getAllAuthors();
    Author getAuthorById(Integer id);
}
