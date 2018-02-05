package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itis.models.Author;
import ru.itis.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    Book findBookById(Integer id);

    List<Book> findAll();

    List<Book> findBooksByBookAuthor(Author author);
}
