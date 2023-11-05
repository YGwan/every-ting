package com.everyTing.member.domain;

import com.everyTing.member.domain.data.GeneratedImgUrls;
import com.everyTing.member.dto.validatedDto.ValidatedGeneratedImgUrlsSaveRequest;
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

    private Long userId;

    private GeneratedImgUrls generatedImgUrls;

    public GeneratedPhoto() {
    }

    public GeneratedPhoto(Long userId, GeneratedImgUrls generatedImgUrls) {
        this.userId = userId;
        this.generatedImgUrls = generatedImgUrls;
    }

    public static GeneratedPhoto from(ValidatedGeneratedImgUrlsSaveRequest request) {
        return new GeneratedPhoto(
                request.getUserId(),
                request.getGeneratedImgUrls()
        );
    }
}
