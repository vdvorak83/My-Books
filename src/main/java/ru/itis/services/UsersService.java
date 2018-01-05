package ru.itis.services;

import ru.itis.models.Book;
import ru.itis.models.User;

import java.util.List;

public interface UsersService {
    void setBookStatusAsCurrentlyReading();
    void setBookStatusAsGoingToRead();
    void setBookStatusAsStoppedReading();
    void setBookStatusAsRead();

    Book getCurrentlyReadingBook();
    List<Book> getGoingToReadBooks();
    List<Book> getStoppedReadingBooks();
    List<Book> getReadBooks();

    Integer rateTheBook();
}
