package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Author;
import ru.itis.repositories.AuthorsRepository;
import ru.itis.services.AuthorsService;

import java.util.List;

@Service
public class AuthorsServiceImpl implements AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }

    @Override
    public Author getAuthorById(Integer id) {
        return authorsRepository.findAuthorById(id);
    }
}
