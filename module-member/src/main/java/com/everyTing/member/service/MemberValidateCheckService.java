package com.everyTing.member.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.UniversityEmail;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import static com.everyTing.member.errorCode.MemberErrorCode.*;

@Service
public class MemberValidateCheckService {

    private final MemberRepository memberRepository;

    public MemberValidateCheckService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void throwIfExistUsername(Username username) {
        if (memberRepository.existsByUsername(username)) {
            throw new TingApplicationException(MEMBER_006);
        }
    }

    public void throwIfExistEmail(UniversityEmail email) {
        if (memberRepository.existsByUniversityEmail(email)) {
            throw new TingApplicationException(MEMBER_007);
        }
    }

    public void throwIfNotExistEmail(UniversityEmail email) {
        if (!memberRepository.existsByUniversityEmail(email)) {
            throw new TingApplicationException(MEMBER_015);
        }
    }

    public void throwIfExistKakaoId(KakaoId kakaoId) {
        if (memberRepository.existsByKakaoId(kakaoId)) {
            throw new TingApplicationException(MEMBER_008);
        }
    }
}
