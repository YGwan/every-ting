package com.everyTing.photo.repository;

import com.everyTing.photo.domain.GeneratedPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneratedPhotoRepository extends JpaRepository<GeneratedPhoto, Long> {

    Boolean existsByMemberId(Long memberId);

    Optional<GeneratedPhoto> findByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);
}
