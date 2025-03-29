package com.mechat.utils;

public class Time {

    public static String getCurrentTime() {
        return java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static String getFormatTime(String hour, String minute) {
        StringBuilder time = new StringBuilder();
        if (hour.length() == 1) {
            time.append("0").append(hour);
        } else {
            time.append(hour);
        }
        time.append(":");
        if (minute.length() == 1) {
            time.append("0").append(minute);
        } else {
            time.append(minute);
        }
        return time.toString();
    }
}
