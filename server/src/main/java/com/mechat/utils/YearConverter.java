package com.mechat.utils;

import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.chrono.ThaiBuddhistChronology;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class YearConverter implements AttributeConverter<LocalDateTime, LocalDateTime> {

    public LocalDateTime convertToDatabaseColumn(LocalDateTime attribute) {
        if (attribute.getChronology().equals(ThaiBuddhistChronology.INSTANCE)) {
            return attribute.minusYears(543);
        }
        return attribute;
    }

    public LocalDateTime convertToEntityAttribute(LocalDateTime dbData) {
        if (dbData.getChronology().equals(IsoChronology.INSTANCE)) {
            return dbData.plusYears(543);
        }
        return dbData;
    }
}
