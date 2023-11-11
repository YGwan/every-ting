package com.everyTing.photo.repository;

import com.everyTing.photo.domain.PhotoRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRequestRepository extends JpaRepository<PhotoRequest, Long> {

    Boolean existsByMemberId(Long memberId);

    Optional<PhotoRequest> findByMemberId(Long memberId);
}
