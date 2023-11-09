package com.everyTing.photo.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.member.service.MemberService;
import com.everyTing.photo.service.GeneratedPhotoService;
import com.everyTing.photo.service.S3Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/photos")
@RestController
public class PhotoController {

    private final MemberService memberService;
    private final S3Service s3Service;

    public PhotoController(MemberService memberService, S3Service s3Service) {
        this.memberService = memberService;
        this.s3Service = s3Service;
    }

    @PostMapping
    public Response<Void> profilePhotoAdd(@LoginMember LoginMemberInfo memberInfo,
                                          @RequestPart("profile_photo") MultipartFile multipartFile) {
        final var memberId = memberInfo.getId();
        final var profilePhotoUrl = s3Service.uploadPhoto(memberId, multipartFile);
        memberService.modifyProfilePhoto(memberId, profilePhotoUrl);
        return Response.success();
    }
}
