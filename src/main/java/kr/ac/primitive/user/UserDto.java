package kr.ac.primitive.user;

import lombok.Getter;

@Getter
public class UserDto {
    private Long id;
    private String studentNumber;
    private String password;
    private String name;
    private String major;

    public User toEntity() {
        return User.builder()
                .id(id)
                .studentNumber(studentNumber)
                .password(password)
                .name(name)
                .major(major)
                .build();
    }
}
