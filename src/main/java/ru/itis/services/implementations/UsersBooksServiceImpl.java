package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Book;
import ru.itis.models.User;
import ru.itis.models.UsersBooks;
import ru.itis.repositories.UsersBooksRepository;
import ru.itis.security.enums.BookStatus;
import ru.itis.services.UsersBooksService;

import java.util.List;

@Service
public class UsersBooksServiceImpl implements UsersBooksService {
    @Autowired
    private UsersBooksRepository usersBooksRepository;

    @Override
    public List<UsersBooks> getUsersBooksByUser(User user) {
        return usersBooksRepository.findUsersBooksByUser(user);
    }

    @Override
    public void setBookAsCurrentlyReading(User user, Book book) {
        UsersBooks usersBooks = new UsersBooks();

        usersBooks.setUser(user);
        usersBooks.setBook(book);
        usersBooks.setBookStatus(BookStatus.READING);

        usersBooksRepository.save(usersBooks);
    }

    @Override
    public void setBookAsGoingToRead(User user, Book book) {
        UsersBooks usersBooks = new UsersBooks();

        usersBooks.setUser(user);
        usersBooks.setBook(book);
        usersBooks.setBookStatus(BookStatus.TO_READ);

        usersBooksRepository.save(usersBooks);
    }

    @Override
    public void setBookAsStoppedReading(User user, Book book) {
        UsersBooks usersBooks = new UsersBooks();

        usersBooks.setUser(user);
        usersBooks.setBook(book);
        usersBooks.setBookStatus(BookStatus.STOPPED_READING);

        usersBooksRepository.save(usersBooks);
    }

    @Override
    public void setBookAsRead(User user, Book book) {
        UsersBooks usersBooks = new UsersBooks();

        usersBooks.setUser(user);
        usersBooks.setBook(book);
        usersBooks.setBookStatus(BookStatus.READ);

        usersBooksRepository.save(usersBooks);
    }

    @Override
    public void rateBook(User user, Book book, Integer rating) {
        usersBooksRepository.rateBook(user, book, rating);
    }

    @Override
    public UsersBooks queryByUserAndBook(User user, Book book) {
        return usersBooksRepository.queryByUserAndBook(user, book);
    }
}
