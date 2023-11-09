package com.everyTing.photo.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.member.service.MemberService;
import com.everyTing.photo.service.PhotoService;
import com.everyTing.photo.service.S3Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/requests")
    public Response<Void> photoRequestAdd(@LoginMember LoginMemberInfo memberInfo) {
        photoService.addPhotoRequest(memberInfo.getId());
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
