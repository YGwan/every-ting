package com.everyTing.member.service;

import com.everyTing.core.exception.TingApplicationException;
import com.everyTing.core.token.data.MemberTokens;
import com.everyTing.core.token.service.TokenService;
import com.everyTing.member.domain.Member;
import com.everyTing.member.domain.data.KakaoId;
import com.everyTing.member.domain.data.Username;
import com.everyTing.member.dto.validatedDto.ValidatedSignInRequest;
import com.everyTing.member.dto.validatedDto.ValidatedSignUpRequest;
import com.everyTing.member.repository.MemberRepository;
import com.everyTing.member.service.mail.MailService;
import com.everyTing.member.service.mail.form.SignUpForm;
import com.everyTing.member.utils.RandomCodeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static com.everyTing.member.errorCode.MemberErrorCode.MEMBER_009;

@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;
    private final MailService mailService;

    public MemberService(MemberRepository memberRepository, TokenService tokenService, MailService mailService) {
        this.memberRepository = memberRepository;
        this.tokenService = tokenService;
        this.mailService = mailService;
    }

    public boolean existsMemberByUsername(Username username) {
        return memberRepository.existsByUsername(username);
    }

    public boolean existsMemberByKakaoId(KakaoId kakaoId) {
        return memberRepository.existsByKakaoId(kakaoId);
    }

    @Transactional
    public MemberTokens signUp(ValidatedSignUpRequest request) {
        final Member newMember = memberRepository.save(Member.from(request));
        return tokenService.issue(newMember.getId());
    }

    @Transactional
    public MemberTokens signIn(ValidatedSignInRequest request) {
        Member member = memberRepository.findByUniversityEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> new TingApplicationException(MEMBER_009));
        return tokenService.issue(member.getId());
    }

    public MemberTokens reissueToken(HttpServletRequest request) {
        return tokenService.reissue(request);
    }

    public void sendAuthCodeFromUniversityEmail(String username, String universityEmail) {
        final String emailAuthCode = RandomCodeUtils.generate();
        mailService.sendMail(universityEmail, new SignUpForm(username, emailAuthCode));
    }
}
