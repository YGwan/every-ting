package com.everyTing.photo.service;

import com.everyTing.photo.domain.PhotoRequest;
import com.everyTing.photo.domain.data.PhotoRequestStatus;
import com.everyTing.photo.repository.PhotoRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    private final PhotoRequestRepository photoRequestRepository;

    public PhotoService(PhotoRequestRepository photoRequestRepository) {
        this.photoRequestRepository = photoRequestRepository;
    }

    public void addPhotoRequest(Long memberId) {

        final var photoRequest = PhotoRequest.of(memberId, PhotoRequestStatus.REQUESTING);
        photoRequestRepository.save(photoRequest);
    }
}
