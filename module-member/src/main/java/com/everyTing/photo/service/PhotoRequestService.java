package com.everyTing.photo.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.photo.dto.notification.PhotoGeneratedCompletedForm;
import com.everyTing.photo.dto.notification.PhotoGeneratedErrorForm;
import com.everyTing.notification.service.NotificationDataService;
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

    private final NotificationDataService notificationDataService;
    private final PhotoRequestRepository photoRequestRepository;

    public PhotoRequestService(NotificationDataService notificationDataService, PhotoRequestRepository photoRequestRepository) {
        this.notificationDataService = notificationDataService;
        this.photoRequestRepository = photoRequestRepository;
    }

    public void addPhotoRequest(Long memberId, PhotoRequestStatus status) {
        if (photoRequestRepository.existsByMemberId(memberId)) {
            throw new TingApplicationException(PHOTO_005);
        }

        final var photoRequest = PhotoRequest.of(memberId, status);
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
            notificationDataService.sendNotificationAndAddNotification(memberId, new PhotoGeneratedCompletedForm());
        } else if (status == PhotoRequestStatus.FAILED) {
            notificationDataService.sendNotificationAndAddNotification(memberId, new PhotoGeneratedErrorForm());
        }
    }

    public void removePhotoRequest(Long memberId) {
        photoRequestRepository.deleteByMemberId(memberId);
    }

    private PhotoRequest getPhotoRequestByMemberId(Long memberId) {
        return photoRequestRepository.findByMemberId(memberId).orElseThrow(
                () -> new TingApplicationException(PHOTO_006)
        );
    }
}
