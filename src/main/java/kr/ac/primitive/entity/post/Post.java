package kr.ac.primitive.entity.post;

import jakarta.persistence.*;
import kr.ac.primitive.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter // id는 변하면 안됨
    private Long id;

    @Column(nullable = false)
    private String title;
    private String summary;
    private String description;

    @Column(nullable = false)
    private boolean isPublic;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter // user는 변하면 안됨
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechStack> techStacks;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants;

    public Post() {
    }

    public Post(String title, String summary, String description, boolean isPublic, String image, LocalDateTime createdAt, User user, List<TechStack> techStacks, List<Participant> participants) {
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.isPublic = isPublic;
        this.image = image;
        this.createdAt = createdAt;
        this.user = user;
        this.techStacks = techStacks;
        this.participants = participants;
    }
}