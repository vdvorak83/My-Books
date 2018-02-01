package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.forms.UserRegistrationForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.security.enums.Role;
import ru.itis.security.enums.Status;
import ru.itis.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UsersRepository usersRepository;

    private final String DEFAULT_PROFILE_PHOTO = "65345955-f3f5-4f33-9cdd-6de087127b1f.png";

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User register(UserRegistrationForm userRegistrationForm) {
        User newUser = User.builder()
                .name(userRegistrationForm.getName())
                .username(userRegistrationForm.getUsername())
                .email(userRegistrationForm.getEmail())
                .phone(userRegistrationForm.getPhone())
                .hashPassword(passwordEncoder.encode(userRegistrationForm.getPassword()))
                .role(Role.USER)
                .photo(DEFAULT_PROFILE_PHOTO)
                .status(Status.USER_NOT_CONFIRMED)
                .build();

        usersRepository.save(newUser);

        return newUser;
    }
}
