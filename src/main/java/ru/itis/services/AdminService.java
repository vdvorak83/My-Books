package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.models.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers();
    String createTempPassword(Integer id);
}
