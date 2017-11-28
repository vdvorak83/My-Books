package ru.itis.services.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.models.Book;
import ru.itis.services.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setBookStatusAsCurrentlyReading() {

    }

    @Override
    public void setBookStatusAsGoingToRead() {

    }

    @Override
    public void setBookStatusAsStoppedReading() {

    }

    @Override
    public void setBookStatusAsRead() {

    }

    @Override
    public Book getCurrentlyReadingBook() {
        return null;
    }

    @Override
    public List<Book> getGoingToReadBooks() {
        return null;
    }

    @Override
    public List<Book> getStoppedReadingBooks() {
        return null;
    }

    @Override
    public List<Book> getReadBooks() {
        return null;
    }

    @Override
    public Integer rateTheBook() {
        return null;
    }
}
