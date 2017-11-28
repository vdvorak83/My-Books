package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.forms.UserRegistrationForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.security.enums.Role;
import ru.itis.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(UserRegistrationForm userRegistrationForm) {
        User newUser = User.builder()
                .name(userRegistrationForm.getName())
                .username(userRegistrationForm.getUsername())
                .hashPassword(passwordEncoder.encode(userRegistrationForm.getPassword()))
                .role(Role.USER)
                .build();

        usersRepository.save(newUser);
    }
}
