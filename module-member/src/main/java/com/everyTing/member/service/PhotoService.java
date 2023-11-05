package com.everyTing.member.service;

import com.everyTing.member.domain.GeneratedPhoto;
import com.everyTing.member.dto.validatedDto.ValidatedGeneratedImgUrlsSaveRequest;
import com.everyTing.member.repository.GeneratedPhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    private final GeneratedPhotoRepository generatedPhotoRepository;

    public PhotoService(GeneratedPhotoRepository generatedPhotoRepository) {
        this.generatedPhotoRepository = generatedPhotoRepository;
    }

    public void addGeneratedPhoto(ValidatedGeneratedImgUrlsSaveRequest validatedRequest) {
        final var generatedPhoto = GeneratedPhoto.from(validatedRequest);
        generatedPhotoRepository.save(generatedPhoto);
    }
}
