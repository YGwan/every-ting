package com.everyTing.photo.domain;

import com.everyTing.photo.domain.data.GeneratedImgUrls;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class GeneratedPhoto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long memberId;

    private GeneratedImgUrls generatedImgUrls;

    public GeneratedPhoto() {
    }

    public GeneratedPhoto(Long memberId, GeneratedImgUrls generatedImgUrls) {
        this.memberId = memberId;
        this.generatedImgUrls = generatedImgUrls;
    }

    public static GeneratedPhoto from(ValidatedGeneratedImgUrlsSaveRequest request) {
        return new GeneratedPhoto(
                request.getMemberId(),
                request.getGeneratedImgUrls()
        );
    }
}
