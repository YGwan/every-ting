package com.everyTing.member.domain.converter;

import com.everyTing.member.service.encrypt.EncryptService;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MemberIntegerDataEncryptedConverter implements AttributeConverter<Integer, String> {

    private final EncryptService encryptService;

    public MemberIntegerDataEncryptedConverter(EncryptService encryptService) {
        this.encryptService = encryptService;
    }

    @Override
    public String convertToDatabaseColumn(Integer plain) {
        final var plainText = Integer.toString(plain);
        return encryptService.encryptedMemberData(plainText);
    }

    @Override
    public Integer convertToEntityAttribute(String cipherText) {
        String memberData = encryptService.decryptedMemberData(cipherText);
        return Integer.parseInt(memberData);
    }
}
