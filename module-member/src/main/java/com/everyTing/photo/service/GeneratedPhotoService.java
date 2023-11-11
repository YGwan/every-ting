package com.everyTing.photo.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.exception.TingServerException;
import com.everyTing.photo.domain.GeneratedPhoto;
import com.everyTing.photo.dto.response.GeneratedPhotoResponse;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedPhotoAddRequest;
import com.everyTing.photo.repository.GeneratedPhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_004;
import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_007;

@Transactional
@Service
public class GeneratedPhotoService {

    private final GeneratedPhotoRepository generatedPhotoRepository;

    public GeneratedPhotoService(GeneratedPhotoRepository generatedPhotoRepository) {
        this.generatedPhotoRepository = generatedPhotoRepository;
    }

    public void addGeneratedPhoto(ValidatedGeneratedPhotoAddRequest validatedRequest) {
        final var memberId = validatedRequest.getMemberId();

        if (generatedPhotoRepository.existsByMemberId(memberId)) {
            throw new TingServerException(PHOTO_007);
        }

        final var generatedPhoto = GeneratedPhoto.from(validatedRequest);
        generatedPhotoRepository.save(generatedPhoto);
    }

    @Transactional(readOnly = true)
    public GeneratedPhotoResponse findGeneratedPhoto(Long memberId) {
        final var generatedPhoto = getGeneratedPhotoByMemberId(memberId);

        return GeneratedPhotoResponse.from(generatedPhoto);
    }

    public void removeGeneratedPhoto(Long memberId) {
        generatedPhotoRepository.deleteByMemberId(memberId);
    }

    private GeneratedPhoto getGeneratedPhotoByMemberId(Long memberId) {
        return generatedPhotoRepository.findByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(PHOTO_004)
        );
    }
}
