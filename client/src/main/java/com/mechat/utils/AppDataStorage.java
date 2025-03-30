package com.mechat.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppDataStorage {

    private static final String appName = "/com.mechat/";
    private static final String FILE_PATH = getAppDataPath();

    public static void saveData(String fileName, String data) {
        createDirectoryIfNotExists();

        try (FileWriter writer = new FileWriter(FILE_PATH + fileName)) {
            writer.write(data);
        } catch (IOException e) {
        }
    }

    public static String loadData(String fileName) {
        createDirectoryIfNotExists();

        StringBuilder data = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            return null;
        }

        return data.toString().trim();
    }

    public static void deleteData(String fileName) {
        createDirectoryIfNotExists();

        File file = new File(FILE_PATH + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void createDirectoryIfNotExists() {
        File directory = new File(FILE_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static String getAppDataPath() {
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
