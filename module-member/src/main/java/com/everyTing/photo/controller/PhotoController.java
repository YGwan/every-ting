package com.everyTing.photo.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.member.service.MemberService;
import com.everyTing.photo.dto.request.GeneratedImgUrlsSaveRequest;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import com.everyTing.photo.service.PhotoService;
import com.everyTing.photo.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/photo")
@RestController
public class PhotoController {

    private final MemberService memberService;
    private final S3Service s3Service;
    private final PhotoService photoService;

    public PhotoController(MemberService memberService, S3Service s3Service, PhotoService photoService) {
        this.memberService = memberService;
        this.s3Service = s3Service;
        this.photoService = photoService;
    }

    @PostMapping
    public Response<Void> profilePhotoAdd(@LoginMember LoginMemberInfo memberInfo,
                                          @RequestPart("profile_photo") MultipartFile multipartFile) {
        final var memberId = memberInfo.getId();
        final var profilePhotoUrl = s3Service.uploadPhoto(memberId, multipartFile);
        memberService.modifyProfilePhoto(memberId, profilePhotoUrl);
        return Response.success();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/generated")
    public Response<Void> generatedImgUrlsSave(@RequestBody GeneratedImgUrlsSaveRequest request) {
        final var validatedRequest = ValidatedGeneratedImgUrlsSaveRequest.from(request);
        photoService.addGeneratedPhoto(validatedRequest);
        return Response.success();
    }
}
