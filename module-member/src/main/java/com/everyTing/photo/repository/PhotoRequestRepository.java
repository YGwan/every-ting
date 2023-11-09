package com.everyTing.photo.repository;

import com.everyTing.photo.domain.PhotoRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRequestRepository extends JpaRepository<PhotoRequest, Long> {
}
