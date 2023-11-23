package com.everyTing.member.domain.converter;

import com.everyTing.member.utils.MemberDataEncoder;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MemberDataEncryptedConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String plainText) {
        return MemberDataEncoder.encrypt(plainText);
    }

    @Override
    public String convertToEntityAttribute(String encryptedText) {
        return MemberDataEncoder.decrypt(encryptedText);
    }
}