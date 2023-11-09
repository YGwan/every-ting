package com.everyTing.photo.domain;

import com.everyTing.photo.domain.data.GeneratedImgUrls;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "index_memberId", columnList = "memberId"))
@Entity
public class GeneratedPhotos {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long memberId;

    private GeneratedImgUrls generatedImgUrls;

    private GeneratedPhotos(Long memberId, GeneratedImgUrls generatedImgUrls) {
        this.memberId = memberId;
        this.generatedImgUrls = generatedImgUrls;
    }

    public static GeneratedPhotos from(ValidatedGeneratedImgUrlsSaveRequest request) {
        return new GeneratedPhotos(
                request.getMemberId(),
                request.getGeneratedImgUrls()
        );
    }
}
