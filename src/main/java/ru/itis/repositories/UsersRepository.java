package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Book;
import ru.itis.models.User;
import ru.itis.security.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    List<User> findAllByRole(Role role);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(Integer id);
}
