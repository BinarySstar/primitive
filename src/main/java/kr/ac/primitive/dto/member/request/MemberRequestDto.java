package kr.ac.primitive.dto.member.request;

import kr.ac.primitive.entity.member.Member;
import kr.ac.primitive.entity.member.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequestDto {
    private String name;
    private String email;
    private String studentId;
    private String password;
    private String checkPassword;

    @Builder
    public MemberRequestDto(String name, String email, String studentId, String password, String checkPassword) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.password = password;
        this.checkPassword = checkPassword;
    }
    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .studentId(this.studentId)
                .password(this.password)
                .role(Role.USER)
                .build();
    }
}
