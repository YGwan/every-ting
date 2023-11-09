package com.everyTing.photo.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.photo.domain.PhotoRequest;
import com.everyTing.photo.domain.data.PhotoRequestStatus;
import com.everyTing.photo.dto.response.PhotoRequestResponse;
import com.everyTing.photo.repository.PhotoRequestRepository;
import org.springframework.stereotype.Service;

import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_005;

@Service
public class PhotoService {

    private final PhotoRequestRepository photoRequestRepository;

    public PhotoService(PhotoRequestRepository photoRequestRepository) {
        this.photoRequestRepository = photoRequestRepository;
    }

    public void addPhotoRequest(Long memberId) {
        if (photoRequestRepository.existsByMemberId(memberId)) {
            throw new TingApplicationException(PHOTO_005);
        }

        final var photoRequest = PhotoRequest.of(memberId, PhotoRequestStatus.REQUESTING);
        photoRequestRepository.save(photoRequest);
    }

    public PhotoRequestResponse findPhotoRequest(Long memberId) {
        return photoRequestRepository.findByMemberId(memberId)
                .map(PhotoRequestResponse::of)
                .orElseGet(() -> new PhotoRequestResponse(PhotoRequestStatus.NOT_FOUND));
    }
}
