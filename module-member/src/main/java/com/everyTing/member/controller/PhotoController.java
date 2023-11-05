package com.everyTing.member.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.member.dto.request.GeneratedImgUrlsSaveRequest;
import com.everyTing.member.dto.validatedDto.ValidatedGeneratedImgUrlsSaveRequest;
import com.everyTing.member.service.PhotoService;
import com.everyTing.member.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/photo")
@RestController
public class PhotoController {

    private final S3Service s3Service;
    private final PhotoService photoService;

    public PhotoController(S3Service s3Service, PhotoService photoService) {
        this.s3Service = s3Service;
        this.photoService = photoService;
    }

    @PostMapping("/upload/image")
    public Response<Void> test(@RequestPart("file") MultipartFile multipartFile) {
        s3Service.uploadPhoto(multipartFile);
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
