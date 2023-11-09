package com.everyTing.photo.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.photo.dto.request.GeneratedImgUrlsSaveRequest;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import com.everyTing.photo.service.GeneratedPhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/generated/photos")
@RestController
public class GeneratedPhotoController {

    private final GeneratedPhotoService generatedPhotoService;

    public GeneratedPhotoController(GeneratedPhotoService generatedPhotoService) {
        this.generatedPhotoService = generatedPhotoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response<Void> generatedImgUrlsSave(@RequestBody GeneratedImgUrlsSaveRequest request) {
        final var validatedRequest = ValidatedGeneratedImgUrlsSaveRequest.from(request);
        generatedPhotoService.addGeneratedPhoto(validatedRequest);
        return Response.success();
    }
}
