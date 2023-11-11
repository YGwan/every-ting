package com.everyTing.photo.domain;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.photo.domain.data.PhotoRequestStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "index_memberId", columnList = "memberId"))
@Entity
public class PhotoRequest extends AuditingFields {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long memberId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PhotoRequestStatus requestStatus;

    private PhotoRequest(Long memberId, PhotoRequestStatus requestStatus) {
        this.memberId = memberId;
        this.requestStatus = requestStatus;
    }

    public static PhotoRequest of(Long memberId, PhotoRequestStatus requestStatus) {
        return new PhotoRequest(memberId, requestStatus);
    }

    public void modifyPhotoRequestStatus(PhotoRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
