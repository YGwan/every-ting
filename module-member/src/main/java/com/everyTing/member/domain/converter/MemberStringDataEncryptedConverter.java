package com.everyTing.member.domain.converter;

import com.everyTing.member.service.EncryptService;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MemberStringDataEncryptedConverter implements AttributeConverter<String, String> {

    private final EncryptService encryptService;

    public MemberStringDataEncryptedConverter(EncryptService encryptService) {
        this.encryptService = encryptService;
    }

    @Override
    public String convertToDatabaseColumn(String plainText) {
        return encryptService.encryptedMemberData(plainText);
    }

    @Override
    public String convertToEntityAttribute(String cipherText) {
        return encryptService.decryptedMemberData(cipherText);
    }
}