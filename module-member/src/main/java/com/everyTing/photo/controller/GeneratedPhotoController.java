package com.everyTing.photo.controller;

import com.everyTing.core.dto.Response;
import com.everyTing.core.resolver.LoginMember;
import com.everyTing.core.resolver.LoginMemberInfo;
import com.everyTing.photo.dto.request.GeneratedPhotoAddRequest;
import com.everyTing.photo.dto.response.GeneratedPhotoResponse;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedPhotoAddRequest;
import com.everyTing.photo.service.GeneratedPhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/generated-photos")
@RestController
public class GeneratedPhotoController {

    private final GeneratedPhotoService generatedPhotoService;

    public GeneratedPhotoController(GeneratedPhotoService generatedPhotoService) {
        this.generatedPhotoService = generatedPhotoService;
    }

    @GetMapping
    public Response<GeneratedPhotoResponse> generatedPhotoList(@LoginMember LoginMemberInfo memberInfo) {
        final var response = generatedPhotoService.findGeneratedPhoto(memberInfo.getId());
        return Response.success(response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response<Void> generatedPhotoAdd(@RequestBody GeneratedPhotoAddRequest request) {
        final var validatedRequest = ValidatedGeneratedPhotoAddRequest.from(request);
        generatedPhotoService.addGeneratedPhoto(validatedRequest);
        return Response.success();
    }

    @DeleteMapping
    public Response<Void> generatedPhotoRemove(@LoginMember LoginMemberInfo memberInfo) {
        generatedPhotoService.removeGeneratedPhoto(memberInfo.getId());
        return Response.success();
    }
}
