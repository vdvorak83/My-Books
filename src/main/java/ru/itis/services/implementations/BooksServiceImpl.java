package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.models.Author;
import ru.itis.models.Book;
import ru.itis.repositories.BooksRepository;
import ru.itis.services.BooksService;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksRepository booksRepository;

    @Override
    public Book getBookById(Integer id) {
        return booksRepository.findBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public List<Book> getBooksByBookAuthor(Author author) {
        return booksRepository.findBooksByBookAuthor(author);
    }
}
