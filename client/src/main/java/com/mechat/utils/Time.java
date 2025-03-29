package com.mechat.utils;

import java.time.LocalDate;

public class Time {

    public static String getCurrentTime() {
        return java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static String getFormatTime(String dateTime) {
        String[] dateTimeParts = dateTime.split("T");
        String date = dateTimeParts[0];

        LocalDate parsedDate = LocalDate.parse(date);
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];

        String time = dateTimeParts[1].substring(0, 5);
        String[] timeParts = time.split(":");

        if (parsedDate.equals(today)) {
            return getFormatTime(timeParts[0], timeParts[1]);
        } else if (parsedDate.equals(yesterday)) {
            return "Yesterday " + getFormatTime(timeParts[0], timeParts[1]);
        } else {
            return getFormatDate(year, month, day) + " " + getFormatTime(timeParts[0], timeParts[1]);
        }
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

    public static String getFormatDate(String year, String month, String day) {
        StringBuilder date = new StringBuilder();

        if (day.length() == 1) {
            date.append("0").append(day);
        } else {
            date.append(day);
        }
        date.append("/");
        if (month.length() == 1) {
            date.append("0").append(month);
        } else {
            date.append(month);
        }
        date.append("/").append(year.substring(2, 4));

        return date.toString();
    }
}
