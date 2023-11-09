package com.everyTing.photo.domain;

import com.everyTing.photo.domain.data.GeneratedImgUrls;
import com.everyTing.photo.dto.validatedRequest.ValidatedGeneratedImgUrlsSaveRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "index_memberId", columnList = "memberId"))
@Entity
public class GeneratedPhoto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long memberId;

    private GeneratedImgUrls generatedImgUrls;

    private GeneratedPhoto(Long memberId, GeneratedImgUrls generatedImgUrls) {
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
