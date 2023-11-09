package com.everyTing.photo.domain;

import com.everyTing.core.domain.AuditingFields;
import com.everyTing.member.domain.data.ProfilePhoto;
import com.everyTing.photo.domain.data.GeneratedImgUrl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "index_memberId", columnList = "memberId"))
@Entity
public class ProfilePhotoInfo extends AuditingFields {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long memberId;

    private ProfilePhoto profilePhoto;

    private GeneratedImgUrl generatedImgUrl;
}
