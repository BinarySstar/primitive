package kr.ac.primitive.dto.member.response;

import kr.ac.primitive.entity.member.Member;
import kr.ac.primitive.entity.member.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String studentId;
    private Role role;

    @Builder
    public MemberResponseDto(Long id, String name, String email, String studentId, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.role = role;
    }
    public static MemberResponseDto toDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .studentId(member.getStudentId())
                .role(member.getRole())
                .build();
    }
}
