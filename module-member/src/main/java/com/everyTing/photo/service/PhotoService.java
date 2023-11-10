package com.everyTing.photo.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.photo.domain.PhotoRequest;
import com.everyTing.photo.domain.data.PhotoRequestStatus;
import com.everyTing.photo.dto.response.PhotoRequestResponse;
import com.everyTing.photo.repository.PhotoRequestRepository;
import org.springframework.stereotype.Service;

import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_005;
import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_006;

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

        final var photoRequest = PhotoRequest.of(memberId, PhotoRequestStatus.REQUESTED);
        photoRequestRepository.save(photoRequest);
    }

    public PhotoRequestResponse findPhotoRequest(Long memberId) {
        return photoRequestRepository.findByMemberId(memberId)
                .map(PhotoRequestResponse::of)
                .orElseGet(() -> new PhotoRequestResponse(PhotoRequestStatus.NOT_FOUND));
    }

    public void modifyPhotoRequest(Long memberId, PhotoRequestStatus status) {
        final var photoRequest = getPhotoRequestByMemberId(memberId);
        photoRequest.modifyPhotoRequestStatus(status);
    }

    public void removePhotoRequest(Long memberId) {
        final var photoRequest = getPhotoRequestByMemberId(memberId);
        photoRequestRepository.delete(photoRequest);
    }

    private PhotoRequest getPhotoRequestByMemberId(Long memberId) {
        return photoRequestRepository.findByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(PHOTO_006)
        );
    }
}
