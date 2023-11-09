package com.everyTing.photo.repository;

import com.everyTing.photo.domain.GeneratedPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedPhotoRepository extends JpaRepository<GeneratedPhotos, Long> {

    Boolean existsByMemberId(Long memberId);
}
