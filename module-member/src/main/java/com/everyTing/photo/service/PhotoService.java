package com.everyTing.photo.service;

import com.everyTing.core.exception.TingServerException;
import com.everyTing.photo.domain.GeneratedPhotos;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import com.everyTing.photo.repository.GeneratedPhotoRepository;
import org.springframework.stereotype.Service;

import static com.everyTing.photo.errorCode.PhotoServerErrorCode.PSER_002;

@Service
public class PhotoService {

    private final GeneratedPhotoRepository generatedPhotoRepository;

    public PhotoService(GeneratedPhotoRepository generatedPhotoRepository) {
        this.generatedPhotoRepository = generatedPhotoRepository;
    }

    public void addGeneratedPhoto(ValidatedGeneratedImgUrlsSaveRequest validatedRequest) {
        final var memberId = validatedRequest.getMemberId();

        if (generatedPhotoRepository.existsByMemberId(memberId)) {
            throw new TingServerException(PSER_002);
        }

        final var generatedPhoto = GeneratedPhotos.from(validatedRequest);
        generatedPhotoRepository.save(generatedPhoto);
    }
}
