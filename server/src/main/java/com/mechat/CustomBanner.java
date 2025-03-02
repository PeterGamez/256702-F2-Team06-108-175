package com.mechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;

public class CustomBanner implements Banner {

    private PrintStream out;

    private String meChatVersion;
    private String javaVersion = System.getProperty("java.version");
    private String springFrameworkVersion = SpringVersion.getVersion();
    private String springBootVersion = SpringBootVersion.getVersion();
    private String hibernateVersion = org.hibernate.Version.getVersionString();
    private String mariadbVersion = org.mariadb.jdbc.util.VersionFactory.getInstance().getVersion();

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        this.out = out;
        meChatVersion = environment.getProperty("app.version", "UNKNOWN");

        printLogo();
        printCaption();
    }

    private void printLogo() {
        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream("logo.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));

            if (file == null) {
                out.println("Logo file not found.");
                return;
            }

            while (true) {
                String line = reader.readLine();

                if (line == null) {
                    break;
                }

                out.println(line);
            }
        } catch (Exception e) {
            out.println("Error while reading the banner file: " + e.getMessage());
        }
    }

    private void printCaption() {
        out.println("");
        print("Me Chat", meChatVersion);
        print("JVM", javaVersion);
        print("Spring Framework", springFrameworkVersion);
        print("Spring Boot", springBootVersion);
        print("Hibernate", hibernateVersion);
        print("MariaDB", mariadbVersion);
        out.println("");
    }

    private void print(String key, String value) {
        out.print(" ".repeat(8));
        out.print(key);
        out.print(":");
        out.print(" ".repeat(20 - key.length()));
        out.println(value);
    }
}
