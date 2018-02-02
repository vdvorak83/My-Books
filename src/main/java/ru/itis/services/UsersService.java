package ru.itis.services;

import ru.itis.models.User;

import java.util.Optional;

public interface UsersService {
    void updateProfilePhoto(String photo, Integer id);

    String encodePassword(String password);

    void changeUserPassword(String newPassword, Integer id);

    void changeUsername(String newUsername, Integer id);

    Optional<User> findUserById(Integer id);
}
