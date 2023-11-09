package com.everyTing.photo.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.photo.dto.request.GeneratedImgUrlsSaveRequest;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsAddRequest;
import com.everyTing.photo.service.PhotoService;
import com.everyTing.photo.service.S3Service;
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
    public Response<Void> generatedImgUrlsAdd(@RequestBody GeneratedImgUrlsSaveRequest request) {
        final var validatedRequest = ValidatedGeneratedImgUrlsAddRequest.from(request);
        photoService.addGeneratedPhoto(validatedRequest);
        return Response.success();
    }
}
