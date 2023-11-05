package com.everyTing.member.controller;

import com.everyTing.member.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/photo")
@RestController
public class PhotoController {

    private final S3Service s3Service;

    public PhotoController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload/image")
    public ResponseEntity<Void> test(@RequestPart("file") MultipartFile multipartFile) {
        s3Service.uploadPhoto(multipartFile);
        return ResponseEntity.ok().build();
    }
}
