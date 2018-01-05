package ru.itis.services;

import ru.itis.models.Book;

import java.util.List;

public interface BooksService {
    List<Book> getAllBooks();
    Book getBookByAuthorId(Integer id);
}
