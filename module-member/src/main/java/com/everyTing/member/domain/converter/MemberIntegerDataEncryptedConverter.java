package com.everyTing.member.domain.converter;

import com.everyTing.member.utils.MemberDataEncoder;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MemberIntegerDataEncryptedConverter implements AttributeConverter<Integer, String> {

    @Override
    public String convertToDatabaseColumn(Integer plain) {
        final var plainText = Integer.toString(plain);
        return MemberDataEncoder.encrypt(plainText);
    }

    @Override
    public Integer convertToEntityAttribute(String encryptedText) {
        return Integer.parseInt(MemberDataEncoder.decrypt(encryptedText));
    }
}
