package com.mechat.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Crypt {

    private static final int saltRounds = 10;

    public static String encrypt(String data) {
        String salt = BCrypt.gensalt(saltRounds);
        String hashedData = BCrypt.hashpw(data, salt);
        return hashedData;
    }

    public static boolean decrypt(String data, String hashedData) {
        return BCrypt.checkpw(data, hashedData);
    }
}
