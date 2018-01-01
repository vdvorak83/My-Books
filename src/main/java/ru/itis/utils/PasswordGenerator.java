package ru.itis.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PasswordGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";

    public String generate() {
        return generate((LOWER + UPPER + DIGITS + PUNCTUATION).toCharArray(), 10);
    }

    private String generate(char[] validChars, int length) {
        char[] password = new char[length];
        Random random = new Random(System.nanoTime());

        for (int i = 0; i < length; i++) {
            password[i] = validChars[random.nextInt(validChars.length)];
        }

        return new String(password);
    }
}
