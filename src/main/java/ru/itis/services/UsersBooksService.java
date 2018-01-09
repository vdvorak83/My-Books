package ru.itis.services;

import ru.itis.models.User;
import ru.itis.models.UsersBooks;

import java.util.List;

public interface UsersBooksService {
    List<UsersBooks> getUsersBooksByUser(User user);
}
