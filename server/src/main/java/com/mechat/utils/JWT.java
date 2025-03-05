package com.mechat.utils;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

public class JWT {

    private static final String SECRET_KEY = "your-very-secret-key-your-very-secret-key";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private static Header header() {
        Header header = Jwts.header()
                .add("alg", "HS256")
                .add("typ", "JWT")
                .build();

        return header;
    }

    public static String generateToken(Map<String, Object> payload) {
        payload.forEach((k, v) -> {
            if (v == null) {
                payload.put(k, "");
            }
        });

        return Jwts.builder()
                .header()
                .add(header())
                .and()
                .claims(payload)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Object> getPayload(String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        } catch (JwtException e) {
           e.printStackTrace();
           return null;
        }

    }
}
