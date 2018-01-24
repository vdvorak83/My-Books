package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Book;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.UsersService;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void updateProfilePhoto(String photo, Integer id) {
        usersRepository.updateProfilePhoto(photo, id);
    }
}
