package com.mechat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

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
            String bannerFilePath = ResourceUtils.getFile("classpath:logo.txt").getAbsolutePath();
            BufferedReader reader = new BufferedReader(new FileReader(bannerFilePath));
            String line;

            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            out.println("Error while reading the banner file");
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
