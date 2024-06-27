package kr.ac.primitive.dto.member.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLogInRequestDto {
    private String email;
    private String password;

    @Builder
    public MemberLogInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
