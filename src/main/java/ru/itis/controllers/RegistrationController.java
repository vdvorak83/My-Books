package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.forms.UserRegistrationForm;
import ru.itis.services.RegistrationService;
import ru.itis.validators.UserRegistrationFormValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRegistrationFormValidator userRegistrationFormValidator;

    @InitBinder("userForm")
    public void initUserFormValidator(WebDataBinder binder) {
        binder.addValidators(userRegistrationFormValidator);
    }

    @GetMapping(value = "/register")
    public String signUp(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }

        return "register";
    }

    @PostMapping(value = "/register")
    public String signUp(@Valid @ModelAttribute("userForm") UserRegistrationForm userRegistrationForm,
                         BindingResult errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());

            return "redirect:/register";
        }

        registrationService.register(userRegistrationForm);

        return "success";
    }

    @GetMapping("/confirm/{uuid}")
    public String submitEmail(@PathVariable("uuid") String uuid) {
        registrationService.confirm(uuid);

        return "login";
    }
}
