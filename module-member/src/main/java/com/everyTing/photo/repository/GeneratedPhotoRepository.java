package com.everyTing.photo.repository;

import com.everyTing.photo.domain.GeneratedPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratedPhotoRepository extends JpaRepository<GeneratedPhoto, Long> {
}
