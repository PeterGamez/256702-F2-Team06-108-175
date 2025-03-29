package com.mechat.utils;

import java.time.LocalDate;
import java.time.Year;

public class YearConverter {

    private static final int THAI_BUDDHIST_OFFSET = 543;

    public static LocalDate convertIso(LocalDate time) {
        if (time == null) {
            return null;
        }
        if (time.getYear() > Year.now().getValue() + THAI_BUDDHIST_OFFSET - 100) {
            return time.minusYears(THAI_BUDDHIST_OFFSET);
        }
        return time;
    }

    public static LocalDate convertToThaiBuddhist(LocalDate time) {
        if (time == null) {
            return null;
        }
        if (time.getYear() < Year.now().getValue() - 100) {
            return time.plusYears(THAI_BUDDHIST_OFFSET);
        }
        return time;
    }
}
