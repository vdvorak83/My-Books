package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.security.enums.Role;
import ru.itis.services.AdminService;
import ru.itis.utils.PasswordGenerator;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAllByRole(Role.USER);
    }

    @Override
    public String createTempPassword(Integer id) {
        Optional<User> existedUser = usersRepository.findUserById(id);

        if (!existedUser.isPresent()) {
            throw new IllegalArgumentException(("User not found"));
        }

        User user = existedUser.get();
        String tempPassword = passwordGenerator.generate();

        user.setHashTempPassword(passwordEncoder.encode(tempPassword));
        usersRepository.save(user);

        return tempPassword;
    }
}
