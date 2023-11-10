package com.everyTing.photo.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.member.service.MemberService;
import com.everyTing.photo.domain.data.PhotoRequestStatus;
import com.everyTing.photo.dto.response.PhotoRequestResponse;
import com.everyTing.photo.service.PhotoService;
import com.everyTing.photo.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/profile-photos")
@RestController
public class PhotoController {

    private final S3Service s3Service;
    private final MemberService memberService;
    private final PhotoService photoService;

    public PhotoController(S3Service s3Service, MemberService memberService, PhotoService photoService) {
        this.s3Service = s3Service;
        this.memberService = memberService;
        this.photoService = photoService;
    }

    @GetMapping("/requests")
    public Response<PhotoRequestResponse> photoRequestList(@LoginMember LoginMemberInfo memberInfo) {
        PhotoRequestResponse response = photoService.findPhotoRequest(memberInfo.getId());
        return Response.success(response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/requests")
    public Response<Void> photoRequestAdd(@LoginMember LoginMemberInfo memberInfo) {
        photoService.addPhotoRequest(memberInfo.getId());
        return Response.success();
    }

    @PutMapping("/requests/failed/{memberId}")
    public Response<Void> photoRequestModifyFailed(@PathVariable Long memberId) {
        photoService.modifyPhotoRequest(memberId, PhotoRequestStatus.FAILED);
        return Response.success();
    }

    @PutMapping("/requests/completed/{memberId}")
    public Response<Void> photoRequestModifyCompleted(@PathVariable Long memberId) {
        photoService.modifyPhotoRequest(memberId, PhotoRequestStatus.COMPLETED);
        return Response.success();
    }

    @DeleteMapping("/requests")
    public Response<Void> photoRequestRemove(@LoginMember LoginMemberInfo memberInfo) {
        photoService.removePhotoRequest(memberInfo.getId());
        return Response.success();
    }

    @PostMapping
    public Response<Void> profilePhotoUploadAndUpdate(@LoginMember LoginMemberInfo memberInfo,
                                                      @RequestPart("profile_photo") MultipartFile multipartFile) {
        final var memberId = memberInfo.getId();
        final var profilePhotoUrl = s3Service.uploadPhoto(memberId, multipartFile);
        memberService.modifyProfilePhoto(memberId, profilePhotoUrl);
        return Response.success();
    }
}
