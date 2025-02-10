package com.mechat.utils;

import jakarta.persistence.AttributeConverter;

public class BoolToIntConverter implements AttributeConverter<Boolean, Integer> {

    public Integer convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? 1 : 0;
    }

    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData != null && dbData == 1;
    }
}
