package kr.ac.primitive.dto.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLogInRequestDto {

    @NotBlank
    private String email;

    @NotNull
    private String password;

    @Builder
    public MemberLogInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
