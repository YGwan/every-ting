package com.everyTing.member.repository;

import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByIdIn(List<Long> memberIds);

    boolean existsByUsername(Username username);

    boolean existsByKakaoId(KakaoId kakaoId);

    boolean existsByUniversityEmail(UniversityEmail email);

    Optional<Member> findByUniversityEmail(UniversityEmail universityEmail);
}
