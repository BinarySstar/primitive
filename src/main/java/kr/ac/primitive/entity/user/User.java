package kr.ac.primitive.entity.user;

import jakarta.persistence.*;
import kr.ac.primitive.entity.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private String name;

    @Getter
    private String studentId;

    @Getter
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Participant> participants;

    public User() {
    }

    public User(String name, String studentId, String password, Role role) {
        this.name = name;
        this.studentId = studentId;
        this.password = password;
        this.role = role;
    }
}
