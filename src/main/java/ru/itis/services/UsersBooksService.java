package ru.itis.services;

import ru.itis.models.Book;
import ru.itis.models.User;
import ru.itis.models.UsersBooks;

import java.util.List;

public interface UsersBooksService {
    List<UsersBooks> getUsersBooksByUser(User user);

    void setBookAsCurrentlyReading(User user, Book book);

    void setBookAsGoingToRead(User user, Book book);

    void setBookAsStoppedReading(User user, Book book);

    void setBookAsRead(User user, Book book);
}
