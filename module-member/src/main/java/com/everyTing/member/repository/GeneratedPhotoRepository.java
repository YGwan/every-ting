package com.everyTing.member.repository;

import com.everyTing.member.domain.GeneratedPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedPhotoRepository extends JpaRepository<GeneratedPhoto, Long> {
}
