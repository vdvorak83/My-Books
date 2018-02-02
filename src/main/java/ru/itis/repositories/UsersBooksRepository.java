package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Book;
import ru.itis.models.User;
import ru.itis.models.UsersBooks;

import java.util.List;

public interface UsersBooksRepository extends JpaRepository<UsersBooks, Integer> {
    List<UsersBooks> findUsersBooksByUser(User user);

    @Transactional
    @Modifying
    @Query("UPDATE UsersBooks u SET u.bookRatingByUser = :rating WHERE u.user = :user AND u.book = :book")
    void rateBook(@Param("user") User user, @Param("book") Book book, @Param("rating") Integer rating);

    UsersBooks queryByUserAndBook(User user, Book book);
}
