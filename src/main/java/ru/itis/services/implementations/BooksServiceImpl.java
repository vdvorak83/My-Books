package ru.itis.services.implementations;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Book;
import ru.itis.repositories.BooksRepository;
import ru.itis.services.BooksService;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    BooksRepository booksRepository;

    @Override
    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }
}
