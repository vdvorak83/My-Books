package ru.itis.services;

import ru.itis.models.Author;
import ru.itis.models.Book;

import java.util.List;

public interface BooksService {
    Book getBookById(Integer id);

    List<Book> getAllBooks();

    List<Book> getBooksByBookAuthor(Author author);
}
