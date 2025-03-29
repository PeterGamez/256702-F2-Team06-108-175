package com.mechat.utils;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class YearConverter implements AttributeConverter<LocalDateTime, LocalDateTime> {

    private static final int THAI_BUDDHIST_OFFSET = 543;

    public LocalDateTime convertToDatabaseColumn(LocalDateTime attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.minusYears(THAI_BUDDHIST_OFFSET);
    }

    public LocalDateTime convertToEntityAttribute(LocalDateTime dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.plusYears(THAI_BUDDHIST_OFFSET);
    }
}
