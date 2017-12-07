package ru.itis.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itis.forms.SmsSpamForm;
import ru.itis.models.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class SmsSender {
    //@Value("${sms.aero.user}")
    private String smsAeroLogin = "aymurzin1998@mail.ru";

    //@Value("${sms.aero.password}")
    private String smsAeroPassword = "f3fa9807fa2924317f54c4901d500a0d";

    //@Value("${sms.aero.from}")
    private String smsAeroFrom = "biznes";

    //@Value("${sms.aero.type}")
    private String smsAeroType= "7";

    //@Value("${sms.aero.url}")
    private String smsAeroUri = "https://gate.smsaero.ru/send/";

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private final RestTemplate restTemplate = new RestTemplate();


    public void sendSms(SmsSpamForm form, User user) throws ExecutionException, InterruptedException {
        String login = user.getUsername();
        String password = user.getHashPassword();
        String phone = form.getPhone();
        Future<Boolean> result = executorService.submit(() -> {
            String request = smsAeroUri + "?user="
                    + smsAeroLogin + "&password="
                    + smsAeroPassword + "&to="
                    + phone
                    + "&text=" + "Abacus. Ваш логин: "
                    + login + " Пароль: " + password
                    + "&from="
                    + smsAeroFrom + "&type="
                    + smsAeroType;
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);
            if (responseEntity.getBody().contains("accepted")) {
                return true;
            } else {
                throw new IllegalArgumentException("Проблемы с номером телефона");
            }
        });
    }
}
