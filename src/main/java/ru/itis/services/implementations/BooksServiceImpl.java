package ru.itis.services.implementations;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import ru.itis.models.Book;
import ru.itis.services.BooksService;

import java.util.List;

public class BooksServiceImpl implements BooksService {

    @Override
    public List<Book> getAllBooks() {
        return null;
    }
}
