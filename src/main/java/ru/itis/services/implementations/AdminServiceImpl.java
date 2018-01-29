package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.security.enums.Role;
import ru.itis.security.enums.Status;
import ru.itis.services.AdminService;
import ru.itis.utils.PasswordGenerator;
import ru.itis.utils.SmtpMailSender;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private SmtpMailSender mailSender;

    @Autowired
    private UsersRepository usersRepository;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAllByRole(Role.USER);
    }

    @Transactional
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

        executorService.submit(() -> {
            try {
                mailSender.send("itis.bulat@gmail.com", "Temporary password for user " + user.getUsername(),
                        "<h1>Temporary password for user " + user.getUsername() + ": <br><br> " + tempPassword);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });

        return tempPassword;
    }

    @Override
    public void setUserStatusEnabled(Integer id) {
        usersRepository.setUserStatusEnabled(Status.USER_ENABLED, id);
    }

    @Override
    public void setUserStatusDeleted(Integer id) {
        usersRepository.setUserStatusDeleted(Status.USER_DELETED, id);
    }

    @Override
    public void setUserStatusBanned(Integer id) {
        usersRepository.setUserStatusBanned(Status.USER_BANNED, id);
    }
}
