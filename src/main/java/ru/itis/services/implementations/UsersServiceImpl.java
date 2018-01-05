package ru.itis.services.implementations;

import org.springframework.stereotype.Service;
import ru.itis.models.Book;
import ru.itis.services.UsersService;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
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
