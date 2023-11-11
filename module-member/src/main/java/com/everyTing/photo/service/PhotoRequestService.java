package com.everyTing.photo.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.photo.domain.PhotoRequest;
import com.everyTing.photo.domain.data.PhotoRequestStatus;
import com.everyTing.photo.dto.request.PhotoRequestModifyRequest;
import com.everyTing.photo.dto.response.PhotoRequestResponse;
import com.everyTing.photo.repository.PhotoRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_005;
import static com.everyTing.photo.errorCode.PhotoErrorCode.PHOTO_006;

@Transactional
@Service
public class PhotoRequestService {

    private final PhotoRequestRepository photoRequestRepository;

    public PhotoRequestService(PhotoRequestRepository photoRequestRepository) {
        this.photoRequestRepository = photoRequestRepository;
    }

    public void addPhotoRequest(Long memberId) {
        if (photoRequestRepository.existsByMemberId(memberId)) {
            throw new TingApplicationException(PHOTO_005);
        }

        final var photoRequest = PhotoRequest.of(memberId, PhotoRequestStatus.REQUESTED);
        photoRequestRepository.save(photoRequest);
    }

    @Transactional(readOnly = true)
    public PhotoRequestResponse findPhotoRequest(Long memberId) {
        return photoRequestRepository.findByMemberId(memberId)
                .map(PhotoRequestResponse::from)
                .orElseGet(() -> new PhotoRequestResponse(PhotoRequestStatus.NOT_FOUND));
    }

    public void modifyPhotoRequest(PhotoRequestModifyRequest request) {
        final var memberId = request.getMemberId();
        final var status = request.getStatus();
        final var photoRequest = getPhotoRequestByMemberId(memberId);

        photoRequest.modifyPhotoRequestStatus(status);

        if (status == PhotoRequestStatus.COMPLETED) {
            // 성공 - 성공 알림 로직 처리
            System.out.println("성공");
            return;
        }
        if (status == PhotoRequestStatus.FAILED) {
            // 실패 - 실패 알림 로직 처리
            System.out.println("실패");
            return;
        }
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
