package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.forms.UserRegistrationForm;
import ru.itis.services.RegistrationService;
import ru.itis.validators.UserRegistrationFormValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    private UserRegistrationFormValidator userRegistrationFormValidator;

    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(userRegistrationFormValidator);
    }

    @GetMapping(value = "/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(@Valid @ModelAttribute ("userForm") UserRegistrationForm userRegistrationForm,
                         BindingResult errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());

            return "redirect:/signup";
        }

        registrationService.register(userRegistrationForm);

        return "success";
    }
}
