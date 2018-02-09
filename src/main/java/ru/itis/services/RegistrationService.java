package ru.itis.services;

import ru.itis.forms.UserRegistrationForm;
import ru.itis.models.User;

public interface RegistrationService {
    User register(UserRegistrationForm userRegistrationForm);

    void confirm(String uuid);
}
