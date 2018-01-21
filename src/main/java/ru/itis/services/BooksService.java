package ru.itis.services;

import ru.itis.models.Book;

import java.util.List;

public interface BooksService {
    Book getBookById(Integer id);
    List<Book> getAllBooks();
    //Book getBookByAuthorId(Integer id);
}
