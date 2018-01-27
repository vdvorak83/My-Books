package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.User;
import ru.itis.models.UsersBooks;

import java.util.List;

public interface UsersBooksRepository extends JpaRepository<UsersBooks, Integer> {
    List<UsersBooks> findUsersBooksByUser(User user);
}
