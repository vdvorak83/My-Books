package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.models.UsersBooks;
import ru.itis.repositories.UsersBooksRepository;
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
}
