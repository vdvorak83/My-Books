package ru.itis.services;

import ru.itis.models.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers();

    String createTempPassword(Integer id);

    void setUserStatusEnabled(Integer id);

    void setUserStatusDeleted(Integer id);

    void setUserStatusBanned(Integer id);
}
