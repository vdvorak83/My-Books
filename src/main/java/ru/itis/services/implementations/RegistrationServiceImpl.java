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
import ru.itis.utils.SmtpMailSender;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SmtpMailSender mailSender;

    private final String DEFAULT_PROFILE_PHOTO = "65345955-f3f5-4f33-9cdd-6de087127b1f.png";

    private ExecutorService executorService = Executors.newCachedThreadPool();

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
                .uuid(UUID.randomUUID().toString())
                .build();

        usersRepository.save(newUser);

        executorService.submit(() -> {
            try {
                mailSender.send(newUser.getEmail(), "Please confirm your registration on MyBooks",
                        "http://localhost:8080/confirm/" + newUser.getUuid());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });

        return newUser;
    }

    @Override
    public void confirm(String uuid) {
        Optional<User> optionalUser = usersRepository.findUserByUuid(uuid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setStatus(Status.USER_ENABLED);
            user.setUuid(null);

            usersRepository.save(user);
        }
    }
}
