package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Author;
import ru.itis.models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    Book findBookById(Integer id);
    List<Book> findAll();
    List<Book> findBookByBookAuthor(Author author);
    List<Book> findBookByTitle(String title);
    List<Book> findUserBooksById(Integer id);
}
