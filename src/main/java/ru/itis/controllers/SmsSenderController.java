package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.forms.SmsSpamForm;
import ru.itis.services.AuthenticationService;
import ru.itis.utils.SmsSender;

import java.util.concurrent.ExecutionException;

@Controller
public class SmsSenderController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(value = "/sendsms")
    public String getSmsPage(@ModelAttribute("model") ModelMap model, Authentication authentication) {
        if (authentication != null) {
            return "sendsms";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/sendsms")
    public String sensSms(SmsSpamForm form, Authentication authentication) throws ExecutionException, InterruptedException {
        SmsSender sender = new SmsSender();
        sender.sendSms(form, authenticationService.getUserByAuthentication(authentication));

        return "redirect:/";
    }
}
