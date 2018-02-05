package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itis.models.Author;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    List<Author> findAll();

    Author findAuthorById(Integer id);
}
