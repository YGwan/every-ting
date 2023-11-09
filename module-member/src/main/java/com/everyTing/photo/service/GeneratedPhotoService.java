package com.everyTing.photo.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.photo.domain.GeneratedPhoto;
import com.everyTing.photo.dto.response.GeneratedPhotoResponse;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import com.everyTing.photo.repository.GeneratedPhotoRepository;
import org.springframework.stereotype.Service;

import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_004;
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

    public GeneratedPhotoResponse findGeneratedPhoto(Long memberId) {
        final var generatedPhoto = generatedPhotoRepository.findByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(PHOTO_004)
        );

        return GeneratedPhotoResponse.from(generatedPhoto);
    }
}
