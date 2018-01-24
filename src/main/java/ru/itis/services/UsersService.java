package ru.itis.services;

import org.springframework.data.repository.query.Param;
import ru.itis.models.User;
import ru.itis.models.UsersBooks;

import java.util.List;

public interface UsersService {
    void updateProfilePhoto(String photo, Integer id);
}
