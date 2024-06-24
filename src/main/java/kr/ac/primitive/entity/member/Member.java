package kr.ac.primitive.entity.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String studentId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String name, String email, String studentId, String password, Role role) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.password = password;
        this.role = role;
    }
}
