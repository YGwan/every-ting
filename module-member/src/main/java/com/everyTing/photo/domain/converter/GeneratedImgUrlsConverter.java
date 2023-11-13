package com.everyTing.photo.domain.converter;

import com.everyTing.photo.domain.data.GeneratedImgUrls;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.stream.Collectors;

public class GeneratedImgUrlsConverter implements AttributeConverter<List<String>, String> {

    private static final String DELIMITER = ",,,";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        GeneratedImgUrls.validate(attribute);

        return attribute.stream()
                .map(GeneratedImgUrls::from)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return List.of(dbData.split(DELIMITER));
    }
}
