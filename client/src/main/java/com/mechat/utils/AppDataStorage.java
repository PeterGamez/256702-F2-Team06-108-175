package com.mechat.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppDataStorage {

    private static final String FILE_PATH = getAppDataPath("/com.mechat/");

    public static void saveData(String fileName, String data) {
        createDirectoryIfNotExists();

        try (FileWriter writer = new FileWriter(FILE_PATH + fileName)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadData(String fileNam) {
        createDirectoryIfNotExists();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + fileNam))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private static void createDirectoryIfNotExists() {
        File directory = new File(FILE_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static String getAppDataPath(String appName) {
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");

        if (os.contains("win")) {
            return System.getenv("APPDATA") + "\\" + appName + "\\";
        } else if (os.contains("mac")) {
            return userHome + "/Library/Application Support/" + appName + "/";
        } else { // Linux and other Unix-based systems
            return userHome + "/.config/" + appName + "/";
        }
    }
}
