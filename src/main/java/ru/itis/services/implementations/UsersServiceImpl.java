package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.UsersService;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void updateProfilePhoto(String photo, Integer id) {
        usersRepository.updateUserProfilePhoto(photo, id);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void changeUserPassword(String newPassword, Integer id) {
        usersRepository.updateUserPassword(newPassword, id);
    }

    @Override
    public void changeUsername(String newUsername, Integer id) {
        usersRepository.changeUsername(newUsername, id);
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return usersRepository.findUserById(id);
    }
}
