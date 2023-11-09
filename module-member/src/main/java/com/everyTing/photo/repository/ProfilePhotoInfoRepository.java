package com.everyTing.photo.repository;

import com.everyTing.photo.domain.ProfilePhotoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePhotoInfoRepository extends JpaRepository<ProfilePhotoInfo, Long> {
}
