package ru.itis.utils;

import java.util.UUID;

public class UuidGenerator {
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
