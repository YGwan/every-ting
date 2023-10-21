package com.everyTing.member.service.mail.form;

public class ResetPasswordForm implements MailForm {

    private final String authCode;

    public ResetPasswordForm(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String title() {
        return "EVERYTING 비밀번호 재설정 인증 코드";
    }

    @Override
    public String body() {
        return "안녕하세요 :)\n" +
                "EVERYTING 비밀번호 재설정 관련 이메일입니다.\n" +
                "비밀번호 재설정 인증 코드를 보내드립니다. 아래의 인증 코드를 입력해주세요.\n\n"
                + "==========\n"
                + authCode + "\n"
                + "==========";
    }
}