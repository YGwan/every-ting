package com.everyTing.member.repository;

import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(Username username);

    boolean existsByKakaoId(KakaoId kakaoId);

    boolean existsByUniversityEmail(UniversityEmail email);

    Optional<Member> findByUniversityEmail(UniversityEmail universityEmail);

    Optional<Member> findByUniversityEmailAndPassword(UniversityEmail universityEmail, Password password);
}
