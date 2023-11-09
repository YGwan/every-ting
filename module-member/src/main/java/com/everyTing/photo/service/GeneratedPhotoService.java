package com.everyTing.photo.service;

import com.everyTing.core.exception.TingServerException;
import com.everyTing.photo.domain.GeneratedPhoto;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import com.everyTing.photo.repository.GeneratedPhotoRepository;
import org.springframework.stereotype.Service;

import static com.everyTing.photo.errorCode.PhotoServerErrorCode.PSER_002;

@Service
public class GeneratedPhotoService {

    private final GeneratedPhotoRepository generatedPhotoRepository;

    public GeneratedPhotoService(GeneratedPhotoRepository generatedPhotoRepository) {
        this.generatedPhotoRepository = generatedPhotoRepository;
    }

    public void addGeneratedPhoto(ValidatedGeneratedImgUrlsSaveRequest validatedRequest) {
        final var memberId = validatedRequest.getMemberId();

        if (generatedPhotoRepository.existsByMemberId(memberId)) {
            throw new TingServerException(PSER_002);
        }

        final var generatedPhoto = GeneratedPhoto.from(validatedRequest);
        generatedPhotoRepository.save(generatedPhoto);
    }
}
