package com.mechat.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Crypt {
    private static final int saltRounds = 10;

    public static String encrypt(String data) {
        try {
            String salt = BCrypt.gensalt(saltRounds);
            String hashedData = BCrypt.hashpw(data, salt);
            return hashedData;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static boolean decrypt(String data, String hashedData) {
        try {
            return BCrypt.checkpw(data, hashedData);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
