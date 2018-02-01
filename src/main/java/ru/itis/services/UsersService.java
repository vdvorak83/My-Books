package ru.itis.services;

public interface UsersService {
    void updateProfilePhoto(String photo, Integer id);

    String encodePassword(String password);

    void changeUserPassword(String newPassword, Integer id);

    void changeUsername(String newUsername, Integer id);
}
