package com.mechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

public class CustomBanner implements Banner {

    private PrintStream out;

    private String version;
    private String javaVersion = System.getProperty("java.version");
    private String springBootVersion = org.springframework.boot.SpringBootVersion.getVersion();

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        this.out = out;
        version = environment.getProperty("app.version", "UNKNOWN");

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
        out.println("        Version:        " + version);
        out.println("        JVM:            " + javaVersion);
        out.println("        Spring Boot:    " + springBootVersion);
        out.println("");
    }
}
