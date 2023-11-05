package com.everyTing.photo.service;

import com.everyTing.photo.domain.GeneratedPhoto;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import com.everyTing.photo.repository.GeneratedPhotoRepository;
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
