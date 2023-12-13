package com.everyTing.member.dto.form;

public class SignUpForm implements MailForm {

    private final String username;
    private final String authCode;

    public SignUpForm(String username, String authCode) {
        this.username = username;
        this.authCode = authCode;
    }

    @Override
    public String title() {
        return "EVERYTING 대학교 이메일 인증 메일입니다.";
    }

    @Override
    public String body() {
        return username + "님 \n"
                + "저희 EVERYTING을 찾아주셔서 감사합니다.\n"
                + "아래의 인증 코드를 입력해주세요.\n\n"
                + "==========\n"
                + authCode + "\n"
                + "==========";
    }
}
