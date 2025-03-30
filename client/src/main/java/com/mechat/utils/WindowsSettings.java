package com.mechat.utils;

public class WindowsSettings {

    public static String getWindowsTheme() {
        return "dark";
        // try {
        // ProcessBuilder processBuilder = new ProcessBuilder(
        // "reg",
        // "query",
        // "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize",
        // "/v",
        // "AppsUseLightTheme");
        // Process process = processBuilder.start();
        // process.waitFor();
        // String result = new String(process.getInputStream().readAllBytes());

        // // if AppsUseLightTheme is 0 (dark mode)
        // // if AppsUseLightTheme is 1 (light mode)
        // if (result.contains("0x0")) {
        // return "dark";
        // } else {
        // return "light";
        // }
        // } catch (InterruptedException | IOException e) {
        // System.err.println(e.getMessage());
        // return "light";
        // }
    }
}
