package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();
    List<Book> findBookByTitle(String title);
    List<Book> findBookByAuthor(String author);
}
