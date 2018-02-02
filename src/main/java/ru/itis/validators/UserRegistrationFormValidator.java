package ru.itis.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itis.forms.UserRegistrationForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

@Component
public class UserRegistrationFormValidator implements Validator {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.getName().equals(UserRegistrationForm.class.getName());
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationForm userRegistrationForm = (UserRegistrationForm) target;

        Optional<User> existedUser = usersRepository.findOneByUsername(userRegistrationForm.getUsername());

        if (existedUser.isPresent()) {
            errors.reject("bad.login", "This username is already in use");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace
                (errors, "username", "empty.username", "Empty username");
        ValidationUtils.rejectIfEmptyOrWhitespace
                (errors, "password", "empty.password", "Empty password");
    }
}
