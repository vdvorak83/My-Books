package ru.itis.services;

import org.springframework.security.core.Authentication;
import ru.itis.models.User;

public interface AuthenticationService {
    User getUserByAuthentication(Authentication authentication);
}
